package team.currency.crypto.screens.money_list

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import team.currency.crypto.R
import team.currency.crypto.main_screen.HostFragment
import tech.iuic.iuicwork.base.BaseActivity
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class MoneyListActivity: BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun getViewId(): Int {
        return R.layout.money_list_activity
    }


    protected override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        attachFragment(R.id.fragment_container, MoneyListFragment())
    }



/*
    protected fun attachToFragment(containerViewId: Int, fragment: Fragment){
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
*/




}