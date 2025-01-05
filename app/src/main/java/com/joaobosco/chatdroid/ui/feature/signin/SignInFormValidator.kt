package com.joaobosco.chatdroid.ui.feature.signin

import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.ui.validator.EmailValidator
import com.joaobosco.chatdroid.ui.validator.FormValidator
import com.joaobosco.chatdroid.ui.validator.PasswordValidator
import javax.inject.Inject

/**
 * Created by "João Bosco" on 06/12/2024.
 */

class SignInFormValidator @Inject constructor() : FormValidator<SignInFormState> {
    override fun validate(formState: SignInFormState): SignInFormState {
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)

        val hasError = listOf(
            isEmailValid,
            isPasswordValid
        ).any { !it }

        return formState.copy(
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            hasError = hasError
        )
    }
}