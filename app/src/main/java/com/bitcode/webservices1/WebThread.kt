package com.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread(
    var handler: Handler? = null
) : AsyncTask<Any?, Any?, ArrayList<User>?>() {

    override fun doInBackground(vararg params: Any?): ArrayList<User>? {

        //WebUtil.makeSimpleHTTPRequest("https://bitcode.in")
        //WebUtil.makeSimpleHTTPRequest("https://yahoo.com")

        return WebUtil.getUsersList(1)
    }

    override fun onPostExecute(result: ArrayList<User>?) {
        super.onPostExecute(result)
        if(handler != null) {
            var message = Message()
            message.obj = result
            handler!!.sendMessage(message)
        }
    }

}