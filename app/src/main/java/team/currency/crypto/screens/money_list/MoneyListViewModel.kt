package team.currency.crypto.screens.money_list

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.data.entity.MoneyHolder
import team.currency.crypto.screens.currency_list.CurrencyListRepository
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class MoneyListViewModel @Inject
internal constructor(private val repository: MoneyListRepository) : ViewModel() {

    fun queryCurrencyList(): Observable<MoneyHolder>? {
        return    repository.queryCurrencyList()
    }

    fun queryCurrency(id:Long): Observable<CurrencyHolder>? {
        return    repository.queryCurrency(id)
    }



}

