package team.currency.crypto.screens.currency_list


import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import  toplib.util.LanguageHelper

import team.currency.crypto.data.entity.Currency
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.db.mapper.CurrencyMapper
import team.currency.crypto.web.RetrofitInterface
import tech.iuic.iuicwork.db.AppDatabase
import tech.iuic.iuicwork.db.AppDatabase_Impl
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */
class CurrencyListRepository @Inject
internal constructor(private val retrofitInterface: RetrofitInterface,
                     private val languageHelper: LanguageHelper, private val dataBase: AppDatabase, private val mapper: CurrencyMapper) {


    var list: ArrayList<Currency> = ArrayList()


    fun queryCurrencyList(): Observable<DataHolder>? {
        list.clear()
        return retrofitInterface.getCurrencyList()
    }

    fun addToFoavorite(currency: Currency): Observable<String> {
      return   Observable.just(dataBase).flatMap{ v->
          try {
              dataBase.currencyDao().insert(mapper.currencyDbFromResponse(currency))
          }catch (e:Exception){
              Observable.just("error")
          }
        Observable.just("ok")
      }
    }

    fun addToFoavoriteList(currency: ArrayList<Currency>): Observable<String> {
        return   Observable.just(dataBase).flatMap{ v->
            try {
           for(item  in currency){
               if(item.type==1){
             Log.e("yes","yes")
               dataBase.currencyDao().insert(mapper.currencyDbFromResponse(item))}
       }
            }catch (e:Exception){
                Observable.just("error")
            }
            Observable.just("ok")
        }
     }

}