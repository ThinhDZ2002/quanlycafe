package eu.tutorials.thecoffeeshop.View

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.R
import eu.tutorials.thecoffeeshop.Screen
import eu.tutorials.thecoffeeshop.ViewModel.DrinkViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:(Drink)-> Unit,
               navToBottomBar: NavController)
{
    var search by rememberSaveable {
        mutableStateOf("")
    }
    val image = listOf(
        R.drawable.viewpager1,
        R.drawable.viewpager2,
        R.drawable.viewpager3,
    )
    val menuViewModel:DrinkViewModel= viewModel()
    val viewState by menuViewModel.drinksState
    Scaffold (bottomBar = {
        BottomHomeBar(navToBottomBar=navToBottomBar,
            iconHome = R.drawable.home_on,
            iconHistory = R.drawable.history_off,
            iconProfile = R.drawable.profile_off)
    }){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            OutlinedTextField(value =search ,
                onValueChange ={
                    search=it
                },
                placeholder = { Text(text = "Hôm nay bạn muốn uống gì?")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription =null )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Blue,
                    unfocusedContainerColor = Color.Gray
                )
            )
            ImageSliderWithIndicator(images = image)
            Box {
                when{
                    viewState.error!=null->{
                        Text(text = "${viewState.error}")
                    }
                    else ->{
                        DrinkScreen(drinks = viewState.list,
                            navController=navController)
                    }
                }
            }
        }
    }

}
@Composable
fun BottomHomeBar(navToBottomBar: NavController,
                  @DrawableRes iconHome:Int,
                  @DrawableRes iconHistory:Int,
                  @DrawableRes iconProfile:Int)
{
    var textHomeColor by remember {
        mutableStateOf(Color.Black)
    }
    var textHistoryColor by remember {
        mutableStateOf(Color.Black)
    }
    var textProfileColor by remember {
        mutableStateOf(Color.Black)
    }
    if(iconHome==R.drawable.home_on)
    {
        textHomeColor=Color.Black.copy(0.8f)
    }
    else
    {
        textHomeColor=Color.Black.copy(0.4f)
    }
    if(iconHistory==R.drawable.history_on)
    {
        textHistoryColor=Color.Black.copy(0.8f)
    }
    else
    {
        textHistoryColor=Color.Black.copy(0.4f)
    }
    if(iconProfile==R.drawable.profile_on)
    {
        textProfileColor=Color.Black.copy(0.8f)
    }
    else
    {
        textProfileColor=Color.Black.copy(0.4f)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .border(BorderStroke(0.5f.dp, color = Color.Gray.copy(0.5f))),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .clickable { navToBottomBar.navigate(Screen.HomeView.route) }) {
            Image(painter = painterResource(id = iconHome),
                contentDescription =null,
                modifier = Modifier
                    .size(24.dp))
            Text(text = "Trang chủ", style = TextStyle(
                fontSize = 20.sp,
                color = textHomeColor
            ))
        }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .clickable { navToBottomBar.navigate(Screen.HistoryView.route) }) {
            Image(painter = painterResource(id =iconHistory),
                contentDescription =null,
                modifier = Modifier
                    .size(24.dp))
            Text(text = "Lịch sử", style = TextStyle(
                fontSize = 20.sp,
                color = textHistoryColor
            ))
        }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .clickable { navToBottomBar.navigate(Screen.ProfileView.route) }) {
            Image(painter = painterResource(id =iconProfile),
                contentDescription =null,
                modifier = Modifier
                    .size(24.dp))
            Text(text = "Tài khoản", style = TextStyle(
                fontSize = 20.sp,
                color = textProfileColor
            ))
        }

    }
}
@Composable
fun DrinkScreen(drinks:List<Drink>,
                navController:(Drink)-> Unit)
{
    LazyColumn{
        items(drinks){
                drink ->
            DrinkItem(drink = drink,
                navController=navController)
        }
    }
}
@Composable
fun DrinkItem(drink:Drink,
              navController:(Drink)-> Unit)
{
    val borderColor= Color.Gray.copy(0.2f)
    ConstraintLayout (
        modifier= Modifier
            .background(color = Color.White, shape = RectangleShape)
            .border(BorderStroke(0.5f.dp, borderColor))
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController(drink)
            }
            .height(90.dp)
    )
    {
        val verticalGuideLine20=createGuidelineFromStart(0.2f)
        val (imgIcon,boxRate,ratingText,starIcon)=createRefs()
        Image(painter = rememberAsyncImagePainter(model ="http://${stringResource(id = R.string.url_link)}:1337${drink.ImageURL.url}")
            , contentDescription = null
            , modifier= Modifier
                .background(color = Color.Gray.copy(0.1f), shape = CircleShape)
                .size(60.dp)
                .constrainAs(imgIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(verticalGuideLine20)
                    bottom.linkTo(parent.bottom)
                }
            )
        ConstraintLayout(
            modifier = Modifier
                .padding(3.dp)
                .clip(shape = RoundedCornerShape(8.dp)) // Khoảng cách từ nội dung đến viền Box
                .background(color = Color.White)
                .constrainAs(boxRate) {
                    top.linkTo(imgIcon.top, margin = 47.dp)
                    start.linkTo(imgIcon.start)
                    end.linkTo(imgIcon.end, margin = 3.dp)
                    bottom.linkTo(imgIcon.bottom)
                }
                .wrapContentSize()
        ) {
            Text(
                text = "4.5",
                color = Color.Black,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier.constrainAs(ratingText) {
                    top.linkTo(imgIcon.top, margin = 70.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Star",
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(starIcon) {
                        top.linkTo(ratingText.top)
                        start.linkTo(ratingText.end, margin = 2.dp)
                    }
                    .padding(end = 5.dp)
            )
        }
        val (tvName,tvPrice,tvRecipe)=createRefs()
        val horizontalGuideLine50=createGuidelineFromTop(0.5f)
        Text(text = drink.Name,
            style = TextStyle(
                color = Color.Black.copy(0.7f),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            ), modifier=Modifier.constrainAs(tvName)
            {
                start.linkTo(verticalGuideLine20)
                bottom.linkTo(horizontalGuideLine50)
            }
        )
        Text(text = "${drink.Price} VND", style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        ), modifier=Modifier.constrainAs(tvPrice)
            {
                end.linkTo(parent.end)
                bottom.linkTo(horizontalGuideLine50)
            }
        )
        Text(text = drink.Recipe, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ), modifier=Modifier.constrainAs(tvRecipe)
            {
              top.linkTo(horizontalGuideLine50)
              start.linkTo(verticalGuideLine20)
              bottom.linkTo(parent.bottom)
            }
        )
    }
}
@Composable
fun ImageSliderItem(imageRes:Int){
    Image(painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    )
}
@Composable
fun ImageSliderWithIndicator(images: List<Int>) {
    val currentIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex.value = (currentIndex.value + 1) % images.size
        }

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            ImageSliderItem(imageRes = images[currentIndex.value])
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewHomeScreen() {
//    val sampleDrinks = listOf(
//        Drink(Name = "Cà phê sữa", Price = 20000, Recipe = "Cà phê, sữa", Description = "", ImageURL = eu.tutorials.thecoffeeshop.Model.Image("")),
//        Drink(Name = "Trà xanh", Price = 15000, Recipe = "Trà xanh, đường",Description = "" ,ImageURL = eu.tutorials.thecoffeeshop.Model.Image("")),
//        Drink(Name = "Sinh tố dâu", Price = 30000, Recipe = "Dâu, sữa chua", Description = "",ImageURL = eu.tutorials.thecoffeeshop.Model.Image(""))
//    )
//
//    HomeScreen(navController = { drink: Drink ->
//        sampleDrinks
//    })
//}
