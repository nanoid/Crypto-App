package team.currency.crypto.screens.converter

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.converter_fragment.*
import team.currency.crypto.R
import team.currency.crypto.bus.CurrencyBus
import team.currency.crypto.bus.CurrencyConverterBus
import team.currency.crypto.bus.CurrencyListBus
import team.currency.crypto.bus.MoneyBus
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.CurrencyMoney
import team.currency.crypto.data.entity.MoneyHolder
import team.currency.crypto.screens.currency.CurrencyViewModel
import team.currency.crypto.screens.currency_list.CurrencyListActivity
import team.currency.crypto.screens.currency_list.CurrencyListFragment
import team.currency.crypto.screens.money_list.MoneyListActivity
import team.currency.crypto.screens.money_list.MoneyListFragment
import team.currency.crypto.screens.money_list.MoneyListViewModel
import tech.iuic.iuicwork.base.BaseFragment
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class ConverterFragment:BaseFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: MoneyListViewModel

    @Inject
    lateinit var moneyBus: MoneyBus


    lateinit var mMoneyHolder: CurrencyHolder

    @Inject
    lateinit var currencyListBus: CurrencyListBus

    @Inject
    lateinit var currencyConverterBus: CurrencyConverterBus

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    lateinit var currency:Currency

    lateinit var currencyMoney: CurrencyMoney


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
        //Bitcoin

        queryCurrency(1)
        crypto_button.setOnClickListener(View.OnClickListener { v->
           // addFragment(R.id.converter_layout,CurrencyListFragment())
            currencyListBus.type=2
            startActivity(Intent(activity!!,CurrencyListActivity::class.java))
            currencyConverterBus.toObservable().subscribe { v->
                currency = v
                crypto_button.text = currency.shortName
             }
        })

        result_button.setOnClickListener(View.OnClickListener { v->
   var result:Float
   //Log.e("currency.cost", currency.cost.toString())
            //Log.e("currencyMoney.cost", currencyMoney.cost.toString())

   if(edit_text.text.toString().length!=0) {
       result = currency.cost * edit_text.text.toString().toFloat() * currencyMoney.rate
       result_text_view.text = result.toString() + " " + currencyMoney.code
   }})

        money_button.setOnClickListener(View.OnClickListener { v->
            startActivity(Intent(activity!!,MoneyListActivity::class.java))
              moneyBus.toObservable().subscribe{v->
                  currencyMoney = v
                  money_button.text = currencyMoney.alphaCode
             Log.e("money",v.name)
            }
         })

    }


    private fun queryCurrency(id:Long) {
        viewModel.queryCurrency(1)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                onCurrencyList(s)
                 });
    }

    private fun onCurrencyList(moneyHolder: CurrencyHolder) {
        mMoneyHolder = moneyHolder
        currency = Currency(moneyHolder.id,moneyHolder.shortName,moneyHolder.shortName,moneyHolder.imageUrl,moneyHolder.cost,
                moneyHolder.change_one_hour,moneyHolder.change_twenty_four_hour,moneyHolder.change_one_week,
                moneyHolder.modified)
        currencyMoney = CurrencyMoney(1,"USD","444",1,"USD",1f,"1")
        crypto_button.text= moneyHolder.shortName

        progress_bar_relative_layout.visibility = View.GONE

        converter.visibility = View.VISIBLE
    }


    protected override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.converter_fragment, container, false)
    }





}
