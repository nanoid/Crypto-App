package tech.iuic.iuicwork.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import tech.iuic.iuicwork.db.entity.CurrencyEntity


/**
 * Created by nanoid3 on 17.11.2017.
 */
@Dao
interface CurrencyDao {
   /* @Query("SELECT * FROM cities where cityId = :cityId")
    fun loadCitites(cityId: Int): LiveData<List<CityEntity>>*/

    @Query("SELECT * FROM currency where id = :id")
    fun loadCurrenciesSync(id: Long): CurrencyEntity

    @Query("SELECT * FROM currency")
    fun loadCurrencies(): List<CurrencyEntity>

    @Insert(onConflict = REPLACE)
    fun insertAll(currencies: List<CurrencyEntity>)


    @Insert(onConflict = REPLACE)
    fun insert(currencies: CurrencyEntity)


    @Query("DELETE  FROM currency")
    fun deleteFrom()

}