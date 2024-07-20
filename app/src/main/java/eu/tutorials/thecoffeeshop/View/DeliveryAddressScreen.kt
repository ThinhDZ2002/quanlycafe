package eu.tutorials.thecoffeeshop.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.thecoffeeshop.R

@Composable
fun DeliveryAddressScreen(navController: NavController)
{
    Scaffold(topBar = {
        TopAppBar(navController = navController, titleName ="Địa chỉ giao hàng" )
    },
        bottomBar = {BottomAddressBar()}) {
        Column(modifier = Modifier.padding(it)) {
            DeliveryAddressItem()
            DeliveryAddressItem()
            DeliveryAddressItem()
        }
    }
}
@Composable
fun DeliveryAddressItem()
{
    Row (modifier = Modifier
        .border(BorderStroke(1.dp, color = Color.Gray))
        .padding(top = 16.dp, bottom = 16.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            AddressInfo("Họ và Tên:","Trần Văn Thịnh")
            AddressInfo("Điện thoại:","0326485336")
            AddressInfo("Địa chỉ:","Bình Xuyên, Vĩnh Phúc")
        }
        RadioButton(selected =true ,
            onClick = {},
            colors= RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Black,
                disabledSelectedColor = Color.Gray,
                disabledUnselectedColor = Color.Gray
            ))
    }
}
@Composable
fun AddressInfo(_lable:String,
                _value: String)
{
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)) {
        val verticalGuidLine25=createGuidelineFromStart(0.25f)
        val (lable,value)=createRefs()
        Text(text = _lable, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
            modifier = Modifier
                .constrainAs(lable) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Text(text = _value, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
            modifier = Modifier
                .constrainAs(value) {
                    start.linkTo(verticalGuidLine25)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        )
    }
}
@Composable
fun BottomAddressBar()
{
    Button(onClick = {  },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.brown)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Thêm địa chỉ giao hàng",
            fontSize = 18.sp,
            modifier = Modifier.padding(6.dp))
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddressPreview()
{
    val navController = rememberNavController()
    DeliveryAddressScreen(navController = navController)
}