package team.currency.crypto.screens.currency_list

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
import kotlinx.android.synthetic.main.currency_list_fragment.*
import team.currency.crypto.R
import team.currency.crypto.bus.CurrencyConverterBus
import team.currency.crypto.bus.CurrencyListBus
import team.currency.crypto.bus.FavoriteRefreshBus
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.screens.currency_list.adapter.CurrencyListAdapter
import tech.iuic.iuicwork.base.BaseFragment
import toplib.view.RecyclerViewFn
import javax.inject.Inject

/**
 * Created by nanoid3 on 08.02.2018.
 */
class CurrencyListFragment : BaseFragment(), HasSupportFragmentInjector, CurrencyListAdapter.OnCurrencyClickCallback {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: CurrencyListViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    @Inject
    lateinit var recyclerViewFn: RecyclerViewFn


    internal lateinit var mCurrencyListAdapter: CurrencyListAdapter


    lateinit var list: ArrayList<Currency>

    @Inject
    lateinit var currencyListBus: CurrencyListBus


    @Inject
    lateinit var currencyConverterBus: CurrencyConverterBus

    @Inject
    lateinit var favoriteRefreshBus: FavoriteRefreshBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
        back_image_button.setOnClickListener({
            hideFragment(this)
        })

        queryCurrencyList()


       linear_layout_text_view.setOnClickListener({
           queryList()

       })

        if(currencyListBus.type==2){
            linear_layout_text_view.visibility = View.GONE

        }


     }


    private fun queryList() {
        viewModel.addToFoavoriteList(list)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                    favoriteRefreshBus.send(1)
                    hideFragment(this)
                });
    }

    private fun queryCurrencyList() {
        viewModel.queryCurrencyList()!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                    onCurrencyList(s)
                    Log.e("TAG", s.data.toString())
                });
    }


    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    fun onCurrencyList(categoryListResponses: DataHolder) {
        list = categoryListResponses.data
        if (currency_recycler_view != null) {
            mCurrencyListAdapter = CurrencyListAdapter(this, categoryListResponses.data)
            recyclerViewFn.initRecyclerView(currency_recycler_view, mCurrencyListAdapter, LinearLayoutManager.VERTICAL)
        }
    }


    override fun onCurrencyClick(currency: Currency?, position: Int) {
        if(currencyListBus.type==2){
            if (currency!!.type == 0) {
                currency!!.type = 1
                mCurrencyListAdapter.notifyDataSetChanged()
            }else if (currency!!.type == 1) {
                currency!!.type = 0
                mCurrencyListAdapter.notifyDataSetChanged()
            }
            currencyConverterBus.send(currency)
          activity!!.finish()
            return
        }



        if (currency!!.type == 0) {
            currency!!.type = 1
            mCurrencyListAdapter.notifyDataSetChanged()
        }else if (currency!!.type == 1) {
            currency!!.type = 0
            mCurrencyListAdapter.notifyDataSetChanged()
        }
    }


}
