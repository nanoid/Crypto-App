package team.currency.crypto.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import team.currency.crypto.data.entity.Currency


/**
 * Created by root on 22.02.18.
 */
class CurrencyConverterBus{

   private  val currency = PublishSubject.create<Currency>()

    fun send(o: Currency) {
        currency.onNext(o)
    }

    fun toObservable(): Observable<Currency> {
        return currency
    }


}