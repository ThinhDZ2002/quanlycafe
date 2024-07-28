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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import eu.tutorials.thecoffeeshop.Model.Drink
import eu.tutorials.thecoffeeshop.R
@Composable
fun DetailScreen(navController:(Drink)->Unit,
                 drink:Drink,
                 navControllerBack: NavController)
{
    var checkSuaDac by remember {
        mutableStateOf(false)
    }
    var checkTranChau by remember {
        mutableStateOf(false)
    }
    var checkDuaKho by remember {
        mutableStateOf(false)
    }
    var checkThachDua by remember {
        mutableStateOf(false)
    }
    var checkDuongDen by remember {
        mutableStateOf(false)
    }
    var quantity by remember { mutableIntStateOf(1) }
    var checkQuantity by remember {
        mutableStateOf(false)
    }
    if (quantity==1)
        checkQuantity=false
    else
        checkQuantity=true
    Scaffold(topBar = {TopAppBar(navController = navControllerBack, titleName =drink.Name )},
        bottomBar ={ BottomBar(
        drink = drink,
        checkSuaDac = checkSuaDac,
        checkTranChau = checkTranChau,
        checkDuaKho = checkDuaKho,
        checkThachDua = checkThachDua,
        checkDuongDen = checkDuongDen,
        quantity =quantity,
        navController=navController
    )}) { it->
        Column (modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())){
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (imgThumnail,detailInfo)=createRefs()
                val horizontalGuideLine70=createGuidelineFromTop(0.7f)
                Image(painterResource(id = R.drawable.coffee_leaves),
                    contentDescription =null ,
                    modifier = Modifier
                        .constrainAs(imgThumnail) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .fillMaxWidth())
                Column(
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .background(color = Color.White)
                        .border(
                            BorderStroke(2.dp, color = Color.Gray)
                        )
                        .padding(8.dp)
                        .constrainAs(detailInfo) {
                            start.linkTo(parent.start)
                            top.linkTo(horizontalGuideLine70)
                        }
                ) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically){
                        Text(text = drink.Name, style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        ))
                        Text(text = "${drink.Price} VND", style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        ))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = drink.Recipe, style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Red
                        )
                        )
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.border(BorderStroke(0.5f.dp,Color.Black),
                                shape = RectangleShape)
                        )
                        {
                            Text(text = "-", style = TextStyle(
                                fontSize = 18.sp
                            ), modifier = Modifier
                                .border(
                                    border = BorderStroke(0.5f.dp, color = Color.Black.copy(0.7f)),
                                    shape = RectangleShape
                                )
                                .clickable(enabled = checkQuantity) { quantity-- }
                                .padding(10.dp, 2.dp)
                            )
                            Text(text = "$quantity", style = TextStyle(
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
                                .clickable { quantity++ }
                                .padding(8.dp, 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Xếp hạng và đánh giá")
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                    }
                }
            }
            Column (modifier = Modifier
                .padding(16.dp, 8.dp)
                .border(
                    BorderStroke(2.dp, color = Color.Gray),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)){
                var drinkType by remember {
                    mutableStateOf("hot")
                }
                var sizeType by remember {
                    mutableStateOf("small")
                }
                var sugarType by remember {
                    mutableStateOf("normal")
                }
                var iceType by remember {
                    mutableStateOf("normal")
                }

                Text(text = "Tùy chỉnh", style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Đồ uống", style = TextStyle(
                        fontSize = 16.sp
                    ))
                    Row {
                        Text(text = "Nóng", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (drinkType == "hot") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { drinkType = "hot" }
                            .padding(8.dp)

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Lạnh", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .clickable { drinkType = "cool" }
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (drinkType == "cool") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Kích thước", style = TextStyle(
                        fontSize = 16.sp)
                    )
                    Row {
                        Text(text = "Nhỏ", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (sizeType == "small") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { sizeType = "small" }
                            .padding(8.dp)

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Vừa", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (sizeType == "medium") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { sizeType = "medium" }
                            .padding(8.dp)

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Lớn", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (sizeType == "big") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { sizeType = "big" }
                            .padding(8.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Đường", style = TextStyle(
                        fontSize = 16.sp)
                    )
                    Row {
                        Text(text = "Bình thường", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (sugarType == "normal") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )

                            .padding(8.dp)
                            .clickable { sugarType = "normal" }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Giảm bớt", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (sugarType == "decrease") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                            .clickable { sugarType = "decrease" }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Đá", style = TextStyle(
                        fontSize = 16.sp)
                    )
                    Row {
                        Text(text = "Bình thường", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (iceType == "normal") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                            .clickable { iceType = "normal" }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Giảm bớt", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = if (iceType == "decrease") {
                                        colorResource(id = R.color.brown)
                                    } else {
                                        Color.Black
                                    }
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                            .clickable { iceType = "decrease" }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column (modifier = Modifier
                .padding(16.dp, 8.dp)
                .border(
                    BorderStroke(2.dp, color = Color.Gray),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
            ){
                Column {
                    Text(text = "Thêm lựa chọn khác", style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                    )

                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)){

                        Text(text = "Sữa đặc", style = TextStyle(
                            fontSize = 16.sp
                        ))
                        Row (horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable (
                                onClick = {checkSuaDac=!checkSuaDac},
                                role = Role.RadioButton
                            )){
                            Text(text = "+5.000 VND", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.black)
                            ))
                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(selected = checkSuaDac,
                                onClick = null,
                                colors= RadioButtonDefaults.colors(
                                    selectedColor = Color.Red,
                                    unselectedColor = Color.Black,
                                    disabledSelectedColor = Color.Gray,
                                    disabledUnselectedColor = Color.Gray
                                ))
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)){

                        Text(text = "Trân châu", style = TextStyle(
                            fontSize = 16.sp
                        ))
                        Row (horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable (
                                onClick = {checkTranChau=!checkTranChau},
                                role = Role.RadioButton
                            )){
                            Text(text = "+6.000 VND", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ))
                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(selected = checkTranChau,
                                onClick = null,
                                colors=RadioButtonDefaults.colors(
                                    selectedColor = Color.Red,
                                    unselectedColor = Color.Black,
                                    disabledSelectedColor = Color.Gray,
                                    disabledUnselectedColor = Color.Gray
                                ))
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)){

                        Text(text = "Dừa khô", style = TextStyle(
                            fontSize = 16.sp
                        ))
                        Row (horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable (
                                onClick = {checkDuaKho=!checkDuaKho},
                                role = Role.RadioButton
                            )){
                            Text(text = "+3.000 VND", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ))
                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(selected = checkDuaKho,
                                onClick = null,
                                colors=RadioButtonDefaults.colors(
                                    selectedColor = Color.Red,
                                    unselectedColor = Color.Black,
                                    disabledSelectedColor = Color.Gray,
                                    disabledUnselectedColor = Color.Gray
                                ))
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)){

                        Text(text = "Thạch dừa", style = TextStyle(
                            fontSize = 16.sp
                        ))
                        Row (horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable (
                                onClick = {checkThachDua=!checkThachDua},
                                role = Role.RadioButton
                            )){
                            Text(text = "+7.000 VND", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ))
                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(selected = checkThachDua,
                                onClick = null,
                                colors=RadioButtonDefaults.colors(
                                    selectedColor = Color.Red,
                                    unselectedColor = Color.Black,
                                    disabledSelectedColor = Color.Gray,
                                    disabledUnselectedColor = Color.Gray
                                ))
                        }
                    }
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)){

                        Text(text = "Đường đen", style = TextStyle(
                            fontSize = 16.sp
                        ))
                        Row (horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable (
                                onClick = {checkDuongDen=!checkDuongDen},
                                role = Role.RadioButton
                            )){
                            Text(text = "+10.000 VND", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ))
                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(selected = checkDuongDen,
                                onClick = null,
                                colors=RadioButtonDefaults.colors(
                                    selectedColor = Color.Red,
                                    unselectedColor = Color.Black,
                                    disabledSelectedColor = Color.Gray,
                                    disabledUnselectedColor = Color.Gray
                                ))
                        }
                    }
                }
            }
            Column (modifier = Modifier.padding(16.dp,8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start){
                Text(text = "Ghi chú", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ), modifier = Modifier.padding(8.dp))
                var noteText by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(value = noteText,
                    onValueChange ={ noteText=it},
                    placeholder = {Text(text = "Không bắt buộc")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    minLines = 4
                )
            }
        }
    }
}
@Composable
fun TopAppBar(navController: NavController,
              titleName:String){
    Row (horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .height(40.dp)){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription =null,
            modifier = Modifier.clickable {navController.popBackStack()})
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = titleName, style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp
        ))
    }
}
@Composable
fun BottomBar(drink: Drink,
              checkSuaDac:Boolean,
              checkTranChau:Boolean,
              checkDuaKho:Boolean,
              checkThachDua:Boolean,
              checkDuongDen:Boolean,
              quantity:Int,
              navController:(Drink)->Unit
            )
{
    var total by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect( checkSuaDac, checkTranChau, checkDuaKho, checkThachDua, checkDuongDen,quantity) {
        var newTotal = drink.Price*quantity
        if (checkSuaDac) newTotal += 5000
        if (checkTranChau) newTotal += 6000
        if (checkDuaKho) newTotal += 3000
        if (checkThachDua) newTotal += 7000
        if (checkDuongDen) newTotal += 10000
        total = newTotal
    }
    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = colorResource(id = R.color.lightGray))
            .padding(8.dp)
        ){
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start){
            Text(text = "Tổng tiền", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            )
            Text(text = "$total VND",style= TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.brown)
            )
            )
        }
        Button(onClick = {
            navController(drink)
        },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.brown)
            ),
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        ) {
            Text(text = "Thêm vào giỏ hàng", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            )
        }
    }
}
//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun DetailPreView()
//{
//    val navController = rememberNavController()
//    val drink = Drink(
//        Name = "Cà phê sữa đá",
//        Price = 25000,
//        Recipe = "Cà phê, sữa đặc, đá",
//        Description = "da",
//        ImageURL = Image("")
//    )
//    DetailScreen(navController = navController, drink = drink)
//}