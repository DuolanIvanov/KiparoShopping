package ru.kiparo.lessons.kiparoshopping.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_40
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_24
import ru.kiparo.lessons.kiparoshopping.ui.tools.PhoneVisualTransformation
import ru.kiparo.lessons.kiparoshopping.ui.tools.isValidEmail
import ru.kiparo.lessons.kiparoshopping.ui.widgets.DefaultButton
import ru.kiparo.lessons.kiparoshopping.ui.widgets.FormTextField
import ru.kiparo.lessons.kiparoshopping.ui.widgets.PasswordFormTextField
import ru.kiparo.lessons.kiparoshopping.ui.widgets.TopBar

//TODO: Implement according to design
//https://www.figma.com/file/zBSqR7FXWYiPjpQzOk1Bh4/Kiparo-ShoppingApp?type=design&node-id=682%3A87&mode=design&t=9uEG17dJttPlpODc-1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    Column{
        TopBar(
            title = stringResource(id = R.string.register_screen_title)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding_40) //Отступ от ТопБар
                .padding(horizontal = 36.dp) //Отступ всей колонки по бокам
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space_24)   // Отступы по высоте между элементами ТекстФилдов
        ) {

            val modifier = Modifier.fillMaxWidth()

            var name by rememberSaveable { mutableStateOf("") }

            var email by rememberSaveable { mutableStateOf("") }
            val emailError by remember { derivedStateOf { email.isNotBlank() && !email.isValidEmail() } }  //derivedStateOf = это зависимость от вычислений

            var mobile by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var confirm by rememberSaveable { mutableStateOf("") }
            var referal by rememberSaveable { mutableStateOf("") }

            FormTextField(
                value = name,
                label = stringResource(id = R.string.full_name),
                modifier = modifier,
                onChange = { value -> name = value })

            FormTextField(
                value = email,
                label = stringResource(id = R.string.email_address),
                isError = emailError,
                modifier = modifier,
                onChange = { value -> email = value })

            FormTextField(
                value = mobile,
                label = stringResource(id = R.string.mobile_number),
                visualTransformation = PhoneVisualTransformation(
                    PhoneVisualTransformation.RU_MASK,
                    PhoneVisualTransformation.RU_CHAR
                ),
                modifier = modifier,
                onChange = { value -> mobile = value.take(10) })  // Ограничитель по символам = 10 цифр

            PasswordFormTextField(
                value = password,
                label = stringResource(id = R.string.password),
                modifier = modifier,
                onChange = { value -> password = value })

            PasswordFormTextField(
                value = confirm,
                label = stringResource(id = R.string.confirm_password),
                modifier = modifier,
                onChange = { value -> confirm = value })

            FormTextField(
                value = referal,
                label = stringResource(id = R.string.referal_code),
                modifier = modifier,
                onChange = { value -> referal = value })

            DefaultButton(
                title = stringResource(id = R.string.register_screen_title),
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KiparoShoppingTheme {
        RegisterScreen()
    }
}
