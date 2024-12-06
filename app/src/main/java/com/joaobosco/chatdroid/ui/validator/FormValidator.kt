package com.joaobosco.chatdroid.ui.validator

/**
 * Created by "João Bosco" on 05/12/2024.
 */

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}