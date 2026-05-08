package com.example.nike

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun ProfitScreen(
    onNavigateReProfit: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

        Button(
            onClick = onNavigateReProfit,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {

            Text("수정")

        }
    }
}