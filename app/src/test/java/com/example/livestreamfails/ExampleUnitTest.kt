package com.example.livestreamfails

import org.json.JSONException
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        generateJSONMessage("","","")
    }

     fun generateJSONMessage(dst: String, src: String, msg: String): String {
        var resultData = ""
        val body = JSONObject()
        val jsonData = JSONObject()
        val attributes = JSONObject()
        try {
            //fill atributes
            attributes.put("destination", "3809978764")
            attributes.put("source", "380486644453")
            attributes.put("content", "Hello World!")

            //fill data
            jsonData.put("type", "outbound_messages")
            jsonData.put("attributes", attributes)

            //fill body
            body.put("data", jsonData)

            resultData = body.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return resultData

    }
}
