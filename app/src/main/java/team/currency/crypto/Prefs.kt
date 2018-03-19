package team.currency.crypto

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by root on 15.02.18.
 */
class Prefs {
    private val mContext: Context? = null


    private val TAG = Prefs::class.java.simpleName

    private val PREFERENCES_NAME = "development_preferences"
    private val PREFERENCES_FIRST_START = "first_start"
    private val PREFERENCES_FIRST_VERSION = "cities_version"
    private val PREFERENCES_FIRST_NAME = "first_name"
    private val PREFERENCES_LAST_NAME = "last_name"
    private val PREFERENCES_USER_PHOTO_LINK = "user_link"
    private val PREFERENCES_USER_ID = "user_id"
    private val PREFERENCES_SOCIAL_NETWORK = "social_network"
    private val PREFERENCES_SOCIAL_TOKEN = "social_token"
    private val PREFERENCES_CALLBACK_LOGIN = "callback_login"
    private val PREFERENCES_FCM_TOKEN = "fcm_token"
    private val PREFERENCES_ACCES_TOKEN = "acces_token"
    private val PREFERENCES_APP_TOKEN = "app_token"
    private val PREFERENCES_TOKEN_TYPE = "token_type"
    private val PREFERENCES_CLIENT= "client"
    private val PREFERENCES_UID= "uid"
    private val PREFERENCES_VERSION= "version"

    private var registrationEditor: SharedPreferences.Editor? = null

    fun setFirstStart(context: Context, firstStart: Boolean) {
        registrationEditor = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        registrationEditor!!.putBoolean(PREFERENCES_FIRST_START, firstStart)
        registrationEditor!!.commit()
    }


    fun getFirstStart(context: Context): Boolean {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(PREFERENCES_FIRST_START, false)
    }


    fun setCitiesVersion(context: Context, version: Int) {
        registrationEditor = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        registrationEditor!!.putInt(PREFERENCES_FIRST_VERSION, version)
        registrationEditor!!.commit()
    }


    fun getCitiesVersion(context: Context): Int {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(PREFERENCES_FIRST_VERSION, 0)
    }


}
