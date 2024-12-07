package com.joaobosco.chatdroid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joaobosco.chatdroid.navigation.ChatNavHost

/**
 * Created by "João Bosco" on 22/07/2024.
 */

@Composable
fun ChatApp() {
    Scaffold(
        bottomBar = {}
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(innerPaddings)
                .padding(innerPaddings)
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost()
        }
    }
}