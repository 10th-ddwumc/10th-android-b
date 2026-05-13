package ddwu.com.mobile.week07

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ProductItem(product: ProductData, onHeartClick: ((ProductData)->Unit)?=null) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box{
            Image (
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

            if(onHeartClick!=null) {
                IconButton(
                    onClick = {onHeartClick(product)},
                    modifier = Modifier.align(Alignment.TopEnd)
                ){
                    Image(
                        painter=painterResource(
                            id = if(product.isLiked) R.drawable.heart_filled
                            else R.drawable.heartstraight
                        ),
                        contentDescription = "하트",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        if(product.description.isNotEmpty()) {
            Text(
                text=product.description,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Text(
            text = product.price,
            fontSize = 13.sp
        )
    }
}