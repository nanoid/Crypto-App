package team.currency.crypto.screens.favorite_currency

import io.reactivex.Observable
import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.db.mapper.CurrencyMapper
import team.currency.crypto.web.RetrofitInterface
import tech.iuic.iuicwork.db.AppDatabase
import toplib.util.LanguageHelper
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */
class FavoriteCurrencyListRepository @Inject
internal constructor(private val retrofitInterface: RetrofitInterface,
                     private val languageHelper: LanguageHelper, private val dataBase: AppDatabase, private val mapper: CurrencyMapper) {


    var list: ArrayList<Currency> = ArrayList()


    fun queryCurrencyList(): Observable<List<Currency>>? {

        list.clear()
       var idList:String=""

        return Observable.just(dataBase).flatMap{ v ->
            dataBase.currencyDao().loadCurrencies()
            for (item in dataBase.currencyDao().loadCurrencies()) {
                idList = idList+ item.idCurrency.toString()+","
            //    list.add(mapper.currencyFromDb(item))
            }
            
          if(idList.length>0){
            retrofitInterface.getFavoriteCurrencyList( idList.substring(0, idList.length - 1)).subscribe { v ->
                list = v.data
            }
          }
            Observable.just(list)
        }
    }

}