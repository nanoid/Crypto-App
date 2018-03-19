package team.currency.crypto.screens.money_list

import io.reactivex.Observable
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.data.entity.MoneyHolder
import team.currency.crypto.db.mapper.CurrencyMapper
import team.currency.crypto.web.RetrofitInterface
import tech.iuic.iuicwork.db.AppDatabase
import toplib.util.LanguageHelper
import javax.inject.Inject

/**
 * Created by root on 21.02.18.
 */
class MoneyListRepository @Inject
internal constructor(private val retrofitInterface: RetrofitInterface,
                     private val languageHelper: LanguageHelper, private val mapper: CurrencyMapper) {


    var list: ArrayList<Currency> = ArrayList()


    fun queryCurrencyList(): Observable<MoneyHolder>? {
        list.clear()
        return retrofitInterface.getMoneyList()
    }


    fun queryCurrency(id:Long): Observable<CurrencyHolder>? {
        list.clear()
        return retrofitInterface.getCurrency(id)
    }


}