package team.currency.crypto.web

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.data.entity.MoneyHolder

/**
 * Created by root on 15.02.18.
 */
interface RetrofitInterface{



    @GET("api/currency/read.php")
    abstract fun getCurrencyList(): Observable<DataHolder>

    @GET("api/money_currency/read.php")
    abstract fun getMoneyList(): Observable<MoneyHolder>

    @GET("api/crypto/read.php")
    abstract fun getCurrency(@Query("id") id:Long): Observable<CurrencyHolder>

    @GET("api/favorite_currency/read.php")
    abstract fun getFavoriteCurrencyList(@Query("id") id:String): Observable<DataHolder>




}