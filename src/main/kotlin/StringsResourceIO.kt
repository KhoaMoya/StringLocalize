import model.StringItem
import model.StringsFile
import java.io.File

const val ValuesFolderName = "values"
const val StringsFileName = "strings.xml"

object StringsResourceIO {

    fun getResourceStringFile(source: StringsFile, targetLanguage: Languages): StringsFile {
        val resFolder = File(source.filePath).parentFile.parentFile
        if (resFolder == null) {
            throw Error.ResFolderNotFound
        } else {
            val stringsFolder = File(resFolder, ValuesFolderName + "-" + targetLanguage.value)
            val stringsFile = File(stringsFolder, StringsFileName)

            if (!stringsFolder.exists()) {
                stringsFolder.mkdir()
            }
            if (!stringsFile.exists()) {
                stringsFile.createNewFile()
            }

            val listStringsItem = readAllStringItem(stringsFile, targetLanguage)

            return StringsFile(
                filePath = stringsFile.absolutePath,
                language = targetLanguage,
                listStrings = listStringsItem
            )
        }
    }

    fun readAllDefaultStringsFiles(projectFolder: File, defaultLanguage: Languages): List<StringsFile> {
        return projectFolder.walkTopDown().mapNotNull { file ->
            if (file.name == StringsFileName && file.parentFile.name == ValuesFolderName) {
                val listStringsItem = readAllStringItem(file, defaultLanguage)
                StringsFile(
                    filePath = file.absolutePath,
                    language = defaultLanguage,
                    listStrings = listStringsItem
                )
            } else {
                null
            }
        }.toList()
    }

    fun writeStringsFile(stringsFile: StringsFile) {
        if (stringsFile.listStrings.isEmpty()) return

        val header = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources xmlns:tools=\"http://schemas.android.com/tools\">\n"
        val bottom = "\n</resources>"

        val stringBuilder = StringBuilder(header)
        stringsFile.listStrings.forEach { (id, stringItem) ->
            stringBuilder.append("    <string name=\"$id\">${stringItem.value}</string>\n")
        }
        stringBuilder.append(bottom)

        println(stringBuilder.toString())
    }

    private fun readAllStringItem(file: File, language: Languages): MutableMap<String, StringItem> {
        val map = mutableMapOf<String, StringItem>()
        val lines = file.readLines()
        lines.forEach { line ->
            val trimmedLine = line.trim()
            if (trimmedLine.startsWith("<string name=\"") && trimmedLine.endsWith("</string>")) {
                val endIdIndex = trimmedLine.lastIndexOf("\">")
                if (endIdIndex >= 0) {
                    val id = trimmedLine.substring(14, endIdIndex)

                    val startValueIndex = endIdIndex + 2
                    val endValueIndex = trimmedLine.length - 9
                    val value = trimmedLine.substring(startValueIndex, endValueIndex)

                    map[id] = StringItem(value = value, language = language)
                } else {
                    System.err.println(trimmedLine)
                    null
                }
            } else {
                null
            }
        }
        return map
    }

}