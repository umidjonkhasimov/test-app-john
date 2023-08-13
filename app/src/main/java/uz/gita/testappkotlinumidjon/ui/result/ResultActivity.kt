package uz.gita.testappkotlinumidjon.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import uz.gita.testappkotlin.R
import uz.gita.testappkotlinumidjon.repository.Repository
import uz.gita.testappkotlinumidjon.ui.singleResult.SingleResultActivity

class ResultActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val repository = Repository

        val answers = repository.getAnswers()
        val questions = repository.getGivenQuestions()

        val linearLayoutCompat = findViewById<LinearLayoutCompat>(R.id.container)
        val layoutInflate = LayoutInflater.from(this)

        for (i in answers.indices) {
            val question = questions[i].question
            val actualAns = questions[i].answer
            val userAns = answers[i]
            val text = "Question " + (i + 1)

            val v = layoutInflate.inflate(R.layout.item, linearLayoutCompat, false)
            val textView = v.findViewById<TextView>(R.id.questionTxt)

            when (userAns) {
                null -> {
                    textView.text = "$text skipped"
                    textView.background = getDrawable(R.drawable.corner_radius)
                    linearLayoutCompat.addView(v)
                }
                actualAns -> {
                    textView.text = text
                    textView.background = getDrawable(R.drawable.corner_radius_checked)
                    v.setOnClickListener {
                        val intent = Intent(this@ResultActivity, SingleResultActivity::class.java)
                        intent.putExtra("question", questions[i].question)
                        intent.putExtra("option1", questions[i].option1)
                        intent.putExtra("option2", questions[i].option2)
                        intent.putExtra("option3", questions[i].option3)
                        intent.putExtra("option4", questions[i].option4)
                        intent.putExtra("answer", questions[i].answer)
                        intent.putExtra("userAnswer", userAns)
                        startActivity(intent)
                    }
                    linearLayoutCompat.addView(v)
                }
                else -> {
                    textView.text = text
                    textView.background = getDrawable(R.drawable.corner_radius_wrong)
                    v.setOnClickListener {
                        val intent = Intent(this@ResultActivity, SingleResultActivity::class.java)
                        intent.putExtra("question", questions[i].question)
                        intent.putExtra("option1", questions[i].option1)
                        intent.putExtra("option2", questions[i].option2)
                        intent.putExtra("option3", questions[i].option3)
                        intent.putExtra("option4", questions[i].option4)
                        intent.putExtra("answer", questions[i].answer)
                        intent.putExtra("userAnswer", userAns)
                        startActivity(intent)
                    }
                    linearLayoutCompat.addView(v)
                }
            }
        }
    }
}