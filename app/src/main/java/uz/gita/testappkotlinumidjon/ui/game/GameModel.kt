package uz.gita.testappkotlinumidjon.ui.game

import uz.gita.testappkotlinumidjon.model.TestData
import uz.gita.testappkotlinumidjon.repository.Repository

class GameModel : GameContract.Model {
    private val repository: Repository = Repository
    private val totalTests = 10
    private val questions: MutableList<TestData> = mutableListOf()
    private var currentPos: Int = 0
    private val answers: MutableList<String?> = MutableList(totalTests) { null }
    private var correctCount: Int = 0
    private var wrongCount: Int = 0
    private var attempts: Int = 3

    init {
        loadData()
    }

    override fun testCheck(userAnswer: String): Boolean {
        return if (userAnswer == questions[currentPos - 1].answer) {
            answers[currentPos - 1] = userAnswer
            correctCount++
            true
        } else {
            answers[currentPos - 1] = userAnswer
            attempts--
            wrongCount++
            false
        }
    }

    override fun reduceAttempts() {
        attempts--
    }

    override fun getAttemptsCount(): Int {
        return attempts
    }

    override fun hasQuestion(): Boolean {
        return currentPos < totalTests
    }

    private fun loadData() {
        questions.addAll(repository.getNQuestions(totalTests))
    }

    override fun getNextQuestion(): TestData {
        return questions[currentPos++]
    }

    override fun getCurrPosition(): Int {
        return currentPos
    }

    override fun getTotalTests(): Int {
        return totalTests
    }

    override fun saveResultToRepository() {
        repository.saveAnswers(answers)
    }
}