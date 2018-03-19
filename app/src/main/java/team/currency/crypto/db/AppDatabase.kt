package tech.iuic.iuicwork.db

//import android.support.test.espresso.core.internal.deps.guava.util.concurrent.Futures.addCallback

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import tech.iuic.iuicwork.db.dao.CurrencyDao
import tech.iuic.iuicwork.db.entity.CurrencyEntity


/**
 * Created by nanoid3 on 17.11.2017.
 */
@Database(entities = arrayOf(CurrencyEntity::class), version = 1)
//@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
/*

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    val databaseCreated: LiveData<Boolean>
        get() = mIsDatabaseCreated
*/

    abstract fun currencyDao(): CurrencyDao

   /* *//**
     * Check whether the database already exists and expose it via [.getDatabaseCreated]
     *//*
    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }
*/
    /*private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }*/

 /*   companion object {

        private var sInstance: AppDatabase? = null

        @VisibleForTesting
        val DATABASE_NAME = "basic-sample-db"

        fun getInstance(context: Context, executors: AppExecutors): AppDatabase? {
            if (sInstance == null) {
                synchronized(AppDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.getApplicationContext(), executors)
                        sInstance!!.updateDatabaseCreated(context.getApplicationContext())
                    }
                }
            }
            return sInstance
        }

        *//**
         * Build the database. [Builder.build] only sets up the database configuration and
         * creates a new instance of the database.
         * The SQLite database is only created when it's accessed for the first time.
         *//*
   *//*     private fun buildDatabase(appContext: Context,
                                  executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executors.diskIO().execute({
                                // Add a delay to simulate a long-running operation
                                addDelay()
                                // Generate the data for pre-population
                                val database = AppDatabase.getInstance(appContext, executors)
                                val products = DataGenerator.generateProducts()
                                val comments = DataGenerator.generateCommentsForProducts(products)

                                insertData(database, products)
                                // notify that the database was created and it's ready to be used
                                database.setDatabaseCreated()
                            })
                        }
                    }).build()
        }

        private fun insertData(database: AppDatabase, products: List<CityEntity>) {
            database.runInTransaction {
                database.productDao().insertAll(products)
             }
        }*//*

        private fun addDelay() {
            try {
                Thread.sleep(4000)
            } catch (ignored: InterruptedException) {
            }

        }
    }*/
}