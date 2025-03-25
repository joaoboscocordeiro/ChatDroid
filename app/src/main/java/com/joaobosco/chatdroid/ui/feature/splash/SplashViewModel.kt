package com.joaobosco.chatdroid.ui.feature.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaobosco.chatdroid.data.repository.AuthRepository
import com.joaobosco.chatdroid.model.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by "João Bosco" on 24/03/2025.
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authenticateState = MutableSharedFlow<AuthenticationState>()
    val authenticateState = _authenticateState.asSharedFlow()

    var showErrorDialogState by mutableStateOf(false)
        private set

    fun checkSession() {
        dismissErrorDialog()
        viewModelScope.launch {
            val accessToken = authRepository.getAccessToken()

            if (accessToken.isNullOrBlank()) {
                _authenticateState.emit(AuthenticationState.UserNotAuthenticated)
                return@launch
            }

            authRepository.authenticate(accessToken).fold(
                onSuccess = {
                    _authenticateState.emit(AuthenticationState.UserAuthenticated)
                },
                onFailure = {
                    if (it is NetworkException.ApiException && it.statusCode == 401) {
                        authRepository.clearAccessToken()
                        _authenticateState.emit(AuthenticationState.UserNotAuthenticated)
                    } else {
                        showErrorDialogState = true
                    }
                }
            )
        }
    }

    fun dismissErrorDialog() {
        showErrorDialogState = false
    }

    sealed interface AuthenticationState {
        data object UserAuthenticated : AuthenticationState
        data object UserNotAuthenticated : AuthenticationState
    }
}