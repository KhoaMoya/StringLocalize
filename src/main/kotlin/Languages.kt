const val ValuesFolderName = "values"
const val StringsFileName = "strings.xml"

enum class Languages(val language: String, val region: String = "") {
    Default("", ""),
    Afrikaans("af"),
    Albanian("sq"),
    Amharic("am"),
    Arabic("ar"),
    Armenian("hy", "AM"),
    Azerbaijani("az", "AZ"),
    Bangla("bn", "BD"),
    Basque("eu", "ES"),
    Belarusian("be"),
    Bulgarian("bg"),
    Burmese("my", "MM"),
    Catalan("ca"),
    Chinese_HongKong("zh", "HK"),
    Chinese_Simplified("zh", "CN"),
    Chinese_Traditional("zh", "TW"),
    Croatian("hr"),
    Czech("cs", "CZ"),
    Danish("da", "DK"),
    Dutch("nl", "NL"),
    English_IN("en", "IN"),
    English_SG("en", "SG"),
    English_ZA("en", "ZA"),
    English_Australia("en", "AU"),
    English_Canada("en", "CA"),
    English_UnitedKingdom("en", "GB"),
    English_UnitedStates("en", "US"),
    Estonian("et"),
    Filipino("fil"),
    Finnish("fi", "FI"),
    French_Canada("fr", "CA"),
    French_France("fr", "FR"),
    Galician("gl", "ES"),
    Georgian("ka", "GE"),
    German("de", "DE"),
    Greek("el", "GR"),
    Gujarati("gu"),
    Hebrew("iw", "IL"),
    Hindi("hi", "IN"),
    Hungarian("hu", "HU"),
    Icelandic("is", "IS"),
    Indonesian("id"),
    Italian("it", "IT"),
    Japanese("ja", "JP"),
    Kannada("kn", "IN"),
    Kazakh("kk"),
    Khmer("km", "KH"),
    Korean("ko", "KR"),
    Kyrgyz("ky", "KG"),
    Lao("lo", "LA"),
    Latvian("lv"),
    Lithuanian("lt"),
    Macedonian("mk", "MK"),
    Malay("ms"),
    Malay_Malaysia("ms", "MY"),
    Malayalam("ml", "IN"),
    Marathi("mr", "IN"),
    Mongolian("mn", "MN"),
    Nepali("ne", "NP"),
    Norwegian("no", "NO"),
    Persian("fa"),
    Persian_AE("fa", "AE"),
    Persian_AF("fa", "AF"),
    Persian_IR("fa", "IR"),
    Polish("pl", "PL"),
    Portuguese_Brazil("pt", "BR"),
    Portuguese_Portugal("pt", "PT"),
    Punjabi("pa"),
    Romanian("ro"),
    Romansh("rm"),
    Russian("ru", "RU"),
    Serbian("sr"),
    Sinhala("si", "LK"),
    Slovak("sk"),
    Slovenian("sl"),
    Spanish_LatinAmerica("es", "419"),
    Spanish_Spain("es", "ES"),
    Spanish_UnitedStates("es", "US"),
    Swahili("sw"),
    Swedish("sv", "SE"),
    Tamil("ta", "IN"),
    Telugu("te", "IN"),
    Thai("th"),
    Turkish("tr", "TR"),
    Ukrainian("uk"),
    Urdu("ur"),
    Vietnamese("vi"),
    Zulu("zu");

    fun getFolderName(): String {
        return if (region.isEmpty()) {
            "$ValuesFolderName-$language"
        } else {
            "$ValuesFolderName-$language-r$region"
        }
    }

    fun getCode(): String {
        return if (region.isEmpty()) {
            language
        } else {
            "$language-$region"
        }
    }
}