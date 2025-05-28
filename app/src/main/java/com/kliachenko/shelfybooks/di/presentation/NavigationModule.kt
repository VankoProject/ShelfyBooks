package com.kliachenko.shelfybooks.di.presentation

import com.kliachenko.presentation.navigation.Navigation
import com.kliachenko.presentation.navigation.NavigationState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigation(navigationState: NavigationState): Navigation.Mutable {
        return Navigation.Base(navigationState)
    }

    @Provides
    fun provideToBooksScreen(navigation: Navigation.Mutable): Navigation.ToBooksScreen = navigation

    @Provides
    fun provideToWebView(navigation: Navigation.Mutable): Navigation.ToWebView = navigation

    @Provides
    fun provideToCategoriesScreen(navigation: Navigation.Mutable): Navigation.ToCategoriesScreen = navigation

    @Provides
    fun provideToAuthScreen(navigation: Navigation.Mutable): Navigation.ToAuthScreen = navigation

    @Provides
    fun provideSplashNavigation(navigation: Navigation.Mutable): Navigation.SplashNavigation =
        navigation as Navigation.SplashNavigation

    @Provides
    fun provideBooksNavigation(navigation: Navigation.Mutable): Navigation.BooksNavigation =
        navigation as Navigation.BooksNavigation

}