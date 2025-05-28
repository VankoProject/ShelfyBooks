package com.kliachenko.presentation.navigation

import javax.inject.Inject

interface Navigation {

    interface ToAuthScreen {
        fun navigateToAuth()
    }

    interface ToCategoriesScreen {
        fun navigateToCategories()
    }

    interface ToBooksScreen {

        fun navigateToBooksWithArgs(categoryId: String, categoryName: String)

        fun goBackFromWeb()

    }

    interface ToWebView {

        fun navigateToWebWithArgs(name: String, link: String)

    }

    interface SplashNavigation : ToAuthScreen, ToCategoriesScreen

    interface BooksNavigation: ToWebView, ToCategoriesScreen


    interface Mutable : ToBooksScreen, SplashNavigation, BooksNavigation

    class Base @Inject constructor(
        private val navigationState: NavigationState
    ) : Mutable {

        override fun navigateToAuth() {
            navigationState.navigateAndReplace(AppGraph.AuthGraph.Auth)
        }

        override fun navigateToCategories() {
            navigationState.navigateAndReplace(AppGraph.MainGraph.CategoriesGraph.Categories)
        }

        override fun navigateToWebWithArgs(name: String, link: String) {
            navigationState.navigate(AppGraph.MainGraph.WebViewGraph.Seller(name, link))
        }

        override fun goBackFromWeb() {
            navigationState.popBackStack()
        }

        override fun navigateToBooksWithArgs(categoryId: String, categoryName: String) {
            navigationState.navigate(AppGraph.MainGraph.BooksGraph.Books(categoryId, categoryName))
        }

    }

}
