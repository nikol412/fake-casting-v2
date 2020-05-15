package ru.nikol.fakecasting.common.extension

fun String.containsDigit() = this.contains(Regex("\\d"))
fun String.doesNotContainDigit() = !this.containsDigit()

fun String.containsWhiteSpaces() = this.contains(" ")

fun String.containsLowerCase() = this != this.toUpperCase()
fun String.doesNotContainsLowerCase() = this.containsLowerCase().not()

fun String.containsUpperCase() = this != this.toLowerCase()
fun String.doesNotContainsUpperCase() = this.containsUpperCase().not()

fun String.containsSymbol() = this.contains(Regex("\\W"))
fun String.doesNotContainsSymbol() = this.containsSymbol().not()


fun String.getFirstLetter(withSecond: Boolean = false): String {

    return if (this.length > 1) {
        trim()
        val words: List<String> = this.trim().split(regex = Regex("\\s+"))
        if (words.count() > 1 && withSecond) {
            "${words[0][0].toUpperCase()}${words[1][0].toUpperCase()}"
        } else {
            words[0][0].toString().toUpperCase()
        }
    } else {
        ""
    }
}