package br.com.zup.talkaboutcats.data.datasource.remote

import br.com.zup.talkaboutcats.data.model.CatFactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsAPI {
    @GET("/")
    suspend fun getAllCatFactsNetwork(
        @Query("count")
        count: Int
    ): CatFactsResponse
}