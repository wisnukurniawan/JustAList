package com.wisnu.justalist.base.data

import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by wisnu on 11/28/18
 */
interface ReactiveStore<K, V> {
    fun getSingle(key: K): Observable<V>
    fun storeSingle(value: V): Completable
    fun getAll(): Observable<List<V>>
    fun storeAll(values: List<V>): Completable
    fun replaceAll(values: List<V>): Completable
}