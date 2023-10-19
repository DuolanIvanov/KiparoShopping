package ru.kiparo.lessons.kiparoshopping.register

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.kiparo.lessons.kiparoshopping.ui.tools.isValidEmail

//в RegisterViewModel мы делаем "State Hosting" = вынос управления стейтом из RegisterScreen(всякие параметры имя,телефон,почта и др) наверх в хостинг MainActivity для управления

data class RegisterUiState(
    //data class ВСЕГДА "val" переменные, то есть никогда не изменяются, а идут лишь в одну сторону! Дальше уже сам ViewModel делает нужные дела самостоятельно
    val name: String,
    val nameError: Boolean,
    val email: String,
    val emailError: Boolean,
    val password: String,
    val passwordError: Boolean,
    val confirm: String,
    val referal: String,
    val mobile: String,
    val mobileError: Boolean,
)

@Stable
class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegisterUiState(
            name = "Duolan Ivanov",
            nameError = false,
            email = "ivanov95@gmail.com",
            emailError = false,
            password = "bdfyjd",
            passwordError = false,
            confirm = "BDfyjd",
            referal = "12345",
            mobile = "89679251212",
            mobileError = false
        )
    )

    val uiState: StateFlow<RegisterUiState> = _uiState


    fun onNameChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = value,
                nameError = value.isBlank()
            )
        }
    }

    // Подсвечивание красным поле email
    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = email.isNotBlank() && !email.isValidEmail()
            )
        }
    }

    fun onMobileChange(mobile: String) {
        _uiState.update { currentState ->
            currentState.copy(email = mobile)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = password.isNotBlank() && password != currentState.confirm     // оператор "!=" означает, что ЕСЛИ не совпадает с полем который в confirm, то выдаем ошибку
            )
        }
    }

    fun onPasswordConfirmChange(confirmation: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirm = confirmation,
                passwordError = confirmation.isNotBlank() && confirmation != currentState.password
            )
        }
    }

    fun onReferalChange(referal: String) {
        _uiState.update { currentState ->
            currentState.copy(
                referal = referal
            )
        }
    }

    fun onSubmit() {
        _uiState.update { currentState ->
            currentState.copy(
                nameError = currentState.name.isBlank(),
                emailError = currentState.email.isBlank(),
                mobileError = currentState.mobile.isBlank(),
                passwordError = currentState.password.isBlank()
            )
        }
    }
}