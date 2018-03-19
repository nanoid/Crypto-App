package team.currency.crypto.screens.favorite_currency

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.screens.currency_list.CurrencyListRepository
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */
class FavoriteCurrencyListViewModel @Inject
internal constructor(private val repository: FavoriteCurrencyListRepository) : ViewModel() {

    fun queryCurrencyList(): Observable<List<Currency>>? {
        return    repository.queryCurrencyList()
    }

}