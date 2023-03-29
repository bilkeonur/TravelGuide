package com.ob.travelguide.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.ob.travelguide.databinding.ActivityLoginBinding
import com.ob.travelguide.repo.ILoginRepository
import com.ob.travelguide.util.DataStore
import com.ob.travelguide.util.Status
import com.ob.travelguide.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var pbLoading: ProgressBar

    @Inject
    lateinit var loginRepository: ILoginRepository

    private lateinit var dataStore:DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        pbLoading = binding.pbLoading
        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
                "${Util.baseUrl}oauth2/authenticate?client_id=${Util.clientId}" +
                        "&response_type=code" +
                        "&redirect_uri=${Util.redirectUri}"))

            startActivity(intent)
        }

        val accessToken = dataStore.getLogin()

        accessToken?.let {
            if(accessToken != "") {
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private suspend fun getAccessToken(code:String) {

        val response = loginRepository.getAccessToken(code)

        if (response.status == Status.SUCCESS) {
            pbLoading.visibility = View.GONE
            val accessToken = response.data?.accessToken
            println("Access Token : $accessToken")

            accessToken?.let {
                dataStore.setLogin(accessToken)
            }

            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            pbLoading.visibility = View.GONE
            println("Login Error")
        }
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data

        if(uri!=null && uri.toString().startsWith(Util.redirectUri)) {
            val code = uri.getQueryParameter("code")
            code?.let {
                println("Code : $code")

                pbLoading.visibility = View.VISIBLE

                lifecycleScope.launch {
                    getAccessToken(code)
                }
            }
        }
    }
}