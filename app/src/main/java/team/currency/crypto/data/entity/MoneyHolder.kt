package team.currency.crypto.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 21.02.18.
 */
data class MoneyHolder(
        @SerializedName("currency") val data: ArrayList<CurrencyMoney>
)

data class CurrencyMoney(

        @SerializedName("id") val id: Long,
        @SerializedName("code") val code: String,
        @SerializedName("alphaCode") val alphaCode: String,
        @SerializedName("numericCode") val cost: Int,
        @SerializedName("name") val name: String,
        @SerializedName("rate") val rate: Float,
        @SerializedName("modified") val modified: String,
        var type:Int=0

)
