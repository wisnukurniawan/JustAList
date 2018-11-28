package com.wisnu.justalist.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import io.reactivex.Observable

/**
 * Created by wisnu on 11/28/18
 */

fun <T> LiveData<T>.toObservable(lifecycleOwner: LifecycleOwner): Observable<T> {
    return io.reactivex.Observable.fromPublisher(
        LiveDataReactiveStreams.toPublisher(
            lifecycleOwner,
            this
        )
    )
}

fun <X, Y> LiveData<X>.map(transform: (x: X) -> Y): LiveData<Y> {
    return Transformations.map(this) {
        transform(it)
    }
}

fun <X, Y> LiveData<X>.switchMap(transform: (x: X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this) {
        transform(it)
    }
}