package com.plentina.codingchallengeplentinajcalma.util

/** This class is used for the loaders so the user won't have a blank page while the app loads data from API*/
data class LoadingState(val status: Status, val message: String?) {

    companion object {

        fun success(msg: String): LoadingState {
            return LoadingState(Status.SUCCESS, msg)
        }

        fun error(msg: String): LoadingState {
            return LoadingState(Status.ERROR, msg)
        }

        fun loading(): LoadingState {
            return LoadingState(Status.LOADING, null)
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}