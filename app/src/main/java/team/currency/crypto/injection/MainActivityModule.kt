package tech.iuic.iuicwork.injection


import dagger.Module
import dagger.android.ContributesAndroidInjector
import team.currency.crypto.injection.module.CurrencyActivityModule
import team.currency.crypto.injection.module.CurrencyListActivityModule
import team.currency.crypto.injection.module.MoneyListActivityModule
import team.currency.crypto.main_screen.HostActivity
import team.currency.crypto.screens.currency.CurrencyActivity
import team.currency.crypto.screens.currency_list.CurrencyListActivity
import team.currency.crypto.screens.money_list.MoneyListActivity
import tech.iuic.iuicwork.injection.module.HostActivityModule


/**
 * Created by nanoid3 on 30.10.2017.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules= arrayOf(HostActivityModule::class))
    internal abstract fun contributeHostActivity(): HostActivity

    @ContributesAndroidInjector(modules= arrayOf(MoneyListActivityModule::class))
    internal abstract fun contributeMoneyListActivity(): MoneyListActivity


    @ContributesAndroidInjector(modules= arrayOf(CurrencyActivityModule::class))
    internal abstract fun contributeCurrencyActivity(): CurrencyActivity

    @ContributesAndroidInjector(modules= arrayOf(CurrencyListActivityModule::class))
    internal abstract fun contributeCurrencyListActivity(): CurrencyListActivity




}