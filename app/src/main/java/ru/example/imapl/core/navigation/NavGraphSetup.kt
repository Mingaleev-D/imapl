package ru.example.imapl.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.example.imapl.ui.screens.favorites.FavoritesScreen
import ru.example.imapl.ui.screens.fullImage.FullImageScreen
import ru.example.imapl.ui.screens.home.HomeScreen
import ru.example.imapl.ui.screens.home.HomeViewModel
import ru.example.imapl.ui.screens.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphSetup(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.HomeScreen,
    ) {
        composable<Routes.HomeScreen> {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                //   modifier = Modifier.padding(paddingValues = paddingValue),
                scrollBehavior = scrollBehavior,
                images = viewModel.images,
                onImageClick = { imageId ->
                    navController.navigate(Routes.FullImageScreen(imageId))
                },
                onSearchClick = {
                    navController.navigate(Routes.SearchScreen)
                },
                onFabClick = {
                    navController.navigate(Routes.FavoritesScreen)
                },
            )
        }

        composable<Routes.SearchScreen> {
            SearchScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }

        composable<Routes.FavoritesScreen> {
            FavoritesScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }

        composable<Routes.FullImageScreen> { backStackEntry ->
            val imageId = backStackEntry.toRoute<Routes.FullImageScreen>().imageId
            FullImageScreen(
                imageId = imageId,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }

        composable<Routes.ProfileScreen> {}
    }

}