package com.joaobosco.chatdroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.joaobosco.chatdroid.ui.ChatApp
import com.joaobosco.chatdroid.ui.theme.ChatDroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ChatDroidTheme {
                ChatApp()
            }
        }
    }
}
