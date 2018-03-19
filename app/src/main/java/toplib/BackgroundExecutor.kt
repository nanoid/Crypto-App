package toplib

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS
import android.os.AsyncTask.THREAD_POOL_EXECUTOR





/**
 * Created by nanoid3 on 04.12.2017.
 */
object BackgroundExecutor {

    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
    private val CORE_POOL_SIZE = CPU_COUNT + 1
    private val MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1
    private val KEEP_ALIVE = 1

    private val sPoolWorkQueue:LinkedBlockingQueue<Runnable> = LinkedBlockingQueue(128)

    /**
     * An [Executor] that can be used to execute tasks in parallel.
     */
    var THREAD_POOL_EXECUTOR: Executor = ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE.toLong(),TimeUnit.SECONDS, sPoolWorkQueue)



    fun getSafeBackgroundExecutor(): Executor {
        return THREAD_POOL_EXECUTOR
    }


    fun <T> createSafeBackgroundObservable(f: ObservableOnSubscribe<T>): Observable<T> {
        return Observable.create(f).subscribeOn(Schedulers.from(THREAD_POOL_EXECUTOR))
    }

}