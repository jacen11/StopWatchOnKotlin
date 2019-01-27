package com.example.stopwatchonkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var seconds: Int = 0
    private var running: Boolean = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }

        runTimer()

        start_button.setOnClickListener {
            running = true
        }

        stop_button.setOnClickListener {
            running = false
        }

        reset_button.setOnClickListener {
            running = false
            seconds = 0
        }
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.putInt("seconds", seconds)
        savedInstanceState?.putBoolean("running", running)
        savedInstanceState?.putBoolean("wasRunning", wasRunning)
    }

    private fun runTimer() {
        val handler: Handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                time_view.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }


}
