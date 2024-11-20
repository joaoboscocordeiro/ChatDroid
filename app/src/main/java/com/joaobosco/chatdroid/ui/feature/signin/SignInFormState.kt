package com.joaobosco.chatdroid.ui.feature.signin

import androidx.annotation.StringRes

/**
 * Created by "João Bosco" on 12/09/2024.
 */

data class SignInFormState(
    val email: String = "",
    @StringRes
    val emailError: Int? = null,
    val password: String = "",
    @StringRes
    val passwordError: Int? = null,
    val isLoading: Boolean = false
)
