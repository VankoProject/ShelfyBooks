package com.kliachenko.presentation.navigation

import android.net.Uri
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
            data class Books(val categoryId: String, val categoryName: String) : BooksGraph() {

                override fun pattern(): String = Routes.BOOK_PATTERN

                override fun route() = Routes.booksRoute(categoryId, categoryName)
            }
        }

        @Serializable
        sealed class WebViewGraph : MainGraph() {
            data class Seller(val pageTitle: String, val sellerLink: String) : WebViewGraph() {

                override fun route() = Routes.sellerRoute(pageTitle, sellerLink)

                override fun pattern(): String = Routes.SELLER_PATTERN
            }
        }
    }
}


private object Routes {
    const val SPLASH = "splash"
    const val AUTH = "auth"
    const val MAIN = "main"
    const val CATEGORIES = "categories"

    const val BOOK_PATTERN = "books/{categoryId}/{categoryName}"
    fun booksRoute(categoryId: String, categoryName: String) =
        "books/$categoryId/${Uri.encode(categoryName)}"

    const val SELLER_PATTERN = "seller/{pageTitle}/{sellerLink}"
    fun sellerRoute(pageTitle: String, sellerLink: String) =
        "seller/${Uri.encode(pageTitle)}/${Uri.encode(sellerLink)}"

}




