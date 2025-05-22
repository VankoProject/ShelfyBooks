package com.kliachenko.presentation.navigation

import kotlinx.serialization.Serializable

interface AppGraph {

    fun route(): String

    fun pattern(): String? = null

    @Serializable
    sealed class SplashGraph : AppGraph {

        @Serializable
        data object Splash : SplashGraph() {
            override fun route() = Routes.SPLASH
        }
    }

    @Serializable
    sealed class AuthGraph : AppGraph {

        @Serializable
        data object Auth : AuthGraph() {
            override fun route() = Routes.AUTH
        }
    }

    @Serializable
    sealed class MainGraph : AppGraph {

        companion object {
            fun route() = Routes.MAIN
        }

        @Serializable
        sealed class CategoriesGraph : MainGraph() {
            data object Categories : CategoriesGraph() {
                override fun route() = Routes.CATEGORIES
            }
        }

        @Serializable
        sealed class BooksGraph : MainGraph() {
            data class Books(val categoryId: String) : BooksGraph() {

                override fun pattern(): String = Routes.BOOK_PATTERN

                override fun route() = Routes.booksRoute(categoryId)
            }
        }
    }
}


private object Routes {
    const val SPLASH = "splash"
    const val AUTH = "auth"
    const val MAIN = "main"
    const val CATEGORIES = "categories"

    const val BOOK_PATTERN = "books/{categoryId}"
    fun booksRoute(categoryId: String) = "books/$categoryId"
}




