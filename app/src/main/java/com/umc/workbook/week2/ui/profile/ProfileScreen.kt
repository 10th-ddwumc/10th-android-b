package com.umc.workbook.week2.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.UserData

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = uiState.user?.avatar,
            contentDescription = "프로필 사진",
            placeholder = painterResource(R.drawable.ic_profile),
            error = painterResource(R.drawable.ic_profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 24.dp)
                .size(84.dp)
                .clip(CircleShape)
        )

        Text(
            text = "${uiState.user?.firstName ?: ""}${uiState.user?.lastName ?: ""}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 30.dp)
        )

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 30.dp)
                .width(180.dp),
            shape = CircleShape
        ) {
            Text("프로필 수정", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 41.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStatItem(iconRes = R.drawable.ic_archive, label = "주문")
            Box(modifier = Modifier.width(1.dp).height(31.dp).background(Color(0xFFCDCDCD)))
            ProfileStatItem(iconRes = R.drawable.ic_identificationcard, label = "패스")
            Box(modifier = Modifier.width(1.dp).height(31.dp).background(Color(0xFFCDCDCD)))
            ProfileStatItem(iconRes = R.drawable.ic_calendarblank, label = "이벤트")
            Box(modifier = Modifier.width(1.dp).height(31.dp).background(Color(0xFFCDCDCD)))
            ProfileStatItem(iconRes = R.drawable.ic_gear, label = "설정")
        }

        Image(
            painter = painterResource(R.drawable.divider),
            contentDescription = "회색선",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 2.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 32.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("나이키 멤버 혜택", fontWeight = FontWeight.Bold, color = Color.Black)
                Text(
                    "0개 사용 가능",
                    fontSize = 12.sp,
                    color = Color(0xFF767676),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_caretright),
                contentDescription = "버튼"
            )
        }

        Image(
            painter = painterResource(R.drawable.divider__1_),
            contentDescription = "회색선",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 28.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("팔로잉 (${uiState.userList.size})", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("편집", color = Color(0xFF767676))
        }

        LazyRow(
            contentPadding = PaddingValues(start = 24.dp, top = 18.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(uiState.userList) { user ->
                FollowingItem(user = user)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 96.dp)
                .background(Color(0xFFF6F6F6)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "회원 가입일: 2025년 9월",
                fontSize = 12.sp,
                color = Color(0xFF767676),
                modifier = Modifier.padding(vertical = 17.dp)
            )
        }
    }
}

@Composable
private fun ProfileStatItem(iconRes: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label
        )
        Text(text = label, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
private fun FollowingItem(user: UserData) {
    AsyncImage(
        model = user.avatar,
        contentDescription = "프로필 이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(107.dp)
    )
}
