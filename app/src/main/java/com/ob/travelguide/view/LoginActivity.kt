package com.ob.travelguide.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ob.travelguide.databinding.ActivityLoginBinding
import com.ob.travelguide.service.FourSquareAPI
import com.ob.travelguide.util.Util
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error : ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
                "${Util.baseUrl}oauth2/authenticate?client_id=${Util.clientId}" +
                        "&response_type=code" +
                        "&redirect_uri=${Util.redirectUri}"))

            startActivity(intent)
        }
    }

    fun getAccessToken(code:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Util.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FourSquareAPI::class.java)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getAccessToken(Util.clientId, Util.clientSecret, "authorization_code", Util.redirectUri, code)

            withContext(Dispatchers.Main) {
                if(response != null) {
                    println("Access Token : ${response.accessToken}")
                    var intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    println("Access Token : Error")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var uri = intent.data

        if(uri!=null && uri.toString().startsWith(Util.redirectUri)) {
            val code = uri.getQueryParameter("code")
            code?.let {
                println("Code : ${code}")
                getAccessToken(code)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}