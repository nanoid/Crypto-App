package team.currency.crypto.screens.currency

import io.reactivex.Observable
import team.currency.crypto.data.entity.ChartHolder
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.db.mapper.CurrencyMapper
import team.currency.crypto.web.InnerRetrofitInterface
import team.currency.crypto.web.RetrofitInterface
import tech.iuic.iuicwork.db.AppDatabase
import toplib.util.LanguageHelper
import javax.inject.Inject

/**
 * Created by root on 20.02.18.
 */
class CurrencyRepository @Inject
internal constructor(private val retrofitInterface: RetrofitInterface,private val innerRetrofitInterface: InnerRetrofitInterface) {

    fun queryCurrency(id:Long): Observable<CurrencyHolder>? {

   return retrofitInterface.getCurrency(id)
    }

    fun queryCurrencyForGraph(shortName: String,limit:Int, aggregate:Int ): Observable<ChartHolder>? {
         return innerRetrofitInterface.getCurrencyForChart( shortName,"USD",limit,aggregate)
    }

}