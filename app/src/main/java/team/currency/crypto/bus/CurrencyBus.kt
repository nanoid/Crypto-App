package team.currency.crypto.bus

import android.util.Log
import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by root on 20.02.18.
 */
class CurrencyBus{

     var id:Long=0

    fun send(o: Long) {
         id=o
    }

    fun get(): Long {
         return id
    }

}