package sk.sandeep.readerappcompose.ui.screens.login

data class LoadingSate(
    val status: Status,
    val message: String? = null
) {

    companion object {
        val IDLE = LoadingSate(Status.IDLE)
        val SUCCESS = LoadingSate(Status.SUCCESS)
        val FAILED = LoadingSate(Status.FAILED)
        val LOADING = LoadingSate(Status.LOADING)
    }

    enum class Status {
        SUCCESS,
        FAILED,
        LOADING,
        IDLE
    }
}
