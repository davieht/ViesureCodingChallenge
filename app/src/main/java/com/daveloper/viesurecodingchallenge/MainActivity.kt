package com.daveloper.viesurecodingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daveloper.viesurecodingchallenge.articles.OverviewScreen
import com.daveloper.viesurecodingchallenge.detail.DetailScreen
import com.daveloper.viesurecodingchallenge.presentation.Screen
import com.daveloper.viesurecodingchallenge.ui.theme.ViesureCodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViesureCodingChallengeTheme {

                val scaffoldState = rememberScaffoldState(
                    snackbarHostState = remember {
                        SnackbarHostState()
                    }
                )
                val navController: NavHostController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = navController,
                                startDestination = Screen.OverviewScreen.route
                            ) {
                                composable(Screen.OverviewScreen.route) {
                                    OverviewScreen(
                                        scaffoldState = scaffoldState,
                                        navigateTo = { id ->
                                            navController.navigate("${Screen.DetailScreen.route}/${id}")
                                        }
                                    )
                                }
                                composable(
                                    route = "${Screen.DetailScreen.route}/{id}",
                                    arguments = listOf(
                                        navArgument("id") {
                                            type = NavType.IntType
                                            defaultValue = -1
                                        }
                                    )
                                ) {
                                    DetailScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
