package sample.nackun.com.studyfirst.data.model

import com.google.gson.annotations.SerializedName

data class BithumbResult(
    @SerializedName("data")
    val bithumbData: Map<String, Any>,
    @SerializedName("status")
    val status: String
)