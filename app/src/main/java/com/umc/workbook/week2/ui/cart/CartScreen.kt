package com.umc.workbook.week2.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umc.workbook.week2.R

@Composable
fun CartScreen(onNavigateToShop: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bagcircle),
                contentDescription = "장바구니 이미지"
            )
            Text(
                text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp, start = 40.dp, end = 40.dp)
            )
        }

        Button(
            onClick = onNavigateToShop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 43.dp, end = 38.dp, bottom = 83.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("주문하기")
        }
    }
}
