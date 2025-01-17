package com.joaobosco.chatdroid.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.joaobosco.chatdroid.R

/**
 * Created by "João Bosco" on 16/01/2025.
 */

@Composable
fun AppDialog(
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    message: String,
    modifier: Modifier = Modifier,
    confirmButtonText: String = stringResource(R.string.common_ok),
    title: String? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmButtonClick
            ) {
                Text(
                    text = confirmButtonText
                )
            }
        },
        modifier = modifier,
        title = {
            title?.let {
                Text(text = it)
            }
        },
        text = {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}