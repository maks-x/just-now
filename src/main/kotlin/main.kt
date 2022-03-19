import TimeCase.*

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
    val timeCase = caseDefine(secondsAgo)
    println()
    println("Если пользователь $userName был в сети $secondsAgo секунд назад:")
    println("Текст:\n   $userName был(а) в сети ${agoToText(timeCase, secondsAgo)}")
}

fun caseDefine(secondsAgo: Int): TimeCase {
    return when (secondsAgo) {
        in 0..MINUTE -> JUST_NOW
        in MINUTE + 1..HOUR -> MINUTES_AGO
        in HOUR + 1..DAY -> HOURS_AGO
        in DAY + 1..2 * DAY -> TODAY
        in 2 * DAY + 1..3 * DAY -> YESTERDAY
        else -> LONG_TIME_AGO
    }
}

fun agoToText(case: TimeCase, secondsAgo: Int): String {
    val timeValue: Int
    return when (case) {
        JUST_NOW -> "только что"
        MINUTES_AGO -> {
            timeValue = secondsAgo / MINUTE
            "$timeValue ${format(case, timeValue)} назад"
        }
        HOURS_AGO -> {
            timeValue = secondsAgo / HOUR
            "$timeValue ${format(case, timeValue)} назад"
        }
        TODAY -> "сегодня"
        YESTERDAY -> "вчера"
        LONG_TIME_AGO -> "давно"
    }
}

fun format(case: TimeCase, timeValue: Int): String {
    return when (case) {
        MINUTES_AGO -> when {
            compareTo1(timeValue) -> "минуту"
            compareWithin2and4(timeValue) -> "минуты"
            else -> "минут"
        }
        HOURS_AGO -> when {
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
