package eu.tutorials.thecoffeeshop.View

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.thecoffeeshop.R

@Composable
fun ProfileScreen(navController: NavController)
{
    Scaffold(topBar = {
        TopAppBar(navController = navController, titleName = "Tài khoản")
    },
        bottomBar = {
            BottomHomeBar(navToBottomBar = navController,
                iconHome = R.drawable.home_off,
                iconProfile = R.drawable.profile_on,
                iconHistory = R.drawable.history_off)
        }){
        Column(modifier = Modifier.padding(it)) {
            Column (modifier = Modifier.border(BorderStroke(1.dp, color = Color.Gray.copy(0.5f)))
                .fillMaxWidth()){
                Text(text = "Email", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 4.dp))
                Text(text = "test@gmail.com", style = TextStyle(
                    fontSize = 16.sp,
                ),
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                )
            }

            item(icon=R.drawable.feedback, text = "Phản hồi")
            item(icon=R.drawable.contact, text = "Liên hệ")
            item(icon=R.drawable.change_pass, text = "Đổi mật khẩu")
            item(icon=R.drawable.logout, text = "Đăng xuất")
        }
    }
}
@Composable
fun item(@DrawableRes icon:Int,
         text:String)
{
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {  }) {
        Image(painter = painterResource(id = icon),
            contentDescription = "feedback",
            )
        Text(text = text, style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        ),
            modifier = Modifier.padding(8.dp))
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProfilePreview()
{
    val navController = rememberNavController()
    ProfileScreen(navController)
}