package freelance.demoapp.data.database.shareprf

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// 1. Khởi tạo Singleton DataStore ở Top-level
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_config")

class AppPreferences(@ApplicationContext val context: Context) {
    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val TYPE_CURRENCY = stringPreferencesKey("type_currency")
    }
    private val store = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences()) // Nếu lỗi file, trả về rỗng
            } else {
                throw exception
            }
        }


    val isDarkModeFlow: Flow<Boolean> = store
        .map { preferences ->
            // Trả về false nếu chưa có dữ liệu (Default value)
            preferences[IS_DARK_MODE] ?: false 
        }

    suspend fun setDarkMode(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDark
        }
    }

    suspend fun setTypeCurrency(type: String){
        context.dataStore.edit { preferences ->
            preferences[TYPE_CURRENCY] = type
        }
    }

    fun getTypeCurrency() : Flow<String> {
        return store.map {
            it[TYPE_CURRENCY] ?: "USD"
        }
    }
    
    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}