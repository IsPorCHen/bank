package com.example.exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exam.R
import com.example.exam.R.*

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_calculator)

        val btnBack = findViewById<Button>(id.btnBack)
        val sliderCredit = findViewById<SeekBar>(id.sliderCredit)
        val etTerm = findViewById<EditText>(id.etTerm)
        val btnCalculate = findViewById<Button>(id.btnCalculate)
        val tvResult = findViewById<TextView>(id.tvResult)

        btnBack.setOnClickListener {
            startActivity(Intent(this, Bank::class.java))
        }

        btnCalculate.setOnClickListener {
            val creditAmount = sliderCredit.progress
            val term = etTerm.text.toString().toIntOrNull()

            if (term == null || term <= 0) {
                Toast.makeText(this, "Введите корректный срок", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monthlyPayment = when {
                term <= 12 -> creditAmount / term + creditAmount * 0.059
                term <= 24 -> (creditAmount / term + creditAmount * 0.059) + (creditAmount - (creditAmount / 12) * 12) * 0.051
                else -> (creditAmount / term + creditAmount * 0.059) + (creditAmount - (creditAmount / term) * term) * 0.042
            }

            tvResult.text = "Ежемесячный платёж: %.2f руб.".format(monthlyPayment)
        }
    }
}
