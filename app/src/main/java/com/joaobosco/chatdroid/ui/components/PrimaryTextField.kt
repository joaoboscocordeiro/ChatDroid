package com.joaobosco.chatdroid.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joaobosco.chatdroid.R
import com.joaobosco.chatdroid.ui.extension.getVisualTransformationForPassword
import com.joaobosco.chatdroid.ui.theme.ChatDroidTheme

/**
 * Created by "João Bosco" on 12/08/2024.
 */

@Composable
fun PrimaryTextField(
    value: String,
    onValueChanger: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    @DrawableRes
    leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    errorMessage: String? = null
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanger,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                    val visibilityIcon = if (passwordVisible) {
                        R.drawable.ic_visibility
                    } else {
                        R.drawable.ic_visibility_off
                    }
                    Icon(
                        painter = painterResource(id = visibilityIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwordVisible = !passwordVisible
                            },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            visualTransformation = keyboardType.getVisualTransformationForPassword(passwordVisible),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (errorMessage != null) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        )

        errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(start = 16.dp),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryTextFieldPreview() {
    ChatDroidTheme {
        PrimaryTextField(
            value = "",
            onValueChanger = {},
            placeholder = "E-mail",
            leadingIcon = R.drawable.ic_envelope
        )
    }
}

@Preview
@Composable
private fun PrimaryTextFieldErrorPreview() {
    ChatDroidTheme {
        PrimaryTextField(
            value = "",
            onValueChanger = {},
            placeholder = "E-mail",
            leadingIcon = R.drawable.ic_envelope,
            errorMessage = "E-mail inválido!"
        )
    }
}