package tech.iuic.iuicwork.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import tech.iuic.iuicwork.db.model.CurrencyDb


/**
 * Created by nanoid3 on 17.11.2017.
 */
@Entity(tableName = "currency")
class CurrencyEntity: CurrencyDb {

    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0
    override var idCurrency: Long = 0
    override var name: String=""
    override  var shortName: String=""
    override  var imageUrl: String=""
    override   var cost: Float = 0F
    override   var change_one_hour: Float = 0F
    override   var change_twenty_four_hour: Float = 0F
    override   var change_one_week: Float = 0F
    override   var modified: String =""
}
    /*  constructor(id: Int, productId: Int, text: String, postedAt: Date) {
          this.id = id
          this.productId = productId
          this.text = text
          this.postedAt = postedAt
      }
  */
