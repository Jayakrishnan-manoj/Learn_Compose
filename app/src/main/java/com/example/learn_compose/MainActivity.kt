package com.example.learn_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn_compose.Destinations.firstScreen
import com.example.learn_compose.ui.theme.Learn_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Learn_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavScreen()
                }
            }
        }
    }
}

@Composable
fun NavScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.firstScreen.toString()) {
        composable(Destinations.firstScreen.toString()) {
            loaderColumn(navController = navController)
        }
        composable(Destinations.secondScreen.toString()) {
            secondScreen(navController = navController)
        }
    }
}


@Composable
fun loaderColumn(navController: NavController) {


    var progress by remember { mutableStateOf(0.15f) }
    CircularProgressIndicator(
        progress = progress,
        Modifier
            .size(20.dp)
            .height(10.dp)
            .padding(55.dp)
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(onClick = { if (progress.toInt() != 1) progress += 0.15f else progress -= 0.15f }) {
            Text(text = "Press to change loader")
        }
        ElevatedButton(onClick = { navController.navigate(Destinations.secondScreen.toString()) }) {
            Text(text = "Go to Screen 2")
        }
    }
}

@Composable
fun secondScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is Screen 2", fontSize = 24.sp, color = Color.Cyan)
        ElevatedButton(onClick = {
            navController.popBackStack(
                Destinations.secondScreen.toString(),
                inclusive = true
            )
        }) {
            Text(text = "Go to Screen 1")

        }
    }
}