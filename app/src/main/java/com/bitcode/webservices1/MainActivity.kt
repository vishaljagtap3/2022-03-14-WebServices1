package com.bitcode.webservices1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.bitcode.webservices1.databinding.ActivityMainBinding
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMakeRequest.setOnClickListener {
            WebThread().execute(null)
        }

        binding.btnGetUserList.setOnClickListener {
            WebThread(UsersHandler()).execute(null)
        }
    }

    class UsersHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            for(user in msg.obj as ArrayList<User>) {
                Log.e("user", user.toString())
            }
        }
    }

}