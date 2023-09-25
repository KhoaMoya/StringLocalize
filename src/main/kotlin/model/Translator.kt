package model

import Languages
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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
        return result ?: ""
    }

    private fun handleTranslateResponse(response: String?): String? {
        if (response == null) return null
        val endIndex = response.indexOf("\",\"")
        return response.substring(4, endIndex)
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