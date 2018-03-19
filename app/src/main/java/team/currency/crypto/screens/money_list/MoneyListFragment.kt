package team.currency.crypto.screens.money_list

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.money_list_fragment.*
import team.currency.crypto.R
import team.currency.crypto.bus.MoneyBus
import team.currency.crypto.data.entity.CurrencyMoney
import team.currency.crypto.data.entity.MoneyHolder
import team.currency.crypto.screens.currency_list.CurrencyListViewModel
import team.currency.crypto.screens.money_list.adapter.MoneyListAdapter
import tech.iuic.iuicwork.base.BaseFragment
import toplib.view.RecyclerViewFn
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class MoneyListFragment: BaseFragment(), HasSupportFragmentInjector, MoneyListAdapter.OnCurrencyClickCallback {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: MoneyListViewModel


    @Inject
    lateinit var recyclerViewFn: RecyclerViewFn

    @Inject
    lateinit var moneyBus: MoneyBus


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


lateinit var  moneyListAdapter:MoneyListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
       back_image_button.setOnClickListener(View.OnClickListener { v->
           activity!!.finish()
       })

        viewModel.queryCurrencyList()!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                    onCurrencyList(s)
                    Log.e("TAG", s.data.toString())
                });


    }

    private fun onCurrencyList(moneyHolder: MoneyHolder) {
        if (currency_recycler_view != null) {
            moneyListAdapter = MoneyListAdapter(this, moneyHolder.data)
            recyclerViewFn.initRecyclerView(currency_recycler_view, moneyListAdapter, LinearLayoutManager.VERTICAL)
        }

    }


    protected override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.money_list_fragment, container, false)
    }



    override fun onCurrencyClick(currency: CurrencyMoney?, position: Int) {
        if (currency != null) {
            moneyBus.send(currency)
        }
       activity!!.finish()

    }


}
