package model

import Languages
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder

object Translator {

    var count = 0

    private val client: OkHttpClient = OkHttpClient().newBuilder().build()

    fun translateText(sourceText: String, sourceLanguage: Languages, targetLanguage: Languages): String {
        count++

        val response = requestTranslate(
            text = sourceText,
            source = sourceLanguage.getCode(),
            target = targetLanguage.getCode()
        )
        val result = handleTranslateResponse(response)
//            ?.replace("'", "\'")
//            ?.replace("\\'", "\'")
        return result ?: ""
    }

    private fun handleTranslateResponse(response: String?): String? {
        if (response == null) return null
//        println(response)

        val resultBuilder = StringBuilder()
        val arrayObject = JSONObject("{ \"data\": $response}").getJSONArray("data").getJSONArray(0)
        arrayObject.forEach { item ->
            (item as? JSONArray)?.let { arr ->
//                println(arr[1].toString() + " -> " + arr[0].toString())
                resultBuilder.append(arr[0].toString())
            }
        }
        return resultBuilder.toString()
    }

    private fun requestTranslate(text: String, source: String, target: String): String? {
        val encoded = encodeSource(text)
        val request: Request = Request.Builder()
            .url("https://translate.googleapis.com/translate_a/single?client=gtx&sl=$source&tl=$target&dt=t&q=$encoded")
            .build()
        val response: Response = client.newCall(request).execute()
        return response.body().string()
    }

    private fun encodeSource(source: String): String {
        return URLEncoder.encode(source, "utf-8")
    }
}