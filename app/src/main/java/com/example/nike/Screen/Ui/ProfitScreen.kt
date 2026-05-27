package com.example.nike.Screen.Ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.nike.Model.ProfitViewModel
import com.example.nike.Screen.Ui.Data.UserData
import com.example.nike.R
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog

@Composable
fun ProfitScreen(
    onEditClick: () -> Unit,
    viewModel: ProfitViewModel = viewModel()
) {
    val profile by viewModel.profile.collectAsState()
    val followingList by viewModel.followingList.collectAsState()

    // 화면 처음 들어왔을 때 데이터 불러오기
    LaunchedEffect(Unit) {
        viewModel.loadMyProfile()
        viewModel.loadFollowingList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 프로필 이미지
        AsyncImage(
            model = profile?.avatar,
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 닉네임
        Text(
            text = if (profile != null) {
                "${profile!!.first_name} ${profile!!.last_name}"
            } else {
                "닉네임"
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onEditClick,
            modifier = Modifier
                .width(170.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text(
                text = "프로필 수정",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProfitMenuItem(icon = R.drawable.archive, title = "주문")
            ProfitMenuItem(icon = R.drawable.identificationcard, title = "패스")
            ProfitMenuItem(icon = R.drawable.calendarblank, title = "이벤트")
            ProfitMenuItem(icon = R.drawable.gear, title = "설정")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 나이키 멤버 혜택
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = "나이키 멤버 혜택",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "0개 사용 가능",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            thickness = 3.dp,
            color = Color(0xFFDDDDDD)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 팔로잉 제목
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "팔로잉 (${followingList.size})",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "편집",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // 팔로잉 리스트
        FollowingRow(users = followingList)

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "회원 가입일: 2025년 9월",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun MenuItem(
    title: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 11.sp
        )
    }
}

@Composable
fun FollowingPager(
    users: List<UserData>
) {
    if (users.isEmpty()) {
        Text(
            text = "팔로잉 목록이 없습니다.",
            fontSize = 14.sp,
            color = Color.Gray
        )
        return
    }

    val pagerState = rememberPagerState(
        pageCount = { users.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        contentPadding = PaddingValues(horizontal = 30.dp),
        pageSpacing = 16.dp
    ) { page ->

        val user = users[page]

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = "팔로잉 프로필 이미지",
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.first_name,
                fontSize = 12.sp
            )
        }
    }
}
    @Composable
    fun ProfitMenuItem(
        icon: Int,
        title: String
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }

@Composable
fun FollowingRow(
    users: List<UserData>
) {
    var selectedUser by remember {
        mutableStateOf<UserData?>(null)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) { user ->

            AsyncImage(
                model = user.avatar,
                contentDescription = "팔로잉 이미지",
                modifier = Modifier
                    .size(90.dp)
                    .clickable {
                        selectedUser = user
                    },
                contentScale = ContentScale.Crop
            )
        }
    }

    // 이미지 확대 기능
    selectedUser?.let { user ->

        AlertDialog(
            onDismissRequest = {
                selectedUser = null
            },

            title = {
                Text("${user.first_name} ${user.last_name}")
            },

            text = {
                AsyncImage(
                    model = user.avatar,
                    contentDescription = "확대 이미지",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = ContentScale.Crop
                )
            },

            confirmButton = {
                Text(
                    text = "닫기",
                    modifier = Modifier.clickable {
                        selectedUser = null
                    }
                )
            }
        )
    }
}