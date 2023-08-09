package model

import Languages

object Translator {

    var count = 0

    fun translateText(sourceText: String, sourceLanguage: Languages, targetLanguage: Languages): String {
        count ++
        return "translated_$sourceText"
    }
}