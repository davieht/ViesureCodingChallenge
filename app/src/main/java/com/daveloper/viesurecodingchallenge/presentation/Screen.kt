package com.daveloper.viesurecodingchallenge.presentation

sealed class Screen(val route: String) {
    object OverviewScreen: Screen("overview_screen")
    object DetailScreen: Screen("detail_screen")
}