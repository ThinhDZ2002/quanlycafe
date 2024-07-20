package eu.tutorials.thecoffeeshop



sealed class Screen(val route:String) {
    object LoginView:Screen("loginScreen")
    object RegisterView:Screen("registerScreen")
    object HomeView:Screen("homeScreen")
    object DetailView:Screen("detailScreen")
    object CartView:Screen("cartScreen")
    object PaymentView:Screen("paymentScreen")
    object HistoryView : Screen("historyScreen")
    object ProfileView : Screen("profileScreen")
}