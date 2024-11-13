package com.example.stemelevate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


//@Composable
//fun GeminiFeedbackScreen(
//    navController: NavHostController,
//    summary: String,
//) {
//    val feedbackViewModel = viewModel<GeminiViewModel>()
//    val placeholderResult = stringResource(R.string.results_placeholder)
//    var result by rememberSaveable { mutableStateOf(placeholderResult) }
//    val uiState by feedbackViewModel.uiState.collectAsState()
//
//    LaunchedEffect(Unit) {
//        feedbackViewModel.sendPrompt("Suggest career pathways based on the following user preferences:\n$summary")
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF3E39AF))
//            .padding(24.dp)
//    ) {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            item { // Title
//                Text(
//                    text = stringResource(R.string.output_screen_title),
//                    style = MaterialTheme.typography.titleLarge,
//                    modifier = Modifier.padding(16.dp),
//                    color = Color.White
//                )
//            }
//            item {
//                Box {
//                    Image(
//                        painter = painterResource(id = R.drawable.welcome_screen_image),
//                        contentDescription = "Superimposed Image",
//                        modifier = Modifier.align(Alignment.Center),
//                        contentScale = ContentScale.Fit
//                    )
//                }
//            }
//
//            item { // Loading or Result
//                if (uiState is UiState.Loading) {
//                    CircularProgressIndicator(modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentSize(Alignment.Center),
//                        color = Color.White
//                        )
//                } else {
//                    val textColor = if (uiState is UiState.Error) {
//                        MaterialTheme.colorScheme.error
//                    } else {
//                        MaterialTheme.colorScheme.onSurface
//                    }
//
//                    result = when (uiState) {
//                        is UiState.Error -> (uiState as UiState.Error).errorMessage
//                        is UiState.Success -> (uiState as UiState.Success).outputText
//                        else -> placeholderResult
//                    }
//
//                    Text(
//                        text = result,
//                        textAlign = TextAlign.Start,
//                        color = Color.White,
//                        modifier = Modifier
//                            .padding(16.dp)
//                            .fillMaxWidth()
//                    )
//                }
//            }
//
//            item { // Button
//                Box(modifier = Modifier.fillMaxWidth()) {
//                    Button(
//                        onClick = { navController.navigate(StemScreen.UserPreferences.name) },
//                        modifier = Modifier
//                            .align(Alignment.BottomCenter)
//                            .padding(16.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6599FE))
//                    ) {
//                        Text("Previous")
//                    }
//                }
//            }
//        }
//    }
//}
@Composable
fun GeminiFeedbackScreen(
    navController: NavHostController,
    summary: String,
) {
    val feedbackViewModel = viewModel<GeminiViewModel>()
    val placeholderResult = stringResource(R.string.results_placeholder)
    var result by rememberSaveable { mutableStateOf(placeholderResult) }
    val uiState by feedbackViewModel.uiState.collectAsState()

    // Automatically trigger sendPrompt when the screen opens
    LaunchedEffect(Unit) {
        feedbackViewModel.sendPrompt("Suggest career pathways based on the following user preferences:\n$summary")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E39AF))
            .padding(24.dp)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.output_screen_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
            Box {
                Image(
                    painter = painterResource(id = R.drawable.welcome_screen_image),
                    contentDescription = "Superimposed Image",
                    modifier = Modifier.align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }

            if (uiState is UiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White)
            } else {
                val textColor = if (uiState is UiState.Error) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                result = when (uiState) {
                    is UiState.Error -> (uiState as UiState.Error).errorMessage
                    is UiState.Success -> (uiState as UiState.Success).outputText
                    else -> placeholderResult
                }

                val scrollState = rememberScrollState()
                Text(
                    text = result,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { navController.navigate(StemScreen.UserPreferences.name) },
                        modifier = Modifier
                            .size(width = 200.dp, height = 60.dp)
                            .padding(16.dp)
                    ) {
                        Text("Previous")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GeminiFeedbackScreenPreview() {
    GeminiFeedbackScreen(
        summary = "Sample Summary",
        navController = rememberNavController()
    )
}

