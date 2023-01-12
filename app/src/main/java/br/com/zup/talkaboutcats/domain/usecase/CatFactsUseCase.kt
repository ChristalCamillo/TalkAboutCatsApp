package br.com.zup.talkaboutcats.domain.usecase

import android.app.Application
import br.com.zup.talkaboutcats.data.datasource.local.database.CatFactsDatabase
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.domain.repository.CatFactsRepository
import br.com.zup.talkaboutcats.ui.viewstate.ViewState

class CatFactsUseCase(application: Application) {
    private val catFactsDAO = CatFactsDatabase.getDatabase(application).catFactsDao()
    private val catFactsRepository = CatFactsRepository(catFactsDAO)

    private fun getAllCatFacts(): ViewState<List<CatFactsResult>> {
        return try {
            val catfacts = catFactsRepository.getAllCatFactsDB()
            ViewState.Success(catfacts)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de fatos de gatos do Banco de Dados!"))
        }
    }

    fun getAllCatFactsFavorited(): ViewState<List<CatFactsResult>> {
        return try {
            val catfacts = catFactsRepository.getAllCatFactsFavorited()
            ViewState.Success(catfacts)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de fatos favoritos!"))

        }
    }

    fun updateCatFactsFavorite(catfacts: CatFactsResult): ViewState<CatFactsResult> {
        return try {
            catFactsRepository.updateCatFactsFavorited(catfacts)
            ViewState.Success(catfacts)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível atualizar o fato favoritado!"))
        }
    }

    fun insertCatFactFavorite(catfact: CatFactsResult): ViewState<CatFactsResult> {
        return try {
            catFactsRepository.insertCatFactFavorited(catfact)
            ViewState.Success(catfact)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível atualizar o fato favoritado!"))
        }
    }

    suspend fun getAllCatFactsNetwork(): ViewState<List<CatFactsResult>> {
        return try {
            val response = catFactsRepository.geAllCatFactsNetwork()
            //catFactsRepository.insertAllCatFactsDB(response.data)
            ViewState.Success(response)
        } catch (ex: Exception) {
            getAllCatFacts()
        }
    }
}