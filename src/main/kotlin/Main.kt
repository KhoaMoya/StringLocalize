import model.StringsResourceIO
import model.Translator
import java.io.File

fun main(args: Array<String>) {

    val allStringsFile = StringsResourceIO.readAllDefaultStringsFiles(
        projectFolder = File("/Users/cdbl.khoapv/BBS5/tcb-business"),
        defaultLanguage = Languages.English
    )
    allStringsFile.forEach {
        it.translateToLanguages(listOf(Languages.VietNam), false)
    }

    println("total translated string: ${Translator.count}")
}