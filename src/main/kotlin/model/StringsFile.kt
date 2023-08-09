package model

import Languages
import StringsResourceIO

data class StringsFile(
    val filePath: String,
    val language: Languages,
    val listStrings: MutableMap<String, StringItem>
) {

    fun translateToLanguages(targets: List<Languages>, shouldOverride: Boolean) {
        if (listStrings.isEmpty()) return
        targets.forEach { targetLanguage ->
            val targetStringsFile = StringsResourceIO.getResourceStringFile(this, targetLanguage)
            translateAndUpdateStringsFile(targetStringsFile, shouldOverride)
            StringsResourceIO.writeStringsFile(targetStringsFile)
        }
    }

    private fun translateAndUpdateStringsFile(targetStringsFile: StringsFile, shouldOverride: Boolean) {
        listStrings.forEach { (id, stringSource) ->
            val hasNotTranslated = !targetStringsFile.listStrings[id]?.value.isNullOrBlank()
            if (hasNotTranslated) {
                println(stringSource.value)
            }
            if (shouldOverride || hasNotTranslated) {
                val translatedStringItem = stringSource.translateTo(targetStringsFile.language)
                targetStringsFile.listStrings[id] = translatedStringItem
            }
        }
    }

}