package team.currency.crypto.screens.currency

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import team.currency.crypto.data.entity.ChartHolder
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.screens.currency_list.CurrencyListRepository
import javax.inject.Inject

/**
 * Created by root on 20.02.18.
 */
class CurrencyViewModel @Inject
internal constructor(private val repository: CurrencyRepository) : ViewModel() {

    fun queryCurrencyById(id:Long): Observable<CurrencyHolder>? {
        return    repository.queryCurrency(id)
    }

    fun queryCurrencyForGraph(shortName: String,limit:Int, aggregate:Int ): Observable<ChartHolder>? {

        return    repository.queryCurrencyForGraph(shortName,limit,aggregate)

    }


}
