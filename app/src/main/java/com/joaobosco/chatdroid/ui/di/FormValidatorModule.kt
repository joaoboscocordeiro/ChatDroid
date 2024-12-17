package com.joaobosco.chatdroid.ui.di

import com.joaobosco.chatdroid.ui.feature.signup.SignUpFormState
import com.joaobosco.chatdroid.ui.feature.signup.SignUpFormValidator
import com.joaobosco.chatdroid.ui.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by "João Bosco" on 16/12/2024.
 */

@Module
@InstallIn(ViewModelComponent::class)
interface FormValidatorModule {

    @Binds
    fun bindSignUpFormValidator(signUpFormValidator: SignUpFormValidator): FormValidator<SignUpFormState>
}