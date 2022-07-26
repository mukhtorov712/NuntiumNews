package uz.pdp.dagger2nuntium.utils


fun getCatList(lang: String): List<String> {
    return when (lang) {
        "en" -> listOf("Random", "Health", "Business", "Entertainment", "Science", "Sports", "Technology")
        "ru" -> listOf("Случайное", "Здоровье", "Бизнес", "Развлечения", "Наука", "Спорт", "Технологии")
        "de" -> listOf("Zufall", "Gesundheit", "Geschäft", "Unterhaltung", "Wissenschaft", "Sport", "Technologie")
        "tr" -> listOf("Tesadüfi", "Sağlık", "İş", "Eğlence", "Bilim", "Spor", "Teknoloji")
        else -> emptyList()
    }
}

fun getCatListWith(lang: String): List<String> {
    return when (lang) {
        "en" -> listOf("❤️ Health", "\uD83D\uDCB5 Business", "\uD83D\uDE03 Entertainment", "\uD83D\uDD2C Science", "\uD83C\uDFC8 Sports", "\uD83D\uDDA5 Technology")
        "ru" -> listOf("❤️ Здоровье", "\uD83D\uDCB5 Бизнес", "\uD83D\uDE03 Развлечения", "\uD83D\uDD2C Наука", "\uD83C\uDFC8 Спорт", "\uD83D\uDDA5 Технологии")
        "de" -> listOf("❤️ Gesundheit", "\uD83D\uDCB5 Geschäft", "\uD83D\uDE03 Unterhaltung", "\uD83D\uDD2C Wissenschaft", "\uD83C\uDFC8 Sport", "\uD83D\uDDA5 Technologie")
        "tr" -> listOf("❤️ Sağlık", "\uD83D\uDCB5 İş", "\uD83D\uDE03 Eğlence", "\uD83D\uDD2C Bilim", "\uD83C\uDFC8 Spor", "\uD83D\uDDA5 Teknoloji")
        else -> emptyList()
    }
}