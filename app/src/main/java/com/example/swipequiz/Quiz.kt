package com.example.swipequiz

/**
 *  Data quiz class
 */
data class Quiz (
    var question: String,
    var answer: Boolean
) {
    // array for all QUESTIONS
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same",
            "Mobile Application Development grants 12 ECTS",
            "A Unit Kotlin corresponds to void in Java",
            "In Kotlin 'when' replaces the 'switch' operator in Java",
            "Can you program in kotlin IOS applications "
        )
        // array for the ANSWERS
        val ANSWERS = arrayOf(
            false,
            false,
            true,
            true,
            false

        )
    }

}