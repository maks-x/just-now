const val MINUTE = 60
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

const val userName = "Валера"

fun main() {
    printResult(HOUR + 1)
    printResult(HOUR * 2)
    printResult(HOUR * 5)
    printResult(HOUR * 11)
    printResult(HOUR * 21)
    printResult(HOUR * 25)
    printResult(HOUR * 48 + 1)
    printResult(HOUR * 72 + 1)
}

fun printResult(secondsAgo: Int) {
    println()
    println("Если пользователь $userName был в сети $secondsAgo секунд назад:")
    println("Текст:\n   $userName был(а) в сети ${agoToText(secondsAgo)}")
}

fun agoToText(secondsAgo: Int): String {
    return when (secondsAgo) {
        in 0..MINUTE  -> "только что"
        in MINUTE + 1..HOUR -> {
            val minutes = secondsAgo / MINUTE
            "$minutes ${format(MINUTE, minutes)} назад"
        }
        in HOUR + 1..DAY -> {
            val hours = secondsAgo / HOUR
            "$hours ${format(HOUR, hours)} назад"
        }
        in DAY + 1..2 * DAY -> "сегодня"
        in 2 * DAY + 1..3 * DAY -> "вчера"
        else -> "давно"
    }
}

fun format(timeCase: Int, timeValue: Int): String {
    return when (timeCase) {
        MINUTE -> when {
            compareTo1(timeValue) -> "минуту"
            compareWithin2and4(timeValue) -> "минуты"
            else -> "минут"
        }
        HOUR -> when {
            compareTo1(timeValue) -> "час"
            compareWithin2and4(timeValue) -> "часа"
            else -> "часов"
        }
        else -> "unknown time case"
    }
}

fun compareTo1(num: Int): Boolean {
    return num == 1 || num % 10 == 1 && num != 11
}

fun compareWithin2and4(num: Int): Boolean {
    return num in 2..4 || num % 10 in 2..4 && num !in 12..14
}
