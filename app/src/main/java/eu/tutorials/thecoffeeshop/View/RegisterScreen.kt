package eu.tutorials.thecoffeeshop.View

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorials.thecoffeeshop.ViewModel.LoginViewModel
import eu.tutorials.thecoffeeshop.R
import eu.tutorials.thecoffeeshop.Screen
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

@Composable
fun RegisterScreen(viewModel: LoginViewModel,
                   navController: NavController)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.logo1), contentDescription =null )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Username", modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            placeholder = { Text("Enter your username") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email", modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value=it },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Password", modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value=it },
            placeholder = { Text("Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        val context= LocalContext.current
        Button(
            onClick = {
                val json = JSONObject().apply {
                    put("username", userName.value)
                    put("email", email.value)
                    put("password", password.value)
                }
                val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
                viewModel.registerUser(requestBody) {
                    navController.navigate(Screen.LoginView.route)
                }
                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFF8B4513),
                disabledContainerColor = Color.Gray
            )
            , enabled =if (email.value.isNotEmpty()&&password.value.isNotEmpty())
            {
                true
            }else
            {
                false
            }
        ) {
            Text(text = "Register", style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            )
        }
        Spacer(modifier = Modifier.height(56.dp))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(text = "Đã có tài khoản?", style = TextStyle(

            )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Đăng nhập", style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
                modifier = Modifier.clickable {navController.popBackStack()  }
            )
        }

    }
}
