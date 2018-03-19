package team.currency.crypto

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import tech.iuic.iuicwork.db.AppDatabase
import tech.iuic.iuicwork.injection.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by root on 15.02.18.
 */

class App : Application(), HasActivityInjector {



    private lateinit var appDatabase: AppDatabase
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
         //Instantiate Dagger
        Fabric.with(this, Crashlytics())
        //DaggerAndroidInjector.initialize(this);

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        /*   appDatabase = Room.databaseBuilder(applicationContext,
                   AppDatabase::class.java, "mvvm-database").build()*/
        //Register activity lifeCycle callback listener for automatically injecting every activity
        //that launches


        /*   registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
               override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                   p0?.let { AndroidInjection.inject(p0)
                       //handleActivity(p0)
                   }
               }
           })*/


    }


    /*abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {

        }


    }
*/

}