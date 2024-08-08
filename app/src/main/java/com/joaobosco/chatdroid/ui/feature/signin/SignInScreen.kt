package com.joaobosco.chatdroid.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.ui.theme.BackgroundGradient
import com.joaobosco.chatdroid.ui.theme.ChatDroidTheme

/**
 * Created by "João Bosco" on 07/08/2024.
 */

@Composable
fun SignInRoute() {

}

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    ChatDroidTheme {
        SignInScreen()
    }
}