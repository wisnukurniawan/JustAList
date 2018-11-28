package com.wisnu.justalist.data.remote.model

/**
 * Created by wisnu on 11/28/18
 */
sealed class RemoteError(
    throwable: Throwable? = null,
    override val message: String? = null,
    open val errorCode: String? = null
) : RuntimeException(throwable)

class NetworkError(throwable: Throwable) : RemoteError(throwable)

class HttpError(
    val code: Int,
    override val message: String,
    override val errorCode: String = ""
) : RemoteError(message = message, errorCode = errorCode) {

    fun isServerError(): Boolean {
        return code in 500..599
    }
}
