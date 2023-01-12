package br.com.zup.talkaboutcats.domain.repository

import br.com.zup.talkaboutcats.data.datasource.local.dao.CatFactsDAO
import br.com.zup.talkaboutcats.data.datasource.remote.RetrofitService
import br.com.zup.talkaboutcats.data.model.CatFactsResult

class CatFactsRepository(private val catFactsDAO: CatFactsDAO) {

    fun getAllCatFactsDB(): List<CatFactsResult> = catFactsDAO.getAllCatFacts()

    fun insertAllCatFactsDB(listCatFacts: List<CatFactsResult>) {
        catFactsDAO.insertAllCatFacts(listCatFacts)
    }

    fun getAllCatFactsFavorited(): List<CatFactsResult> = catFactsDAO.getAllCatFactsFavorited()

    fun updateCatFactsFavorited(catFacts: CatFactsResult){
        catFactsDAO.updateCatFactsFavorite(catFacts)
    }

    fun insertCatFactFavorited(catFact: CatFactsResult) : CatFactsResult{
        catFact.id = catFactsDAO.insertCatFact(catFact).toInt()
        return catFact
    }

    suspend fun geAllCatFactsNetwork(): MutableList<CatFactsResult> {
       var apiReturn = RetrofitService.apiService.getAllCatFactsNetwork(10)
        return createCatFactList(apiReturn.data)
    }

    fun createCatFactList(listCatFactsAPI: List<String>): MutableList<CatFactsResult>{

        var listCatFactsROOM = getAllCatFactsDB()

        var listCatFacts = mutableListOf<CatFactsResult>()


        for(apiItem in listCatFactsAPI){
            var duplicate = false
            for(databaseItem in listCatFactsROOM){
                if(apiItem.equals(databaseItem.catfact)){
                    duplicate = true
                    listCatFacts.add(databaseItem)
                }else{
                    duplicate = false
                }
            }
            if(!duplicate){
                var newCatFact = CatFactsResult(
                    id=null,
                    catfact = apiItem,
                    isFavorite = false
                )
                listCatFacts.add(newCatFact)
            }
        }
        return listCatFacts
    }
}