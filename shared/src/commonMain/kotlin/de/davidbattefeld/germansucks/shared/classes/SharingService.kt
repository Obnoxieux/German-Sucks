package de.davidbattefeld.germansucks.shared.classes

enum class SharingService(val url: String) {
    //Totally not gonna work when they change their website, but well...
    DEEPL("https://www.deepl.com/translator#de/en/"),
    GOOGLE_TRANSLATE("https://translate.google.com/?sl=de&tl=en&text="),
    DUDEN("https://www.duden.de/suchen/dudenonline/");
}