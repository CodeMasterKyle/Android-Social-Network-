package com.incorp.ichat

// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private val apiService = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация EditText и Button
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            performLogin(username, password)
        }
    }

   /* private fun performLogin(username: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = apiService.signIn(username, password)
            if (response != null && !response.error) {
                val accessToken = response.accessToken
                val accountId = response.accountId
                Toast.makeText(this@MainActivity, "Login successful! AccessToken: $accessToken", Toast.LENGTH_LONG).show()
                // Здесь можно сохранить accessToken и accountId для дальнейшего использования
            } else {
                Toast.makeText(this@MainActivity, "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
   private fun performLogin(username: String, password: String) {
       CoroutineScope(Dispatchers.Main).launch {
           val response = apiService.signIn(username, password)
           if (response != null && !response.error) {
               val accessToken = response.accessToken
               val accountId = response.accountId

               // Переход на DialogsActivity с передачей accessToken и accountId
               val intent = Intent(this@MainActivity, DialogsActivity::class.java).apply {
                   putExtra("accessToken", accessToken)
                   putExtra("accountId", accountId)
               }
               startActivity(intent)
               finish() // Закрываем MainActivity, если не нужно возвращаться назад
           } else {
               Toast.makeText(this@MainActivity, "Login failed!", Toast.LENGTH_SHORT).show()
           }
       }
   }

}
