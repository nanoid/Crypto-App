package team.currency.crypto.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import team.currency.crypto.data.entity.Currency

/**
 * Created by root on 27.02.18.
 */
class FavoriteRefreshBus{

    private  val currency = PublishSubject.create<Int>()

    fun send(o: Int) {
        currency.onNext(o)
    }

    fun toObservable(): Observable<Int> {
        return currency
    }

}