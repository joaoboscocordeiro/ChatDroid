package com.joaobosco.chatdroid.ui.feature.signup

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.data.repository.AuthRepository
import com.joaobosco.chatdroid.model.CreateAccount
import com.joaobosco.chatdroid.model.NetworkException
import com.joaobosco.chatdroid.ui.validator.FormValidator
import com.joaobosco.chatdroid.util.image.ImageCompressor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by "João Bosco" on 04/12/2024.
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpFormState>,
    private val authRepository: AuthRepository,
    private val imageCompressor: ImageCompressor
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
                event.uri?.let { compressImageAndUpdateState(it) }
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

    private fun compressImageAndUpdateState(uri: Uri) {
        viewModelScope.launch {
            try {
                formState = formState.copy(isCompressingImage = false)
                val compressFile = imageCompressor.compressAndResizeImage(uri)
                formState = formState.copy(profilePictureUri = compressFile.toUri())
            } catch (e: Exception) {
                // Log error
            } finally {
                formState = formState.copy(isCompressingImage = false)
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
            viewModelScope.launch {
                var profilePictureId: Int? = null
                var errorWhenUploadingProfilePicture = false

                formState.profilePictureUri?.path?.let { path ->
                    authRepository.uploadProfilePicture(path).fold(
                        onSuccess = { image ->
                            profilePictureId = image.id
                        },
                        onFailure = {
                            formState = formState.copy(
                                isLoading = false,
                                profilePictureUri = null,
                                apiErrorMessageResId = R.string.error_message_profile_picture_uploading_failed
                            )
                            errorWhenUploadingProfilePicture = true
                        }
                    )
                }

                if (errorWhenUploadingProfilePicture) {
                    return@launch
                }

                authRepository.signUp(
                    createAccount = CreateAccount(
                        username = formState.email,
                        password = formState.password,
                        firstName = formState.firstName,
                        lastName = formState.lastName,
                        profilePictureId = null
                    )
                ).fold(
                    onSuccess = {
                        formState = formState.copy(
                            isLoading = false,
                            isSignedUp = true
                        )
                    },
                    onFailure = {
                        formState = formState.copy(
                            isLoading = false,
                            apiErrorMessageResId = if (it is NetworkException.ApiException) {
                                when (it.statusCode) {
                                    400 -> R.string.error_message_api_form_validation_failed
                                    409 -> R.string.error_message_user_with_username_already_exists
                                    else -> R.string.common_generic_error_message
                                }
                            } else R.string.common_generic_error_message
                        )
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

    fun successMessageShown() {
        formState = formState.copy(isSignedUp = false)
    }

    fun errorMessageShown() {
        formState = formState.copy(apiErrorMessageResId = null)
    }
}