package sample.nackun.com.studyfirst.data.model

import com.google.gson.annotations.SerializedName

data class UpbitMarket(
    @SerializedName("market")
    val market: String
)