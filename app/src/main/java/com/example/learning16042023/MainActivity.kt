package com.example.learning16042023

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create spinner
        val spinnerCourse = findViewById<Spinner>(R.id.courseSpinner)

        spinnerCourse.onItemSelectedListener = this

        spinnerCourse.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.coursesArray,
            android.R.layout.simple_spinner_dropdown_item
        )

        //create grade button click listener
        val gradeButton = findViewById<Button>(R.id.gradeButton)
        gradeButton.setOnClickListener {
            val score = findViewById<EditText>(R.id.scoreInput)

            val result = when {
                score.text.toString().toDouble() >= 80 -> "A"
                score.text.toString().toDouble() >= 75 -> "A-"
                score.text.toString().toDouble() >= 70 -> "B+"
                score.text.toString().toDouble() >= 65 -> "B"
                score.text.toString().toDouble() >= 60 -> "B-"
                score.text.toString().toDouble() >= 55 -> "C+"
                score.text.toString().toDouble() >= 50 -> "C"
                else -> "D"
            }

            findViewById<TextView>(R.id.resultText).text = getString(R.string.resultText, result)
        }

        //create implicit intent for contact button
        findViewById<Button>(R.id.contactButton).setOnClickListener {
            startActivity(contactDE());
        }


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(
            this,
            "Course selected is \n${p0?.getItemAtPosition(p2)}",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        
    }

    private fun contactDE():Intent{
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("departmentOfExam@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Course : ${findViewById<Spinner>(R.id.courseSpinner).selectedItem}\n"
                + "Score : ${findViewById<EditText>(R.id.scoreInput).text}\n"
                + "Grade : ${findViewById<TextView>(R.id.resultText).text}")

        return emailIntent
    }
}