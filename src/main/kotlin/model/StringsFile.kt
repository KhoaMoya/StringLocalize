package model

import Languages

data class StringsFile(
    val filePath: String,
    val language: Languages,
    val listStrings: LinkedHashMap<String, StringItem>
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
        listStrings.forEach { (id, stringItem) ->
            val hasNotTranslated = targetStringsFile.listStrings[id]?.value.isNullOrBlank()
            if (stringItem.needTranslate) {
                if (shouldOverride || hasNotTranslated) {
                    val translatedStringItem = stringItem.translateTo(targetStringsFile.language)
                    targetStringsFile.listStrings[id] = translatedStringItem
                }
            } else {
                targetStringsFile.listStrings[id] = stringItem.copy(language = targetStringsFile.language)
            }
        }
    }

}