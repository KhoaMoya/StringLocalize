import model.StringsResourceIO
import model.Translator
import java.io.File

fun main(args: Array<String>) {

    val allStringsFile = StringsResourceIO.readAllDefaultStringsFiles(
        projectFolder = File("/Users/phamkhoa/Data/Freelance/Browser/FastVideoDownloader"),
        defaultLanguage = Languages.English_UnitedKingdom
    )
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
                Languages.Chinese_Traditional,
                Languages.Japanese,
                Languages.Vietnamese,
                Languages.Turkish,
                Languages.Arabic,
                Languages.Dutch,
            ),
            false
        )
    }

    println("total translated string: ${Translator.count}")
}