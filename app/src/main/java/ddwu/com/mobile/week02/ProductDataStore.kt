package ddwu.com.mobile.week02

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "product_store")

object ProductDataStore {

    private val gson = Gson()
    private val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
    private val PURCHASE_PRODUCTS_KEY = stringPreferencesKey("purchase_products")
    private val WISHLIST_PRODUCTS_KEY = stringPreferencesKey("wishlist_products")

    suspend fun saveHomeProduts(context: Context, products: List<ProductData>){
        val json = gson.toJson(products)
        context.dataStore.edit {it[HOME_PRODUCTS_KEY]=json}
    }

    fun getHomeProducts(context: Context) : Flow<List<ProductData>>{
        return context.dataStore.data.map{preferences ->
            val json = preferences[HOME_PRODUCTS_KEY]?: return@map emptyList()
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(json,type)
        }
    }

    suspend fun savePurchaseProducts(context: Context, products: List<ProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit{it[PURCHASE_PRODUCTS_KEY] = json}
    }

    fun getPurchaseProducts(context: Context) : Flow<List<ProductData>>{
        return context.dataStore.data.map{ preferences ->
            val json = preferences[PURCHASE_PRODUCTS_KEY]?: return@map emptyList()
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(json,type)

        }
    }

    suspend fun saveWishlistProducts(context: Context, products: List<ProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit{it[WISHLIST_PRODUCTS_KEY] = json}
    }

    fun getWishlistProducts(context: Context) : Flow<List<ProductData>>{
        return context.dataStore.data.map{preferences ->
            val json = preferences[WISHLIST_PRODUCTS_KEY] ?: return@map emptyList()
            val type = object: TypeToken<List<ProductData>>() {}.type
            gson.fromJson(json, type)
        }
    }

}