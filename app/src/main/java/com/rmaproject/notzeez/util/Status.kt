package com.rmaproject.notzeez.util

sealed class Status<T>(val data: T?= null, val message: String?= null) {
    class Success<T>(data: T?): Status<T>(data)
    class Loading<T>(data: T? = null) : Status<T>(data)
    class Error<T>(message: String, data: T? = null) : Status<T>(data, message = message)
}

inline fun <T> safeCall(action: () -> Status<T>): Status<T> {
    return try {
        action()
    } catch (e:Exception) {
        Status.Error(e.message?: "An unknown Error")
    }
}