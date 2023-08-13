package uz.gita.testappkotlinumidjon.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import uz.gita.testappkotlin.R

class OutOfAttemptsDialog : DialogFragment() {
    private lateinit var listener: (() -> Unit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("GGG", "onCreateView")
        val view = inflater.inflate(R.layout.dialog_out_of_attempts, container, false)
        val btnResults = view.findViewById<AppCompatButton>(R.id.btn_results)
        btnResults.setOnClickListener {
            listener.invoke()
        }
        return view
    }

    fun setResultsButtonListener(action: () -> Unit) {
        listener = action
    }
}
