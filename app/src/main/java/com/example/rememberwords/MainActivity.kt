package com.example.rememberwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rememberwords.navigation.Routes
import com.example.rememberwords.ui.theme.RememberWordsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberWordsTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Routes.MAIN_SCREEN) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(navigation = navController)
                    }
                    composable(
                        Routes.SECOND_SCREEN,
                        arguments =
                            listOf(
                                navArgument("customValue") {
                                    type = NavType.StringType
                                }
                            )
                    ) {
                        SecondScreen(
                            navigation = navController,
                            textToShow = it.arguments?.getString(
                                "customValue",
                                "Default second screen"
                            ) ?: ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navigation: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MainScreen")
        Button(onClick = {
            val ololo = "olololo"
            navigation.navigate("SecondScreen/$ololo")
        }) {
            Text(text = "to second")
        }
    }
}

@Composable
fun SecondScreen(navigation: NavController, textToShow: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = textToShow)
        Button(onClick = {
            navigation.popBackStack()
        }) {
            Text(text = "to first")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}