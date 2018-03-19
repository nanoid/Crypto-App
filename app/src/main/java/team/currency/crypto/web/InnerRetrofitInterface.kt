package team.currency.crypto.web

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import team.currency.crypto.data.entity.ChartHolder
import team.currency.crypto.data.entity.CurrencyHolder
import team.currency.crypto.data.entity.DataHolder
import team.currency.crypto.data.entity.MoneyHolder

/**
 * Created by root on 21.02.18.
 */
interface InnerRetrofitInterface{




    @GET("data/histoday")
    abstract fun getCurrencyForChart(@Query("fsym") money:String,
                             @Query("tsym") crypto:String,
                             @Query("limit") limit:Int,
                             @Query("aggregate") aggregate:Int

    ): Observable<ChartHolder>


}