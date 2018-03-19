package team.currency.crypto.screens.currency_list

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.DataHolder
import javax.inject.Inject

/**
 * Created by nanoid3 on 08.02.2018.
 */
class CurrencyListViewModel @Inject
internal constructor(private val repository: CurrencyListRepository) : ViewModel() {

    fun queryCurrencyList(): Observable<DataHolder>? {
        return    repository.queryCurrencyList()
    }


    fun addToFoavorite(currency:Currency): Observable<String> {
        return    repository.addToFoavorite(currency)
    }

    fun addToFoavoriteList(currency:ArrayList<Currency>): Observable<String> {
        return    repository.addToFoavoriteList(currency)
    }


}

