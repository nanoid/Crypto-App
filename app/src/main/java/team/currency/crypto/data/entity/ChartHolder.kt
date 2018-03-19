package team.currency.crypto.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 21.02.18.
 */
data class ChartHolder(
        @SerializedName("Data") val data: ArrayList<DataChart>
)

data class DataChart(

        @SerializedName("time") val time: Long,
        @SerializedName("high") val high: Float,
        @SerializedName("low") val low: Float,
        @SerializedName("open") val open: Float,
        @SerializedName("volumefrom") val volumefrom: Float,
        @SerializedName("volumeto") val volumeto: Float,
        @SerializedName("close") val close: Float,
        var type:Int=0

)