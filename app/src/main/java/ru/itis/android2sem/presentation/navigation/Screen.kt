package ru.itis.android2sem.presentation.navigation

sealed class Screen(val route: String) {
    object CatList : Screen("cat_list")
    object CatDetail : Screen("cat_detail/{text}") {
        fun createRoute(text: String) = "cat_detail/$text"
    }
}