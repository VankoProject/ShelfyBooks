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

interface AuthUiState {

    @Composable
    fun Show(onClick: () -> Unit)

    abstract class Abstract(
        private val content: @Composable (onClick: () -> Unit) -> Unit
    ) : AuthUiState {

        @Composable
        override fun Show(onClick: () -> Unit) {
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
                content(onClick)
            }
        }
    }

    data object Progress : Abstract(content = {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
    })

    data object Initial : Abstract(content = { onClick ->
        Box(
            modifier = Modifier
                .clickable(onClick = onClick, role = Role.Button)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_natural_logo),
                contentDescription = stringResource(id = R.string.login_with_google),
                modifier = Modifier.height(48.dp)
            )
        }
    })

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun AuthUiStateSignInPreview() {
    AuthUiState.Initial.Show {}
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun AuthUiStateProgressPreview() {
    AuthUiState.Progress.Show {}
}