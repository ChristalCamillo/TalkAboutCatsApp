package br.com.zup.talkaboutcats.ui.factlist.viewmodel

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

class CatFactsListViewModel(application: Application): AndroidViewModel(application) {
    private val catFactsUseCase = CatFactsUseCase(application)
    val catFactsListState = MutableLiveData<ViewState<List<CatFactsResult>>>()

    fun getAllCatFactsNetwork(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    catFactsUseCase.getAllCatFactsNetwork()
                }
                catFactsListState.value = response
            }
            catch (ex: Exception){
                catFactsListState.value = ViewState.Error(Throwable("Não foi possível carregar a lista de fatos"))
            }
        }
    }
}