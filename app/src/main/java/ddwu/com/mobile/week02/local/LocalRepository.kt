package ddwu.com.mobile.week02.local

import android.content.Context
import ddwu.com.mobile.week02.ProductData
import ddwu.com.mobile.week02.ProductDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(){
    suspend fun saveHomeProducts(context: Context, products: List<ProductData>){
        ProductDataStore.saveHomeProduts(context,products)
    }

    fun getHomeProducts(context: Context): Flow<List<ProductData>> {
        return ProductDataStore.getHomeProducts(context)
    }

    fun getPurchaseProducts(context: Context): Flow<List<ProductData>> {
        return ProductDataStore.getPurchaseProducts(context)
    }

    suspend fun savePurchaseProducts(context: Context, products: List<ProductData>) {
        ProductDataStore.savePurchaseProducts(context, products)
    }

    suspend fun saveWishlistProducts(context: Context, products: List<ProductData>) {
        ProductDataStore.saveWishlistProducts(context, products)
    }

    fun getWishlistProducts(context: Context): Flow<List<ProductData>> {
        return ProductDataStore.getWishlistProducts(context)
    }
}