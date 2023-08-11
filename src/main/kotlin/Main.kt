import model.StringsResourceIO
import model.Translator
import java.io.File

fun main(args: Array<String>) {

    val allStringsFile = StringsResourceIO.readAllDefaultStringsFiles(
        projectFolder = File("/Users/phamkhoa/Data/Freelance/Browser/FastVideoDownloader"),
        defaultLanguage = Languages.English_UnitedKingdom
    )
    allStringsFile.forEach {
        it.translateToLanguages(listOf(Languages.Vietnamese, Languages.Japanese), false)
    }

    println("total translated string: ${Translator.count}")
}