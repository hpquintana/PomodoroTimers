package io.github.hpquintana.pomodorotimer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast

class PomodoroPicker : AppCompatActivity(), View.OnClickListener {

    private lateinit var webViewButton: Button
    private lateinit var nativeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro_picker)

        webViewButton = findViewById(R.id.webViewButton)
        webViewButton.setOnClickListener(this)

        nativeButton = findViewById(R.id.nativeButton)
        nativeButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.webViewButton -> {
                val intent = Intent(this, WebViewActivity::class.java)
                startActivity(intent)
            }
            R.id.nativeButton -> {
                Toast.makeText(this, "coming soon!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
