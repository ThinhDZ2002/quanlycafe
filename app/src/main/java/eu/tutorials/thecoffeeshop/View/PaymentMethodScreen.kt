package eu.tutorials.thecoffeeshop.View

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.thecoffeeshop.R

@Composable
fun PaymentMethodScreen(navController: NavController)
{
    var selectedPaymentMethod by remember {
        mutableStateOf(-1)
    }
    Scaffold(topBar = {
        TopAppBar(navController = navController, titleName = "Phương thức thanh toán")
    }) {
        Column(modifier = Modifier.padding(it)) {
            PaymentItem(imgIcon = R.drawable.icon_cash,
                "Thanh toán tiền mặt",
                "(Thanh toán khi nhận hàng)",
                isSelected = selectedPaymentMethod == 0,
                onSelected = { selectedPaymentMethod = 0})
            PaymentItem(imgIcon = R.drawable.icon_credit_card,
                "Credit or debit card",
                "(Thẻ visa hoặc Mastercard)",
                isSelected = selectedPaymentMethod == 1,
                onSelected = { selectedPaymentMethod = 1 })
            PaymentItem(imgIcon = R.drawable.icon_bank,
                "Chuyển khoản ngân hàng",
                "(Tự động xa nhận)",
                isSelected = selectedPaymentMethod == 2,
                onSelected = { selectedPaymentMethod = 2 })
            PaymentItem(imgIcon = R.drawable.icon_zalopay,
                "ZaloPay",
                "(Tự động xa nhận)",
                isSelected = selectedPaymentMethod == 3,
                onSelected = { selectedPaymentMethod = 3 })
        }
    }
}
@Composable
fun PaymentItem(
                @DrawableRes imgIcon:Int,
                paymentName:String,
                description:String,
                isSelected: Boolean,
                onSelected: () -> Unit)
{
    ConstraintLayout(modifier = Modifier
        .border(BorderStroke(0.5f.dp, Color.Gray))
        .padding(8.dp, 16.dp)
        .fillMaxWidth()
        ) {
        val (icon,paymentMethod,paymentDescription,radio)=createRefs()
        val verticalGuiLine15=createGuidelineFromStart(0.15f)
        Image(painter =painterResource(id = imgIcon) ,
            contentDescription =null,
            modifier = Modifier
                .size(40.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(verticalGuiLine15)
                }
            )
        Text(text = paymentName, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
            modifier = Modifier.constrainAs(paymentMethod){
                start.linkTo(verticalGuiLine15)
                top.linkTo(parent.top)
            })
        Text(text = description, style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
            modifier = Modifier.constrainAs(paymentDescription){
                start.linkTo(verticalGuiLine15)
                bottom.linkTo(icon.bottom)
            })
        RadioButton(selected =isSelected ,
            onClick = onSelected,
            modifier = Modifier.constrainAs(radio){
                end.linkTo(parent.end)
                top.linkTo(icon.top)
                bottom.linkTo(icon.bottom)
            },
            colors= RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Black,
                disabledSelectedColor = Color.Gray,
                disabledUnselectedColor = Color.Gray
            ))
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PaymentPreview()
{
    val navController = rememberNavController()
    PaymentMethodScreen(navController = navController)
}