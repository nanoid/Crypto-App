package tech.iuic.iuicwork.injection

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
 import javax.inject.Singleton
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import team.currency.crypto.Constants
import team.currency.crypto.Prefs
import team.currency.crypto.bus.*
import team.currency.crypto.db.mapper.CurrencyMapper
import team.currency.crypto.web.InnerRetrofitInterface
import team.currency.crypto.web.RetrofitInterface
 import tech.iuic.iuicwork.db.AppDatabase
 import toplib.util.LanguageHelper
import toplib.view.RecyclerViewFn


@Module(includes = arrayOf(ViewModelModule::class))
internal class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(application: Application): Context {
        return application
    }





    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        var appDatabase = Room.databaseBuilder(application,
                AppDatabase::class.java, "mvvm-database1").build()

        return appDatabase
    }


    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): RetrofitInterface {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)
        val gson = GsonBuilder()
                /*    .registerTypeAdapterFactory(
                        new EmptyCheckTypeAdapterFactory())
            */.serializeNulls()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              //  .client(httpClient.build())
                .build()

        return retrofit.create(RetrofitInterface::class.java!!)
    }




    @Provides
    @Singleton
    internal fun provideInnerRetrofitInterface(): InnerRetrofitInterface {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)
        val gson = GsonBuilder()
                /*    .registerTypeAdapterFactory(
                        new EmptyCheckTypeAdapterFactory())
            */.serializeNulls()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.INNER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        return retrofit.create(InnerRetrofitInterface ::class.java!!)
    }





    @Singleton
    @Provides
    internal fun provideLanguageHelper(): LanguageHelper {
        return LanguageHelper()
    }


    @Singleton
    @Provides
    internal fun providePrefs(): Prefs {
        return Prefs()
    }

    @Singleton
    @Provides
    internal fun provideRecyclerViewFn(): RecyclerViewFn {
        return RecyclerViewFn()
    }

    @Singleton
    @Provides
    internal fun provideCurrencyMapper(): CurrencyMapper {
        return CurrencyMapper()
    }



    @Singleton
    @Provides
    internal fun provideCurrencyBus(): CurrencyBus {
        return CurrencyBus()

    }

    @Singleton
    @Provides
    internal fun provideMoneyBus(): MoneyBus {
        return MoneyBus()

    }




    @Singleton
    @Provides
    internal fun provideCurrencyListBus(): CurrencyListBus{
        return CurrencyListBus()
    }

    @Singleton
    @Provides
    internal fun provideCurrencyConverterBus(): CurrencyConverterBus{
        return CurrencyConverterBus()
    }



    @Singleton
    @Provides
    internal fun provideFavoriteRefreshBus(): FavoriteRefreshBus{
        return FavoriteRefreshBus()
    }


}