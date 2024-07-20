package eu.tutorials.thecoffeeshop

import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.Model.User
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private val retrofit= Retrofit.Builder().baseUrl("http://192.168.1.10:1337/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val Service = retrofit.create(ApiService::class.java)
interface ApiService{
    @GET("drinks")
    suspend fun getDrinks():List<Drink>

    @GET("users")
    suspend fun getUsers():List<User>

    @POST("auth/local/register")
    suspend fun registerUser(@Body requestBody: RequestBody): User

    //endpoint đăng nhập
    @POST("auth/local")
    suspend fun loginUser(@Body requestBody: RequestBody): AuthResponse


}
data class AuthResponse(
    val jwt: String,
    val user: User
)

