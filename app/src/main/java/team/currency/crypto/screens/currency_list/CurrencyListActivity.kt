package team.currency.crypto.screens.currency_list

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import team.currency.crypto.R
import team.currency.crypto.screens.money_list.MoneyListFragment
import tech.iuic.iuicwork.base.BaseActivity
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class CurrencyListActivity: BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun getViewId(): Int {
        return R.layout.currency_list_activity
    }


    protected override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        attachFragment(R.id.fragment_container, CurrencyListFragment())
    }





}