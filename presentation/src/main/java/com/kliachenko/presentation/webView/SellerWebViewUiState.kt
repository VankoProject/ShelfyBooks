package com.kliachenko.presentation.webView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R

interface SellerWebViewUiState {

    @Composable
    fun Show(
        modifier: Modifier,
        link: String,
        onBack: () -> Unit,
        onRetry: () -> Unit,
        onPageStarted: () -> Unit,
        onPageFinished: () -> Unit,
        onError: (errorCode: Int?, description: CharSequence?) -> Unit
    )

    data class SuccessSellerWebContent(
        private val pageTitle: String,
        private val link: String,
        private val onError: (errorCode: Int?, description: CharSequence?) -> Unit
    ) : SellerWebViewUiState {

        @Composable
        override fun Show(
            modifier: Modifier,
            link: String,
            onBack: () -> Unit,
            onRetry: () -> Unit,
            onPageStarted: () -> Unit,
            onPageFinished: () -> Unit,
            onError: (errorCode: Int?, description: CharSequence?) -> Unit
        ) {
            SellerWebViewContent(
                modifier = modifier,
                link = this.link,
                onWebViewError = this.onError,
                onPageStarted = onPageStarted,
                onPageFinished = onPageFinished
            )
        }
    }

    data class Error(private val errorMessage: String) : SellerWebViewUiState {

        @Composable
        override fun Show(
            modifier: Modifier,
            link: String,
            onBack: () -> Unit,
            onRetry: () -> Unit,
            onPageStarted: () -> Unit,
            onPageFinished: () -> Unit,
            onError: (errorCode: Int?, description: CharSequence?) -> Unit
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = errorMessage,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onRetry.invoke() }) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }

    data object Progress : SellerWebViewUiState {

        @Composable
        override fun Show(
            modifier: Modifier,
            link: String,
            onBack: () -> Unit,
            onRetry: () -> Unit,
            onPageStarted: () -> Unit,
            onPageFinished: () -> Unit,
            onError: (errorCode: Int?, description: CharSequence?) -> Unit
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.loading),
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(54.dp))
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}