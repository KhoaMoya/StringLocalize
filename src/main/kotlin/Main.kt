import model.StringsResourceIO
import model.Translator
import java.io.File

fun main(args: Array<String>) {
//    translateSingleText("Dear user!\\n\\nWe use Google Admob to show ads. Ads support our work, and enable further development of this app. In line with the new European Data Protection Regulation (GDPR), we need your consent to serve ads tailored for you.")
    translateSingleText("Hello!\n\nMy nam is Khoa")
//    translateStringsFile()
}

fun translateStringsFile() {
    val allStringsFile = StringsResourceIO.readAllDefaultStringsFiles(
        projectFolder = File("/Users/phamkhoa/Data/Freelance/Browser/FastVideoDownloader"),
        defaultLanguage = Languages.English_UnitedKingdom
    )
    // dịch file string.xml sang các tiếng Spanish, Filipino, French France, Indonesian, Italian, Malay, Portuguese Portugal, Telugu, Hindi, Thai, Korean, Russian, Chinese Simplified, Japanese, Turkish, Dutch
    allStringsFile.forEach {
        it.translateToLanguages(
            listOf(
                Languages.Spanish_Spain,
                Languages.German,
                Languages.Filipino,
                Languages.French_France,
                Languages.Indonesian,
                Languages.Italian,
                Languages.Malay,
                Languages.Portuguese_Portugal,
                Languages.Telugu,
                Languages.Hindi,
                Languages.Thai,
                Languages.Korean,
                Languages.Russian,
                Languages.Chinese_Simplified,
                Languages.Japanese,
                Languages.Vietnamese,
                Languages.Turkish,
                Languages.Arabic,
                Languages.Dutch,
            ),
            true
        )
    }

    println("total translated string: ${Translator.count}")
}

fun translateSingleText(text: String) {
    val targetLanguages = listOf(
        Languages.English_UnitedKingdom,
        Languages.Spanish_Spain,
        Languages.German,
        Languages.Filipino,
        Languages.French_France,
        Languages.Indonesian,
        Languages.Malay,
        Languages.Portuguese_Portugal,
        Languages.Telugu,
        Languages.Hindi,
        Languages.Thai,
        Languages.Korean,
        Languages.Russian,
        Languages.Chinese_Simplified,
        Languages.Japanese,
        Languages.Vietnamese,
        Languages.Turkish,
        Languages.Arabic,
        Languages.Dutch,
    )
    targetLanguages.forEach { target ->
        val result = Translator.translateText(
            sourceText = text,
            sourceLanguage = Languages.English_UnitedKingdom,
            targetLanguage = target
        )
        println(target.name + ": " + result)
    }
}