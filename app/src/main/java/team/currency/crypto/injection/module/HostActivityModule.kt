package tech.iuic.iuicwork.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import team.currency.crypto.main_screen.HostFragment
import team.currency.crypto.screens.converter.ConverterFragment
import team.currency.crypto.screens.currency.CurrencyFragment
import team.currency.crypto.screens.currency_list.CurrencyListFragment
import team.currency.crypto.screens.currency_list.CurrencyListViewModel
import team.currency.crypto.screens.favorite_currency.FavoriteCurrencyListFragment
import team.currency.crypto.screens.money_list.MoneyListFragment
import team.currency.crypto.screens.money_list.adapter.MoneyListAdapter
import team.currency.crypto.screens.settings.SettingsFragment


/**
 * Created by nanoid3 on 06.11.2017.
 */
@Module
abstract class HostActivityModule{

    @ContributesAndroidInjector
    internal abstract fun hostFragment(): HostFragment

    @ContributesAndroidInjector
    internal abstract fun currencyListFragment(): CurrencyListFragment

    @ContributesAndroidInjector
    internal abstract fun favoriteCurrencyListFragment(): FavoriteCurrencyListFragment

    @ContributesAndroidInjector
    internal abstract fun favoriteCurrencyFragment(): CurrencyFragment


    @ContributesAndroidInjector
    internal abstract fun favoriteConverterFragment(): ConverterFragment

    @ContributesAndroidInjector
    internal abstract fun favoriteSettingsFragment(): SettingsFragment




}