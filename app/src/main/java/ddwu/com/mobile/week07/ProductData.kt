package ddwu.com.mobile.week07

data class ProductData(
    val id: Int,
    val name: String,
    val price: String,
    val image: Int,
    val description: String="",
    val isLiked: Boolean = false
)
