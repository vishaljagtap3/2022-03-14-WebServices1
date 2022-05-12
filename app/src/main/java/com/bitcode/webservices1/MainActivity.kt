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

        binding.btnGetUserListWithGson.setOnClickListener {
            WebThread1(UserHandler1()).execute(null)
        }
    }

    class UserHandler1  : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var userResponse = msg.obj as UserResponse
            Log.e("tag", "Page - ${userResponse.page}")
            Log.e("tag", "Per page: ${userResponse.per_page}")
            Log.e("tag", "total pages: ${userResponse.total_pages}")

            for(userNew in userResponse.data) {
                Log.e("tag", "${userNew.id} - ${userNew.email} - ${userNew.first_name + userNew.last_name} - ${userNew.avatar}")
            }
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