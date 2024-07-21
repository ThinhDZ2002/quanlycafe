package eu.tutorials.thecoffeeshop

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.Model.Image
import eu.tutorials.thecoffeeshop.View.CartScreen
import eu.tutorials.thecoffeeshop.View.DetailScreen
import eu.tutorials.thecoffeeshop.View.HistoryScreen
import eu.tutorials.thecoffeeshop.View.HomeScreen
import eu.tutorials.thecoffeeshop.View.LoginScreen
import eu.tutorials.thecoffeeshop.View.PaymentMethodScreen
import eu.tutorials.thecoffeeshop.View.ProfileScreen
import eu.tutorials.thecoffeeshop.View.RegisterScreen
import eu.tutorials.thecoffeeshop.ViewModel.LoginViewModel

@Composable
fun Navigation( viewModel: LoginViewModel = viewModel(), navController: NavHostController = rememberNavController())
{
    NavHost(navController = navController, startDestination = Screen.HomeView.route ){
        composable(Screen.LoginView.route)
        {
            LoginScreen(navController)
        }
        composable(Screen.RegisterView.route)
        {
            RegisterScreen(viewModel = viewModel, navController =navController )
        }
        composable(Screen.HomeView.route)
        {
            HomeScreen(navController={
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailView.route)
            },
                navToBottomBar = navController)
        }
        composable(Screen.DetailView.route)
        {
            val drink=navController.previousBackStackEntry?.savedStateHandle?.get<Drink>("cat")?:Drink("",0,"","",
                Image("")
            )
            DetailScreen(navController={
                navController.currentBackStackEntry?.savedStateHandle?.set("dog",it)
                navController.navigate(Screen.CartView.route)
            },
                drink=drink,
                navControllerBack = navController)
        }
        composable(Screen.CartView.route)
        {
            val drink=navController.previousBackStackEntry?.savedStateHandle?.get<Drink>("dog")?:Drink("",0,"","",
                Image("")
            )
            CartScreen(navController = navController,drink=drink)
        }
        composable(Screen.PaymentView.route)
        {
            PaymentMethodScreen(navController = navController)
        }
        composable(Screen.HistoryView.route)
        {
            HistoryScreen()
        }
        composable(Screen.ProfileView.route)
        {
            ProfileScreen(navController=navController)
        }
    }
}