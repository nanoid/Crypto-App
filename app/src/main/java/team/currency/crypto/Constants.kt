package team.currency.crypto

/**
 * Created by root on 15.02.18.
 */
object Constants {
    var BASE_URL = "http://1216504.vr289771.web.hosting-test.net/"
    var INNER_BASE_URL = "https://min-api.cryptocompare.com/"

    interface Login {
        companion object {
            val LIKE_CALLBACK = 1
            val DISLIKE_CALLBACK = 2
            val COMMENT_CALLBACK = 3
            val SUBSCRIBE_USER = 3
            val PROFILE_USER = 10
        }
    }


    object Requests {
        val GET_VERSION = "v1/versions"
        val FB_LOGIN = "auth/facebook/"
        val GET_LIST_CITY = "v1/cities"
        val GET_LIST_CATEGORY = "v1/categories"
        val GET_LIST_SUB_CATEGORY = "v1/categories"
        val GET_LIST_ORDER = "v1/vacancies"


    }

}