package ddwu.com.mobile.week07

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen() {

    var user by remember {mutableStateOf<UserData?>(null)}
    var userList by remember {mutableStateOf<List<UserData>>(emptyList())}

    LaunchedEffect(Unit) {
        try{
            val response = RetrofitClient.api.getUser(1)
            user = response.data

            val listResponse = RetrofitClient.api.getUserList()
            userList = listResponse.data
        } catch(e:Exception){
            e.printStackTrace()
        }
    }

    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))

            AsyncImage(
                model = user?.avatar,
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${user?.firstName?: ""} ${user?.lastName?: ""}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button (
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(150.dp)
            ) {
                Text (text = "프로필 수정", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuIcon(image = R.drawable.order, label = "주문")
                MenuIcon(image = R.drawable.pass, label = "패스")
                MenuIcon(image = R.drawable.event, label = "이벤트")
                MenuIcon(image = R.drawable.settings, label = "설정")
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 10.dp)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(modifier=Modifier.weight(1f)){
                    Text(text="나이키 멤버 혜택", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text="0개 사용 가능", fontSize = 12.sp, color = Color.Gray)
                }
                Image(
                    painter = painterResource(id=R.drawable.caretright),
                    contentDescription = "화살표",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 10.dp)
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text="팔로잉 (${userList.size})",fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text="편집", fontSize = 12.sp, color = Color.Gray)
            }

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(userList){user ->
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = user.avatar,
                            contentDescription = user.firstName,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 10.dp)
        }

        item {
            Spacer(modifier = Modifier.height(130.dp))

            Text(
                text = "회원 가입일: 2025년 9월",
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun MenuIcon(image:Int, label: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id=image),
            contentDescription = label,
            modifier = Modifier.size(26.dp)
        )
        Text (text = label, fontSize = 12.sp)
    }
}