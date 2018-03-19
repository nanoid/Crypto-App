package tech.iuic.iuicwork.db.model

/**
 * Created by nanoid3 on 17.11.2017.
 */
interface CurrencyDb {
     val id: Long
    val idCurrency:Long
    val name: String
    val shortName: String
     val imageUrl: String
     val cost: Float
     val change_one_hour: Float
    val change_twenty_four_hour: Float
     val change_one_week: Float
     val modified: String
}