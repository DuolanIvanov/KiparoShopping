package ru.kiparo.lessons.kiparoshopping

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ru.kiparo.lessons.kiparoshopping.register.RegisterScreen
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContent {
            KiparoShoppingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    RegisterScreen()
                }
            }
        }
    }
}
