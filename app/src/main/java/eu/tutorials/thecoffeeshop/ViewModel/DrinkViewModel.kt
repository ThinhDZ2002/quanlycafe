package eu.tutorials.thecoffeeshop.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.Service
import eu.tutorials.thecoffeeshop.ViewModel.LoginViewModel.LoginState
import kotlinx.coroutines.launch

class DrinkViewModel:ViewModel() {
    private val _drinksState = mutableStateOf(DrinkState())
    val drinksState: State<DrinkState> = _drinksState
    init {
        fetchMeals()
    }
    private fun fetchMeals(){
        viewModelScope.launch {
            try {
                val response = Service.getDrinks()
                _drinksState.value=_drinksState.value.copy(
                    list = response ,
                    error = null
                )
            }catch (e : Exception){
                _drinksState.value = _drinksState.value.copy(
                    error = "ERROR IS ${e.message}"
                )
            }
        }
    }

    data class DrinkState(
        val list: List<Drink> = emptyList(),
        val error: String? = null
    )
}