package eu.tutorials.thecoffeeshop.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.thecoffeeshop.AuthResponse
import eu.tutorials.thecoffeeshop.Service
import eu.tutorials.thecoffeeshop.Model.User
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class LoginViewModel :ViewModel()
{
    private val _usersState = mutableStateOf(LoginState())
    val usersState: State<LoginState> = _usersState
    init {
        fetchUsers()
    }
    private fun fetchUsers(){
        viewModelScope.launch {
            try {
                val response = Service.getUsers()
                _usersState.value=_usersState.value.copy(
                    list = response ,
                    error = null
                )
            }catch (e : Exception){
                _usersState.value = _usersState.value.copy(
                    error = "ERROR IS ${e.message}"
                )
            }
        }
    }
    fun registerUser(requestBody: RequestBody, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = Service.registerUser(requestBody)
                onResult(response)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
    fun loginUser(email: String, password: String, onResult: (AuthResponse?) -> Unit) {
        viewModelScope.launch {
            val jsonObject = JSONObject().apply {
                put("identifier", email)
                put("password", password)
            }
            val jsonObjectString = jsonObject.toString()
            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObjectString)

            try {
                val response = Service.loginUser(requestBody)
                onResult(response)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
    data class LoginState(
        val list: List<User> = emptyList(),
        val error: String? = null
    )
}
