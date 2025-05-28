package com.kliachenko.presentation.webView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kliachenko.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerWebStateContent(
    sellerWebUiState: SellerWebViewUiState,
    pageTitle: String,
    sellerLink: String,
    onBack: () -> Unit,
    onRetry: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = pageTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.go_back
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.height(56.dp)
            )
        },
    ) { paddingValues ->
        sellerWebUiState.Show(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            link = sellerLink,
            onBack = onBack,
            onRetry = onRetry,
            onPageStarted = {},
            onError = {_,_->},
            onPageFinished = {}
        )
    }
}