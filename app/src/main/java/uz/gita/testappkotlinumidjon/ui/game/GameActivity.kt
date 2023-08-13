package uz.gita.testappkotlinumidjon.ui.game

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import uz.gita.testappkotlin.R
import uz.gita.testappkotlinumidjon.model.TestData
import uz.gita.testappkotlinumidjon.ui.dialogs.OutOfAttemptsDialog
import uz.gita.testappkotlinumidjon.ui.result.ResultActivity

class GameActivity : AppCompatActivity(), GameContract.View {
    private lateinit var presenter: GameContract.Presenter
    private lateinit var optionButtons: MutableList<LinearLayoutCompat>
    private lateinit var optionButtonTextView: MutableList<TextView>
    private lateinit var hearts: MutableList<ImageView>
    private lateinit var questionText: AppCompatTextView
    private lateinit var currPosText: AppCompatTextView
    private lateinit var skipButton: AppCompatButton
    private lateinit var nextButton: AppCompatButton
    private lateinit var backButton: AppCompatImageView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        loadViews()
        setListeners()
        presenter = GamePresenter(this)
    }

    private fun loadViews() {
        optionButtons = mutableListOf()
        optionButtons.add(findViewById(R.id.option1))
        optionButtons.add(findViewById(R.id.option2))
        optionButtons.add(findViewById(R.id.option3))
        optionButtons.add(findViewById(R.id.option4))

        optionButtonTextView = mutableListOf()
        optionButtonTextView.add(findViewById(R.id.option_text1))
        optionButtonTextView.add(findViewById(R.id.option_text2))
        optionButtonTextView.add(findViewById(R.id.option_text3))
        optionButtonTextView.add(findViewById(R.id.option_text4))

        hearts = mutableListOf()
        hearts.add(findViewById(R.id.heart1))
        hearts.add(findViewById(R.id.heart2))
        hearts.add(findViewById(R.id.heart3))

        questionText = findViewById(R.id.question)
        currPosText = findViewById(R.id.currentState)
        skipButton = findViewById(R.id.skipButton)
        nextButton = findViewById(R.id.nextButton)
        backButton = findViewById(R.id.backButtonArrow)

        timerText = findViewById(R.id.timer)

        countDownTimer = object : CountDownTimer(11000, 1000) {
            override fun onTick(remaining: Long) {
                timerText.text = updateTimer(remaining)
            }

            override fun onFinish() {
                if (nextButton.isEnabled)
                    presenter.clickNextButton()
                else
                    presenter.clickSkipButton()
            }
        }
    }

    private fun updateTimer(remaining: Long): String {
        val timer: StringBuilder = java.lang.StringBuilder()
        when (remaining) {
            in 10000 until 11000 -> timer.append((remaining / 1000))
            in 1000 until 10000 -> timer.append("0").append(remaining / 1000)
            else -> timer.append("00")
        }
        return timer.toString()
    }

    private fun setListeners() {
        skipButton.setOnClickListener { presenter.clickSkipButton() }
        nextButton.setOnClickListener { presenter.clickNextButton() }
        backButton.setOnClickListener { finish() }

        for (i in optionButtons.indices) {
            optionButtons[i].setOnClickListener {
                clearCheckedOptions()
                optionButtons[i].background = AppCompatResources.getDrawable(
                    this,
                    R.drawable.corner_radius_checked
                )
                presenter.usersSelectedAnswer(optionButtonTextView[i].text.toString())
            }
        }
    }

    override fun clearCheckedOptions() {
        for (i in optionButtons.indices) {
            optionButtons[i].background = AppCompatResources.getDrawable(
                this,
                R.drawable.corner_radius
            )
        }
    }

    override fun loadTestView(testData: TestData, currPos: Int, totalTests: Int) {
        questionText.text = testData.question
        currPosText.text = "Question $currPos of $totalTests"

        optionButtonTextView[0].text = testData.option1
        optionButtonTextView[1].text = testData.option2
        optionButtonTextView[2].text = testData.option3
        optionButtonTextView[3].text = testData.option4

        countDownTimer.start()
    }

    override fun stateNextButton(bool: Boolean) {
        nextButton.isEnabled = bool
    }

    override fun openResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        countDownTimer.cancel()
        finish()
    }

    override fun reduceHearts() {
        for (i in hearts.size - 1 downTo 0) {
            if (hearts[i].isEnabled) {
                hearts[i].setImageResource(R.drawable.ic_heart_grey)
                hearts[i].isEnabled = false
                break
            }
        }
    }

    override fun showDialog() {
        countDownTimer.cancel()
        val dialog = OutOfAttemptsDialog()
        dialog.setResultsButtonListener {
            openResultActivity()
        }
        dialog.show(supportFragmentManager, "")

    }

    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

}