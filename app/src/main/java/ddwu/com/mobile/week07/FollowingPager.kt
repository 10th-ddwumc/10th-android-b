package ddwu.com.mobile.week07

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun FollowingPager(userList: List<UserData>) {
    if (userList.isEmpty()) return

    val pagerState = rememberPagerState (pageCount = {userList.size})

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val user = userList[page]

        Column (
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = user.firstName,
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Text (
                text = "${user.firstName} ${user.lastName}",
                fontSize = 12.sp
            )
        }
    }
}