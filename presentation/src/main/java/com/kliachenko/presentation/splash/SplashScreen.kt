package com.kliachenko.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kliachenko.presentation.R
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.Navigation
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun SplashScreen(
    navigation: Navigation.SplashNavigation
) {

    val viewModel: SplashViewModel = hiltViewModel()
    val isAuthorized by viewModel.appEntryScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = isAuthorized) {
        when (isAuthorized) {
            AuthState.Authorized ->
                navigation.navigateToCategories()
            AuthState.Unauthorized ->
                navigation.navigateToAuth()
            else -> {}
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter =
                rememberVectorPainter(
                    image = ImageVector.vectorResource(id = R.drawable.shelfybooks_logo)),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(2.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreenContent() {
    SplashScreenContent()
}
