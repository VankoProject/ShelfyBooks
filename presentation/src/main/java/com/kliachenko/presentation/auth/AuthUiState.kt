package com.kliachenko.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState
import com.kliachenko.presentation.uiComponents.dialog.HandleDialog

interface AuthUiState {

    @Composable
    fun Show(dialogUiState: DialogUiState, onSignIn: () -> Unit, onDismiss: () -> Unit)

    abstract class Abstract(
        private val content: @Composable (onSignIn: () -> Unit) -> Unit
    ) : AuthUiState {

        @Composable
        override fun Show(
            dialogUiState: DialogUiState,
            onSignIn: () -> Unit,
            onDismiss: () -> Unit
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 22.sp,
                    text = stringResource(R.string.auth_greeting)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.auth_instruction)
                )
                Spacer(modifier = Modifier.height(56.dp))
                Box(
                    modifier = Modifier
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    content(onSignIn)
                }

                HandleDialog(
                    dialogUiState = dialogUiState,
                    onDismiss = onDismiss,
                    onRetryButtonClick = onSignIn,
                    onCancelButtonClick = onDismiss
                )
            }
        }
    }

    data object Progress : Abstract(content = { _ ->
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
    })

    data object Initial : Abstract(content = { onSignIn ->
        Image(
            modifier = Modifier
                .height(48.dp)
                .clickable(onClick = onSignIn, role = Role.Button),
            painter = painterResource(id = R.drawable.google_natural_logo),
            contentDescription = stringResource(id = R.string.login_with_google),
        )
    })

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun AuthUiStateSignInPreview() {
    AuthUiState.Initial.Show(
        dialogUiState = DialogUiState.None,
        onSignIn = {},
        onDismiss = {}
    )
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun AuthUiStateProgressPreview() {
    AuthUiState.Progress.Show(
        dialogUiState = DialogUiState.None,
        onSignIn = {},
        onDismiss = {})
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun AuthUiStateErrorDialogPreview() {
    AuthUiState.Initial.Show(
        dialogUiState = DialogUiState.Error(
            "No internet connection"
        ),
        onSignIn = {},
        onDismiss = {}
    )
}