package com.joaobosco.chatdroid.ui.feature.signin

/**
 * Created by "João Bosco" on 12/09/2024.
 */

data class SignInFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false
)
