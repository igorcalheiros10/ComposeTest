package com.icebreakerlabs.myapplication2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icebreakerlabs.myapplication2.test.Login
import com.icebreakerlabs.myapplication2.ui.theme.MyApplication2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = TestViewModel(LoginHandler())
        setContent {
            MyApplication2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login().LoginScreen(Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyApplication2Theme {
            Login().LoginScreen(Modifier.padding(24.dp), TestViewModel(LoginHandler()))
        }
    }
}
/*
Create an Android app that simulates a login process using Kotlin coroutines.
 The app should have the following features:

Login form: Design a simple login form with
fields for username and password.

Simulated authentication:
Utilize Kotlin coroutines to simulate an authentication process.

For example, you can use a coroutine with a delay to mimic the authentication process taking some time.
Display login result: After the simulated authentication,
display a message indicating whether the login was successful or not.

Error handling: Handle cases where the login process encounters errors or
invalid credentials gracefully, displaying appropriate error messages to the user.

Bonus (optional):

Loading indicator: Show a loading indicator while the authentication process is ongoing.
Remember me: Add an option to remember the user's login credentials for future sessions.
 */