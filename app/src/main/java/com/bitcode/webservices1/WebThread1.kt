package com.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread1(var handler: Handler) : AsyncTask<Any?, Any?, UserResponse?>() {

    override fun doInBackground(vararg params: Any?): UserResponse? {
        return WebUtil.getUsersListWithGson(1)
    }

    override fun onPostExecute(result: UserResponse?) {
        super.onPostExecute(result)
        var message = Message()
        message.obj = result

        handler.sendMessage(message)
    }
}