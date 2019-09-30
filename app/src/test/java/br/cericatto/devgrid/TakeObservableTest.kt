package br.cericatto.devgrid

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Test
import timber.log.Timber

/**
 * TakeObservableTest.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
class TakeObservableTest {

    private var mList : List<Int> = mutableListOf(1, 2, 3, 4, 5)
    lateinit var mObservableList : MutableList<Int>

    @Test
    fun testTakeObservable() {
        val subscription = getObservable()
            // Run on a background thread
            .subscribeOn(Schedulers.io())
            // Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .take(AppConfiguration.COMMITS_NUMBER.toLong())
            .subscribe(
                {
                    mObservableList.add(it)
                },
                {
                    Timber.e("ObservableActivity -> Error: %s", it.message)
                },
                // OnCompleted
                {
                    assertEquals(mList.take(AppConfiguration.COMMITS_NUMBER), mObservableList)
                }
            )
        val composite = CompositeDisposable()
        composite.add(subscription)
    }

    private fun getObservable(): Observable<List<Int>> {
        return Observable.just(mList)
    }
}