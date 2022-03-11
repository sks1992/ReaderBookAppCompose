package sk.sandeep.readerappcompose.model


import com.google.gson.annotations.SerializedName

data class ListPriceX(
    @SerializedName("amountInMicros")
    val amountInMicros: Long,
    @SerializedName("currencyCode")
    val currencyCode: String
)