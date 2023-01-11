package br.com.zup.talkaboutcats.data.model


import com.google.gson.annotations.SerializedName


data class CatFactsResponse(
    @SerializedName("data")
    val `data`: List<String> = listOf()
)