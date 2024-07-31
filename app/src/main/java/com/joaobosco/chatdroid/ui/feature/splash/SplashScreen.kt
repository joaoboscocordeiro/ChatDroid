package com.joaobosco.chatdroid.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.ui.theme.ChatDroidTheme

/**
 * Created by "João Bosco" on 30/07/2024.
 */

@Composable
fun SplashRoute() {

}

@Composable
fun SplashScreen() {
    Column {

        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )

        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_safety),
                contentDescription = null
            )
            Text(
                text = "Mensagens, Seguras, Criptografadas, Privadas"
            )
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    ChatDroidTheme {
        SplashScreen()
    }
}