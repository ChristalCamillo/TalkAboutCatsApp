package br.com.zup.talkaboutcats.domain.repository

object AuthenticationRepositoryFactory {

    fun create(): AuthenticationRepository{
        return AuthenticationRepository()
    }
}