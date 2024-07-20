package eu.tutorials.thecoffeeshop.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.Model.Image
import eu.tutorials.thecoffeeshop.R
import eu.tutorials.thecoffeeshop.Screen

@Composable
fun CartScreen(navController: NavController,drink: Drink)
{

    Scaffold(topBar = {
        TopAppBar(navController = navController, titleName = "Giỏ hàng")
    },
        bottomBar = { BottomCartBar()}
        ){
        Column(modifier = Modifier.padding(it)) {
            DrinkItemInCart(drink = drink)
            AddToCard()
            Spacer(modifier = Modifier
                .height(8.dp)
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxWidth())
            BodyCart("Phương thức thanh toán",
                "Chưa chọn phương thức thanh toán",
                navController=navController)
            BodyCart("Địa chỉ giao hàng",
                "Chưa chọn địa chỉ giao hàng",
                navController=navController)
            BodyCart("Khuyến mại",
                "Chưa áp dụng mã khuyến mại",
                navController=navController)
            Spacer(modifier = Modifier
                .height(8.dp)
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxWidth())
            Text(text = "Thanh toán", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            ),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 8.dp))
            BillCart("Giá","(1 đồ uống)","324000vnd")
            BillCart("Khuyến mại","Giảm giá 15%","-48000vnd")
        }
    }
}
@Composable
fun TopCartBar(navController: NavController,drink: Drink){
    Row (horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, color = Color.Gray.copy(0.2f)))){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription =null,
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(8.dp))
        Text(text = "Giỏ hàng", style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp
        ), modifier = Modifier.padding(8.dp)
        )
    }
}
@Composable
fun DrinkItemInCart(drink: Drink)
{
    ConstraintLayout(modifier = Modifier
        .border(BorderStroke(0.1f.dp, color = Color.Gray.copy(0.2f)))
        .padding(8.dp)
        .fillMaxWidth()
        ) {
        val (img,name,price,recipe,quantity,iconEdit,iconDelete,editQuantity)=createRefs()
        val verticalGuideLine20=createGuidelineFromStart(0.2f)
        Image(painterResource(id = R.drawable.anhdouong) ,
            contentDescription =null,
            modifier = Modifier
                .constrainAs(img) {
                    start.linkTo(parent.start)
                    end.linkTo(verticalGuideLine20)
                    top.linkTo(parent.top)
                }
                .size(60.dp)
                .background(shape = CircleShape, color = Color.Gray.copy(0.2f)))
        Text(text = drink.Name, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(img.top)
                    start.linkTo(verticalGuideLine20)
                }
                .padding(8.dp))
        Text(text = "${drink.Price} vnd", style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(img.top)
                    end.linkTo(parent.end)
                }
                .padding(8.dp))
        Text(text = drink.Recipe, style = TextStyle(
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        ),
            modifier = Modifier
                .constrainAs(recipe) {
                    bottom.linkTo(img.bottom)
                    start.linkTo(verticalGuideLine20)
                }
                .padding(8.dp))
        Text(text = "x3",
            modifier = Modifier
                .constrainAs(quantity) {
                    end.linkTo(parent.end)
                    bottom.linkTo(img.bottom)
                }
                .padding(8.dp))
        Icon(Icons.Default.Edit,
            contentDescription =null,
            modifier = Modifier
                .constrainAs(iconEdit)
                {
                    start.linkTo(verticalGuideLine20)
                    top.linkTo(img.bottom)
                }
                .padding(8.dp, 0.dp)
                )
        Icon(Icons.Default.Delete,
            contentDescription =null,
            modifier = Modifier
                .constrainAs(iconDelete) {
                    end.linkTo(editQuantity.start)
                    top.linkTo(iconEdit.top)
                }
                .padding(8.dp, 0.dp))
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .border(
                    BorderStroke(0.5f.dp, Color.Black),
                    shape = RectangleShape
                )
                .constrainAs(editQuantity) {
                    top.linkTo(iconEdit.top)
                    end.linkTo(parent.end)
                }
        )
        {
            Text(text = "-", style = TextStyle(
                fontSize = 18.sp
            ), modifier = Modifier
                .border(
                    border = BorderStroke(0.5f.dp, color = Color.Black.copy(0.7f)),
                    shape = RectangleShape
                )
                .clickable { }
                .padding(10.dp, 2.dp)
            )
            Text(text = "5", style = TextStyle(
                fontSize = 18.sp
            ), modifier = Modifier
                .border(
                    BorderStroke(0.5f.dp, Color.Black.copy(0.7f)),
                    shape = RectangleShape
                )
                .padding(8.dp, 2.dp)
            )
            Text(text = "+", style = TextStyle(
                fontSize = 18.sp
            ), modifier = Modifier
                .border(
                    border = BorderStroke(0.5f.dp, color = Color.Black.copy(0.7f)),
                    shape = RectangleShape
                )
                .clickable { }
                .padding(8.dp, 2.dp)
            )
        }

    }
}
@Composable
fun AddToCard()
{
    Row (modifier = Modifier.fillMaxWidth()
        .border(BorderStroke(0.1f.dp, color = Color.Gray))
        .padding(8.dp)
        .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start){
        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
        Text(text = "Thêm vào giỏ hàng", style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
            )
        )
    }
}
@Composable
fun BodyCart(Lable:String,
             Value:String,
             navController: NavController)
{
    ConstraintLayout (modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(0.5f.dp, color = Color.Gray))
        .clickable {
            if(Lable=="Phương thức thanh toán")
            {
                navController.navigate(Screen.PaymentView.route)
            }
            else if(Lable=="Địa chỉ giao hàng")
            {
                navController.navigate(Screen.DetailView.route)
            }
            else if(Lable=="Khuyến mại")
            {
                navController.navigate(Screen.LoginView.route)
            }
        }
    ){
        val (text,value,icon)=createRefs()
        Text(text = Lable, style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color.Black
        ), modifier = Modifier
            .padding(8.dp)
            .constrainAs(text) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(text = Value, style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(value) {
                    start.linkTo(parent.start)
                    top.linkTo(text.bottom)
                })
        Icon(Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(icon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(8.dp))

    }
}
@Composable
fun BillCart(Lable:String,
             Text:String,
             Value: String)
{
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(0.2f.dp, color = Color.Gray))) {
        val (priceText,numberOfDrink,priceValue)=createRefs()
        Text(text = Lable, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
        Text(text = Text, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(numberOfDrink) {
                    start.linkTo(parent.start)
                    top.linkTo(priceText.bottom)
                })
        Text(text = Value, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
            modifier = Modifier
                .constrainAs(priceValue) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(8.dp))

    }
}
@Composable
fun BottomCartBar()
{
    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = colorResource(id = R.color.lightGray))
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Tổng tiền", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
            Text(
                text = "234000 vnd", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.brown)
                )
            )
        }
        Button(
            onClick = {

            },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.brown)
            ),
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Đặt đơn hàng", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CartPreview()
{
    val navController = rememberNavController()
    val drink = Drink(
        Name = "Cà phê sữa đá",
        Price = 25000,
        Recipe = "Cà phê, sữa đặc, đá",
        Description = "da",
        ImageURL = Image("")
    )
    CartScreen(navController = navController, drink = drink)

}