package com.joaobosco.chatdroid.ui.validator

/**
 * Created by "João Bosco" on 05/12/2024.
 */

object PasswordValidator {

    fun isValid(value: String): Boolean {
        return value.length >= 8 && value.any { it.isDigit() } && value.any { it.isLetter() }
    }
}