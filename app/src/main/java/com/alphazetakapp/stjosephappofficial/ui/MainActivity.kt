package com.alphazetakapp.stjosephappofficial.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.navigation.AppNavigation
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Este sería el tema de arranque
        setTheme(R.style.Theme_App_Starting)

        setContent {
            StjosephappofficialTheme {
                /*val screenSplash = installSplashScreen().setKeepOnScreenCondition{}*/
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StjosephappofficialTheme {

    }
}