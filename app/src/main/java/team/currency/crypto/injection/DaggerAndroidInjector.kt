package tech.iuic.iuicwork.injection


import android.os.Bundle

import android.app.Activity
import android.app.Application

import team.currency.crypto.App



/**
 * Created by nanoid3 on 01.11.2017.
 */
object DaggerAndroidInjector {

    fun initialize(app: App) {

        DaggerAppComponent.builder().application(app)
                .build().inject(app)

        app
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
                        //handleActivity(activity)
                    }

                    override fun onActivityStarted(activity: Activity) {

                    }

                    override fun onActivityResumed(activity: Activity) {

                    }

                    override fun onActivityPaused(activity: Activity) {

                    }

                    override fun onActivityStopped(activity: Activity) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                    }

                    override fun onActivityDestroyed(activity: Activity) {

                    }
                })
    }

 /*   private fun handleActivity(activity: Activity) {
        if (activity is DaggerInjectable) {
            AndroidInjection.inject(activity)
        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(fm: FragmentManager, f: Fragment,
                                                   savedInstanceState: Bundle) {

                        if (f is DaggerInjectable) {

                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true)
    }*/
}