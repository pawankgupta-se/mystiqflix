package com.gowittgroup.mystiqflix.android.desitnation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val title: String
    val route: String
    val routWithArgs: String
}

object Home: Destinations {
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routWithArgs: String
        get() = route
}

object Detail: Destinations {
    override val title: String
        get() = "Movie Details"
    override val route: String
        get() = "detail"
    override val routWithArgs: String
        get() = "$route/{movieId}"

    val args = listOf(
        navArgument(name = "movieId"){
            type = NavType.IntType
        }
    )
}

val movieDestinations = listOf(Home, Detail)