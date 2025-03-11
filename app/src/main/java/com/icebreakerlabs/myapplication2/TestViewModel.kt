package com.icebreakerlabs.myapplication2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TestViewModel(private val handler: LoginHandler) : ViewModel() {
    val loginStatus: MutableStateFlow<State> = MutableStateFlow(State.None)

    fun handleLogin(email: String, pass: String) {
        viewModelScope.launch {
            loginStatus.update { State.Loading }

            handler.submitLogin(email, pass).let { result ->
                loginStatus.update {
                    (if (result.isSuccess) State.Success else State.Error(
                        result.exceptionOrNull()?.message.or(
                            "Please review your login info"
                        )
                    ))
                }
                println(result)
            }
        }
    }

    private fun String?.or(default: String) = this ?: default

    sealed class State {
        abstract val message: String?

        object None : State() {
            override val message = null
        }

        object Loading : State() {
            override val message = "Logging you in"
        }

        object Success : State() {
            override val message = "Auth success!"
        }

        data class Error(override val message: String) : State()
    }
}