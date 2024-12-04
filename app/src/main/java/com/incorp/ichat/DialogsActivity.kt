package com.incorp.ichat

// DialogsActivity.kt

// DialogsActivity.kt
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogsActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val apiService = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        listView = findViewById(R.id.dialogsListView)

        // Получаем accessToken и accountId из Intent
        val accessToken = intent.getStringExtra("accessToken")
        val accountId = intent.getStringExtra("accountId")

        if (accessToken != null && accountId != null) {
            fetchDialogs(accessToken, accountId)
        } else {
            Toast.makeText(this, "Error retrieving access token or account ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchDialogs(accessToken: String, accountId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = apiService.getDialogs(accessToken, accountId)
            if (response != null && !response.error) {
                // Обновляем ListView с полученными данными
                val adapter = DialogsAdapter(this@DialogsActivity, response.chats)
                listView.adapter = adapter
            } else {
                Toast.makeText(this@DialogsActivity, "Failed to fetch dialogs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
