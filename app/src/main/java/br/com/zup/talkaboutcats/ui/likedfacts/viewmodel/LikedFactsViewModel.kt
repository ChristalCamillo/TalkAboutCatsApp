package br.com.zup.talkaboutcats.ui.likedfacts.viewmodel

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

class LikedFactsViewModel(application: Application): AndroidViewModel(application) {
    private val catfactsUseCase = CatFactsUseCase(application)
    val catfactsFavoriteState = MutableLiveData<ViewState<List<CatFactsResult>>>()

    fun getAllCatFactsFavorited(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    catfactsUseCase.getAllCatFactsFavorited()
                }
                catfactsFavoriteState.value = response
            }catch (e: Exception){
                catfactsFavoriteState.value = ViewState.Error(Exception("Erro na exibição da lista de fatos favoritados"))
            }
        }
    }
}