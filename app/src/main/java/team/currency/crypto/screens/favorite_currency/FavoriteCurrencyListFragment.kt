package team.currency.crypto.screens.favorite_currency

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.favorite_currency_list_fragment.*
import team.currency.crypto.R
import team.currency.crypto.bus.CurrencyBus
import team.currency.crypto.bus.CurrencyListBus
import team.currency.crypto.bus.FavoriteRefreshBus
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.screens.currency.CurrencyActivity
import team.currency.crypto.screens.currency_list.CurrencyListFragment
import team.currency.crypto.screens.currency_list.adapter.FavoriteCurrencyListAdapter
import tech.iuic.iuicwork.base.BaseFragment
import toplib.view.RecyclerViewFn
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */
class FavoriteCurrencyListFragment : BaseFragment(), HasSupportFragmentInjector, FavoriteCurrencyListAdapter.OnFavoriteCurrencyClickCallback {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: FavoriteCurrencyListViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    @Inject
    lateinit var recyclerViewFn: RecyclerViewFn


    internal lateinit var mCurrencyListAdapter: FavoriteCurrencyListAdapter


    @Inject
    lateinit var currencyBus: CurrencyBus


    @Inject
    lateinit var currencyListBus: CurrencyListBus

    @Inject
    lateinit var favoriteRefreshBus: FavoriteRefreshBus


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
         plus_image_button.setOnClickListener(View.OnClickListener { v->
            currencyListBus.type=1
             addFragment(R.id.fragment_container, CurrencyListFragment())

        })
        queryCurrencyList()

        favoriteRefreshBus.toObservable().subscribe{
            queryCurrencyList()
         }
        swipe_refresh_layout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3)
        swipe_refresh_layout.setOnRefreshListener({
            queryCurrencyList()
            swipe_refresh_layout.setRefreshing(false)
        })
    }


    private fun queryCurrencyList() {
        viewModel.queryCurrencyList()!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                    onCurrencyList(s)
                 })
    }


    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.favorite_currency_list_fragment, container, false)
    }

    fun onCurrencyList(list: List<Currency>) {
        if (currency_recycler_view != null) {
            mCurrencyListAdapter = FavoriteCurrencyListAdapter(this, list)
            recyclerViewFn.initRecyclerView(currency_recycler_view, mCurrencyListAdapter, LinearLayoutManager.VERTICAL)
        }
    }


    override fun onCurrencyClick(currency: Currency?, position: Int) {
        currencyBus.send(currency!!.id)
        startActivity(Intent(activity!!,CurrencyActivity::class.java))
     }

}
