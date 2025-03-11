package com.icebreakerlabs.myapplication2.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.icebreakerlabs.myapplication2.LoginHandler
import com.icebreakerlabs.myapplication2.TestViewModel
import com.icebreakerlabs.myapplication2.TestViewModel.State
import com.icebreakerlabs.myapplication2.ui.theme.MyApplication2Theme

class Login {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginScreen(modifier: Modifier = Modifier, viewModel: TestViewModel) {
        val snackBarHostState = remember { SnackbarHostState() }

        val status = viewModel.loginStatus.collectAsState()

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                modifier = Modifier.padding(top = 36.dp),
                fontSize = TextUnit(24f, TextUnitType.Sp),
                text = "Log in here"
            )

            TextField(
                value = email,
                modifier = modifier,
                onValueChange = {
                    email = it
                },
                label = { Text("Your Email Here") },
            )

            TextField(value = password, onValueChange = {
                password = it
            },
                label = { Text("Your Password Here") }
            )

            if (status.value == State.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Spacer(Modifier.padding(18.dp))
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 56.dp,
                        vertical = 48.dp
                    ),
                onClick = {
                    viewModel.handleLogin(email, password)
                }
            ) {
                Text("Submit")
            }


            status.value.let {
                LaunchedEffect(it.message) {
                    if (it is State.Error || it is State.Success)
                        snackBarHostState.showSnackbar(it.message.orEmpty())
                }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(snackBarHostState) { data ->
                            Snackbar(
                                snackbarData = data,
                                containerColor = Color.White,
                                contentColor = when (it) {
                                    is State.Error -> Color.Red
                                    is State.Success -> Color.Green
                                    else -> Color.White
                                }
                            )
                        }
                    },
                    content = { unused -> println(unused) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    MyApplication2Theme {
        Login().LoginScreen(Modifier.fillMaxSize(), TestViewModel(LoginHandler()))
    }
}