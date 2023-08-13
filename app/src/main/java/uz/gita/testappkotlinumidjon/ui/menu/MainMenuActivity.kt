package uz.gita.testappkotlinumidjon.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uz.gita.testappkotlin.R
import uz.gita.testappkotlinumidjon.ui.game.GameActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        findViewById<View>(R.id.startButton).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        findViewById<View>(R.id.exitButton).setOnClickListener { finish() }
    }
}