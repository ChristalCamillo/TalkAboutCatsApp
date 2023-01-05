package br.com.zup.talkaboutcats.domain.repository

import br.com.zup.talkaboutcats.data.datasource.local.dao.CatFactsDAO
import br.com.zup.talkaboutcats.data.datasource.remote.RetrofitService
import br.com.zup.talkaboutcats.data.model.CatFactsResponse
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.domain.model.CatFacts

class CatFactsRepository(private val catFactsDAO: CatFactsDAO) {

    suspend fun getAllCatFacts(): List<CatFactsResult> = catFactsDAO.getAllCatFacts()

    suspend fun insertAllCatFactsDB(listCatFacts: List<CatFactsResult>) {
        catFactsDAO.insertAllCatFacts(listCatFacts)
    }

    suspend fun getAllCatFactsFavorited(): List<CatFactsResult> = catFactsDAO.getAllCatFactsFavorited()

    suspend fun updateCatFactsFavorited(catFacts: CatFactsResult){
        catFactsDAO.updateCatFactsFavorite(catFacts)
    }

    suspend fun geAllCatFactsNetwork(): CatFactsResponse {
        return RetrofitService.apiService.getAllCatFactsNetwork(10)
    }
}