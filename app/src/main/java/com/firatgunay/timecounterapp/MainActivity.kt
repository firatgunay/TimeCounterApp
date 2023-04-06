package com.firatgunay.timecounterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.firatgunay.timecounterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hour: Int = 0
    private var minute: Int = 0
    private var second: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incrementButton.setOnClickListener {
            incrementTime()
            updateTime()
        }

        binding.decrementButton.setOnClickListener {
            decrementTime()
            updateTime()
        }

        binding.resultText.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 8) {
                val timeParts = text.split(":")
                hour = timeParts[0].toInt()
                minute = timeParts[1].toInt()
                second = timeParts[2].toInt()
            }
        }
    }

    private fun incrementTime() {
        if (second < 59) {
            second++
        } else {
            second = 0
            if (minute < 59) {
                minute++
            } else {
                minute = 0
                if (hour < 23) {
                    hour++
                } else {
                    hour = 0
                }
            }
        }
    }

    private fun decrementTime() {
        if (second > 0) {
            second--
        } else {
            second = 59
            if (minute > 0) {
                minute--
            } else {
                minute = 59
                if (hour > 0) {
                    hour--
                } else {
                    hour = 23
                }
            }
        }
    }

    private fun updateTime() {
        val hourString = hour.toString().padStart(2, '0')
        val minuteString = minute.toString().padStart(2, '0')
        val secondString = second.toString().padStart(2, '0')
        val timeString = "$hourString:$minuteString:$secondString"
        binding.resultText.text = timeString
    }
}