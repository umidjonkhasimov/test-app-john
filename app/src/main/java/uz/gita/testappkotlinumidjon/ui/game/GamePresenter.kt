package uz.gita.testappkotlinumidjon.ui.game

import android.content.Context

class GamePresenter(private val context: Context) : GameContract.Presenter {
    private val model: GameContract.Model = GameModel()
    private val view: GameContract.View = context as GameContract.View
    private lateinit var userAnswer: String

    init {
        loadNextQuestion()
    }

    override fun clickSkipButton() {
        loadNextQuestion()
        model.reduceAttempts()
        view.reduceHearts()
    }

    private fun loadNextQuestion() {
        if (model.getAttemptsCount() >= 1 && model.hasQuestion()) {
            view.clearCheckedOptions()
            view.loadTestView(
                model.getNextQuestion(),
                model.getCurrPosition(),
                model.getTotalTests()
            )
        } else {
            view.showDialog()
            model.saveResultToRepository()
        }
    }

    override fun clickNextButton() {
        if (!(model.testCheck(userAnswer))) {
            view.reduceHearts()
        }
        loadNextQuestion()
        view.stateNextButton(false)
    }

    override fun usersSelectedAnswer(answer: String) {
        this.userAnswer = answer
        view.stateNextButton(true)
    }
}