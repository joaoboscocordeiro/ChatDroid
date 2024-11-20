package com.joaobosco.chatdroid.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.joaobosco.chatdroid.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by "João Bosco" on 11/09/2024.
 */

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            SignInFormEvent.Submit -> {
                doSignIn()
            }
        }
    }

    private fun doSignIn() {
        var isFormValid = true
        if (formState.email.isBlank()) {
            formState = formState.copy(emailError = R.string.error_message_email_invalid)
            isFormValid = false
        }

        if (formState.password.isBlank()) {
            formState = formState.copy(passwordError = R.string.error_message_password_invalid)
            isFormValid = false
        }

        if (isFormValid) {
            formState = formState.copy(isLoading = true)
            // Request to api
        }
    }

    /*private fun resetFormErrorState() {
        formState = formState.copy(
            emailError = null,
            passwordError = null
        )
    }*/
}