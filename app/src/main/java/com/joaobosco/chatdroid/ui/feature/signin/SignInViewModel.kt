package com.joaobosco.chatdroid.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.data.repository.AuthRepository
import com.joaobosco.chatdroid.model.NetworkException.ApiException
import com.joaobosco.chatdroid.ui.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by "João Bosco" on 11/09/2024.
 */

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val formValidator: FormValidator<SignInFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    private val _signInActionFlow = MutableSharedFlow<SignInAction>()
    val signInActionFlow = _signInActionFlow.asSharedFlow()

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
        if (formState.email.isBlank()) {
            formState = formState.copy(emailError = R.string.error_message_email_invalid)
            isValidForm()
        }

        if (formState.password.isBlank()) {
            formState = formState.copy(passwordError = R.string.error_message_password_invalid)
            isValidForm()
        }

        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                authRepository.signIn(
                    username = formState.email,
                    password = formState.password
                ).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)

                        _signInActionFlow.emit(SignInAction.Success)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)

                        val error = if (it is ApiException && it.statusCode == 401) {
                            SignInAction.Error.UnauthorizedError
                        } else {
                            SignInAction.Error.GenericError
                        }
                        _signInActionFlow.emit(error)
                    }
                )
            }
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

    sealed interface SignInAction {
        data object Success : SignInAction
        sealed interface Error : SignInAction {
            data object GenericError : Error
            data object UnauthorizedError : Error
        }
    }
}