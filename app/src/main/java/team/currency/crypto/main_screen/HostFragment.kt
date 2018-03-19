package team.currency.crypto.main_screen

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
 import kotlinx.android.synthetic.main.host_fragment.*
import team.currency.crypto.Prefs
import team.currency.crypto.R
import team.currency.crypto.screens.converter.ConverterFragment
import team.currency.crypto.screens.currency_list.CurrencyListFragment
import team.currency.crypto.screens.favorite_currency.FavoriteCurrencyListFragment
import team.currency.crypto.screens.settings.SettingsFragment
import tech.iuic.iuicwork.base.BaseFragment
import toplib.util.LanguageHelper
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */
class HostFragment : BaseFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }



    @Inject
    lateinit var prefs: Prefs

     @Inject
    lateinit var languageHelper: LanguageHelper




    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                addFragment(R.id.fragment_container_fragment,FavoriteCurrencyListFragment())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                addFragment(R.id.fragment_container_fragment,ConverterFragment())
                 return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                addFragment(R.id.fragment_container_fragment, SettingsFragment())
                 return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        addFragment(R.id.fragment_container_fragment,FavoriteCurrencyListFragment())

          Log.e("language", languageHelper.getLanguage())
     }




    protected override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_fragment, container, false)
    }



}
