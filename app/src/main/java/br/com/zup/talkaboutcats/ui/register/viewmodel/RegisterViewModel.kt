package br.com.zup.talkaboutcats.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.talkaboutcats.domain.model.User
import br.com.zup.talkaboutcats.domain.repository.AuthenticationRepository
import br.com.zup.talkaboutcats.domain.repository.AuthenticationRepositoryFactory
import br.com.zup.talkaboutcats.utils.*

class RegisterViewModel : ViewModel() {
    private val authenticationRepository : AuthenticationRepository by lazy {
        AuthenticationRepositoryFactory.create()
    }

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    private fun validateDataUser(user: User): Boolean {
        when {
            user.name.isEmpty() -> {
                _errorState.value = NAME_ERROR_MESSAGE
                return false
            }
            user.name.length < 3 -> {
                _errorState.value = ERROR_VALIDATE_NAME
                return false
            }
            user.email.isEmpty() -> {
                _errorState.value = EMAIL_ERROR_MESSAGE
                return false
            }
            user.password.isEmpty() -> {
                _errorState.value = PASSWORD_ERROR_MESSAGE
                return false
            }
            user.password.length < 8 -> {
                _errorState.value = ERROR_VALIDATE_PASSWORD
                return false
            }
            user.email.matches(Regex(EMAIL_REGEX)).not() -> {
                _errorState.value = INVALID_EMAIL
                return false
            }
            else -> {
                return true
            }
        }
    }

    fun registerUser(user: User) {
    if(validateDataUser(user)){
    try {
        authenticationRepository.registerUser(
            user.email,
            user.password
        ).addOnSuccessListener {

            authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
                _registerState.value = user
            }

        }.addOnFailureListener {
            _errorState.value = REGISTER_ERROR_MESSAGE + it.message
        }
    } catch (ex: Exception) {
        _errorState.value = ex.message
    }
}
}
}