package team.currency.crypto.bus

/**
 * Created by root on 22.02.18.
 */
class CurrencyListBus{

    var type:Int=0

    fun send(o: Int) {
        type=o
    }

    fun get(): Int {
        return type
    }



}