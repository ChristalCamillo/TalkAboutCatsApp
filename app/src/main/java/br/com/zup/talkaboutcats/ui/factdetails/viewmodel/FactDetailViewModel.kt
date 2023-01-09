package br.com.zup.talkaboutcats.ui.factdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.domain.usecase.CatFactsUseCase
import br.com.zup.talkaboutcats.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FactDetailViewModel(application: Application): AndroidViewModel(application) {
    private val catFactsUseCase = CatFactsUseCase(application)
    private val favoriteCatFactsState = MutableLiveData<ViewState<CatFactsResult>>()

    fun updateCatFactsFavorite(catfacts: CatFactsResult){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    catFactsUseCase.updateCatFactsFavorite(catfacts)
                }
                favoriteCatFactsState.value = response
            }catch (e: Exception){
                favoriteCatFactsState.value = ViewState.Error(Exception("Erro na favoritagem"))
            }
        }
    }
}