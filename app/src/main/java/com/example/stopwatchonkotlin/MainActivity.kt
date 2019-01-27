package com.example.stopwatchonkotlin

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var seconds: Int = 0
    private var running: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun runTimer() {
        val handler: Handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                var hours = seconds / 3600
                var minutes = (seconds % 3600) / 60
                var secs = seconds % 60

                var time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                time_view.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }


}
