package com.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread2(var handler: Handler) : AsyncTask<Any?, Any?, UserResponseNew?>() {

    override fun doInBackground(vararg params: Any?): UserResponseNew? {
        return WebUtil.getUsersListWithGsonUpdate1(1)
    }

    override fun onPostExecute(result: UserResponseNew?) {
        super.onPostExecute(result)
        var message = Message()
        message.obj = result

        handler.sendMessage(message)
    }
}