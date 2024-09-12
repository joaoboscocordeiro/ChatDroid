package com.joaobosco.chatdroid.ui.feature.signin

/**
 * Created by "João Bosco" on 12/09/2024.
 */
sealed interface SignInFormEvent {
    data class EmailChanged(val email: String) : SignInFormEvent
    data class PasswordChanged(val password: String) : SignInFormEvent
    data object Submit : SignInFormEvent
}