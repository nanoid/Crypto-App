package team.currency.crypto.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 15.02.18.
 */
data class DataHolder(
        @SerializedName("currency") val data: ArrayList<Currency>
)

data class Currency(

        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("short_name") val shortName: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("cost") val cost: Float,
        @SerializedName("change_one_hour") val change_one_hour: Float,
        @SerializedName("change_twenty_four_hour") val change_twenty_four_hour: Float,
        @SerializedName("change_one_week") val change_one_week: Float,
        @SerializedName("modified") val modified: String,
        var type:Int=0

)

