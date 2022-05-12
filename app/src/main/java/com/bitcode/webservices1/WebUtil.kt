package com.bitcode.webservices1

import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class WebUtil {

    companion object {

        fun makeSimpleHTTPRequest(url: String)  {
            var url = URL(url)
            var httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.connect()

            log("Response Code - ${httpURLConnection.responseCode}")
            log("Response Message - ${httpURLConnection.responseMessage}")
            log("Request Method - ${httpURLConnection.requestMethod}")
            log("Content Length - ${httpURLConnection.contentLength}")

            var data = ByteArray(1024 * 1)
            var count = 0
            while (count != -1) {
                count = httpURLConnection.inputStream.read(data)
                if (count == -1) {
                    break
                }
                Log.e("tag", String(data, 0, count))
            }
            httpURLConnection.inputStream.close()

        }

        fun log(text: String) {
            Log.e("tag", text)
        }

        fun getUsersList(pageNumber: Int) : ArrayList<User>? {

            var url = URL("https://reqres.in/api/users?page=$pageNumber")
            var httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"

            httpURLConnection.connect()

            if (httpURLConnection.responseCode != 200) {
                log("Fetching users failed...")
                return null
            }

            var responseBuffer = StringBuffer()
            var data = ByteArray(1024 * 1)
            var count = 0;

            while (true) {
                count = httpURLConnection.inputStream.read(data)
                if (count == -1) {
                    break
                }
                responseBuffer.append(String(data, 0, count))
            }

            httpURLConnection.inputStream.close()
            log(responseBuffer.toString())

            var usersList = ArrayList<User>()

            var jRoot = JSONObject(responseBuffer.toString())
            var jUsers = jRoot.getJSONArray("data")

            for (i in 0..jUsers.length() - 1) {
                var jUser = jUsers.getJSONObject(i)

                usersList.add(
                    User(
                        jUser.getInt("id"),
                        jUser.getString("email"),
                        jUser.getString("first_name") + " " + jUser.getString("last_name"),
                        jUser.getString("avatar")
                    )
                )
            }

            return usersList

        }

        fun getUsersListWithGson(pageNumber: Int) : UserResponse? {

            var url = URL("https://reqres.in/api/users?page=$pageNumber")
            var httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"

            httpURLConnection.connect()

            if (httpURLConnection.responseCode != 200) {
                log("Fetching users failed...")
                return null
            }

            var responseBuffer = StringBuffer()
            var data = ByteArray(1024 * 1)
            var count = 0;

            while (true) {
                count = httpURLConnection.inputStream.read(data)
                if (count == -1) {
                    break
                }
                responseBuffer.append(String(data, 0, count))
            }

            httpURLConnection.inputStream.close()
            log(responseBuffer.toString())

            var gson = Gson()
            var userResponse = gson.fromJson<UserResponse>(
                responseBuffer.toString(),
                UserResponse::class.java
            )

            return userResponse

        }

    }
}