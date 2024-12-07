package com.joaobosco.chatdroid.ui.validator

/**
 * Created by "João Bosco" on 05/12/2024.
 */

object EmailValidator {
    private const val EMAIL_REGEX = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"

    fun isValid(value: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(value)
    }
}