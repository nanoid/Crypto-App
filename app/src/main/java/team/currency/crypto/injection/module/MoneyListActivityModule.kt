package team.currency.crypto.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import team.currency.crypto.screens.money_list.MoneyListFragment

/**
 * Created by root on 21.02.18.
 */

@Module
abstract class MoneyListActivityModule{

    @ContributesAndroidInjector
    internal abstract fun moneyListFragment(): MoneyListFragment


}