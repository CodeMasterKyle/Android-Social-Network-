package com.incorp.ichat
// ApiService.kt



// ApiService.kt
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.FormBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ApiService {
    private val client = OkHttpClient()

    suspend fun signIn(username: String, password: String): AuthResponse? {
        val url = "https://api.dkon.app/api/v04.12.2024/method/account.signIn"

        // Создаем тело запроса с использованием FormBody
        val formBody = FormBody.Builder()
            .add("clientId", "1302")
            .add("username", username)
            .add("password", password)
            .build()

        // Создаем POST-запрос
        val request = Request.Builder()
            .url(url)
            .post(formBody) // Указываем, что это POST-запрос с формой
            .build()

        return withContext(Dispatchers.IO) {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    Gson().fromJson(it, AuthResponse::class.java)
                }
            } else {
                null
            }
        }
    }


    // ApiService.kt


    suspend fun getDialogs(accessToken: String, accountId: String): DialogsResponse? {
        val url = "https://api.dkon.app/api/v04.12.2024/method/dialogs_new.get"

        // Создаем тело запроса с использованием FormBody
        val formBody = FormBody.Builder()
            .add("clientId", "1302")
            .add("accountId", accountId)
            .add("accessToken", accessToken)
            .build()

        // Создаем POST-запрос
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        return withContext(Dispatchers.IO) {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    Gson().fromJson(it, DialogsResponse::class.java)
                }
            } else {
                null
            }
        }
    }

}
