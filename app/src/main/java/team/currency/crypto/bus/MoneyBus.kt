package team.currency.crypto.bus

import android.util.Log
import rx.Observable
import rx.subjects.PublishSubject
import team.currency.crypto.data.entity.CurrencyMoney

/**
 * Created by root on 21.02.18.
 */
class MoneyBus{
    private val bus = PublishSubject.create<CurrencyMoney>()

    fun send(o: CurrencyMoney) {
        Log.e("onNext","onNext")
        bus.onNext(o)
    }

    fun toObservable(): Observable<CurrencyMoney> {
        Log.e("bus","bus")
        return bus
    }

}