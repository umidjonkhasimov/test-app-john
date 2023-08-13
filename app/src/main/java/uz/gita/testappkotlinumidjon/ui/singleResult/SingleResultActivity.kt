package uz.gita.testappkotlinumidjon.ui.singleResult

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import uz.gita.testappkotlin.R

class SingleResultActivity : AppCompatActivity() {
    private lateinit var optionButtons: MutableList<LinearLayoutCompat>
    private lateinit var optionButtonTextView: MutableList<TextView>
    private lateinit var backButton: AppCompatButton
    private lateinit var backButtonArrow: ImageView

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_result)

        val intent = intent
        val question = intent.getStringExtra("question")
        val option1 = intent.getStringExtra("option1")
        val option2 = intent.getStringExtra("option2")
        val option3 = intent.getStringExtra("option3")
        val option4 = intent.getStringExtra("option4")
        val answer = intent.getStringExtra("answer")
        val userAnswer = intent.getStringExtra("userAnswer")

        findViewById<TextView>(R.id.question_single).text = question
        loadViews(option1, option2, option3, option4)
        reArrangeButtons(userAnswer, answer)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun reArrangeButtons(userAnswer: String?, answer: String?) {
        if (userAnswer == answer) {

            for (i in optionButtons.indices) {
                if (optionButtonTextView[i].text == userAnswer) {
                    optionButtons[i].background = getDrawable(R.drawable.corner_radius_checked)
                }
            }
        } else {
            for (i in optionButtons.indices) {
                if (optionButtonTextView[i].text == userAnswer) {
                    optionButtons[i].background = getDrawable(R.drawable.corner_radius_wrong)
                }
                if (optionButtonTextView[i].text == answer) {
                    optionButtons[i].background = getDrawable(R.drawable.corner_radius_checked)
                }
            }
        }
    }

    private fun loadViews(option1: String?, option2: String?, option3: String?, option4: String?) {
        optionButtons = mutableListOf()
        optionButtons.add(findViewById(R.id.single_option1))
        optionButtons.add(findViewById(R.id.single_option2))
        optionButtons.add(findViewById(R.id.single_option3))
        optionButtons.add(findViewById(R.id.single_option4))

        optionButtonTextView = mutableListOf()
        optionButtonTextView.add(findViewById(R.id.single_option_text1))
        optionButtonTextView.add(findViewById(R.id.single_option_text2))
        optionButtonTextView.add(findViewById(R.id.single_option_text3))
        optionButtonTextView.add(findViewById(R.id.single_option_text4))

        optionButtonTextView[0].text = option1
        optionButtonTextView[1].text = option2
        optionButtonTextView[2].text = option3
        optionButtonTextView[3].text = option4

        backButton = findViewById(R.id.back_button_single)
        backButtonArrow = findViewById(R.id.backButtonArrow)
        backButton.setOnClickListener { finish() }
        backButtonArrow.setOnClickListener { finish() }
    }
}