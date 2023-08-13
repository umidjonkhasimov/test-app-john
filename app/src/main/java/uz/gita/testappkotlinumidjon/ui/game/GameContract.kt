package uz.gita.testappkotlinumidjon.ui.game

import uz.gita.testappkotlinumidjon.model.TestData

interface GameContract {
    interface Model {
        fun reduceAttempts()
        fun getAttemptsCount(): Int
        fun hasQuestion(): Boolean
        fun getNextQuestion(): TestData
        fun getCurrPosition(): Int
        fun getTotalTests(): Int
        fun saveResultToRepository()
        fun testCheck(userAnswer: String): Boolean

    }

    interface View {
        //        fun clearCheckedRadios()
        fun clearCheckedOptions()
        fun showDialog()
        fun loadTestView(testData: TestData, currPos: Int, totalTests: Int)
        fun stateNextButton(bool: Boolean)
        fun openResultActivity()
        fun reduceHearts()
    }

    interface Presenter {
        fun clickSkipButton()
        fun clickNextButton()
        fun usersSelectedAnswer(answer: String)

    }
}