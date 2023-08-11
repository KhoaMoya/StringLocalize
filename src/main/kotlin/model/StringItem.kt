package model

import Languages

data class StringItem(
    val value: String,
    val language: Languages,
    val needTranslate: Boolean = true
) {

    fun translateTo(targetLanguage: Languages): StringItem {
        val translatedText =
            Translator.translateText(sourceText = value, sourceLanguage = language, targetLanguage = targetLanguage)
        return this.copy(value = translatedText, language = targetLanguage)
    }
}
