package eu.tutorials.thecoffeeshop.View

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.tutorials.thecoffeeshop.ViewModel.LoginViewModel
import eu.tutorials.thecoffeeshop.R
import eu.tutorials.thecoffeeshop.Screen

@Composable
fun LoginScreen(
    navController: NavController
) {
    val userViewModel: LoginViewModel = viewModel()
    val viewState by userViewModel.usersState
    Box {
        when{
            viewState.error!=null->{
                Text(text = "${viewState.error}")
            }
            else ->{
                UserScreen(
                    userViewModel,
                    navController)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
               viewModel: LoginViewModel,
               navController: NavController)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.logo1), contentDescription =null )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Email", modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value=it},
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Password", modifier = Modifier.align(Alignment.Start))
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value=it },
            placeholder = { Text("Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        val context= LocalContext.current
        Button(
            onClick = {
                viewModel.loginUser(email.value, password.value) { response ->
                    if (response != null) {
                        navController.navigate(Screen.HomeView.route)
                    } else {
                        Toast.makeText(context, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors =ButtonDefaults.buttonColors(
                containerColor =  Color(0xFF8B4513),
                disabledContainerColor =Color.Gray
            )
                , enabled =if (email.value.isNotEmpty()&&password.value.isNotEmpty())
                {
                    true
                }else
                {
                    false
                }
        ) {
            Text(text = "Login", style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = AnnotatedString("Forgot Password?"),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { },
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(56.dp))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(text = "Chưa có tài khoản?", style = TextStyle(

            )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Đăng ký", style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
                modifier = Modifier.clickable { navController.navigate(Screen.RegisterView.route) }
            )
        }

    }
}

