package tech.iuic.iuicwork.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import team.currency.crypto.screens.currency.CurrencyViewModel
import team.currency.crypto.screens.currency_list.CurrencyListViewModel
import team.currency.crypto.screens.favorite_currency.FavoriteCurrencyListViewModel
import tech.iuic.iuicwork.base.ProjectViewModelFactory


/**
 * Created by nanoid3 on 30.10.2017.
 */
@Module
abstract class ViewModelModule {

    /**
     * Binds the MainViewModel into the dagger component hierarchy
     * */
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCurrencyListViewModel::class)
    abstract fun bindFavoriteCurrencyListViewModel(viewModel: FavoriteCurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    abstract fun bindCurrencyListViewModel(viewModel: CurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel



    /**
     * Provides the MyViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: ProjectViewModelFactory): ViewModelProvider.Factory

}