package com.joaobosco.chatdroid.ui.feature.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.ui.validator.FormValidator

/**
 * Created by "João Bosco" on 04/12/2024.
 */
class SignUpViewModel(
    private val formValidator: FormValidator<SignUpFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }

            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }

            is SignUpFormEvent.LestNameChanged -> {
                formState = formState.copy(lastName = event.lestName)
            }

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                updatePasswordExtraText()
            }

            is SignUpFormEvent.PasswordConfirmationChanged -> {
                formState = formState.copy(passwordConfirmation = event.passwordConfirmation)
                updatePasswordExtraText()
            }

            SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }

            SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }

            SignUpFormEvent.Submit -> {
                doSignUp()
            }
        }
    }

    private fun updatePasswordExtraText() {
        formState = formState.copy(
            passwordExtraText = if (formState.password.isNotEmpty()
                && formState.password == formState.passwordConfirmation
            ) {
                R.string.feature_sign_up_passwords_match
            } else null
        )
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            // Request to API
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }
}