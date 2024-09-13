package com.example.rigorousai

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        val eTPrompt= findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit= findViewById<Button>(R.id.btnSubmit)
        val tVResult= findViewById<TextView>(R.id.tVResult)


        //for typewriter animation
        val label = "Hello, I am Rigorous how can i help You ?"
        val stringBuilder = StringBuilder()

        Thread{
            for (letter in label){
                stringBuilder.append(letter)
                Thread.sleep(100)
                runOnUiThread {
                    tVResult.text = stringBuilder.toString()
                }
            }
        }.start()

        btnSubmit.setOnClickListener {
            val prompt = eTPrompt.text.toString()

            val generativeModel = GenerativeModel(
                // For text-only input, use the gemini-pro model
                modelName = "gemini-pro",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "Enter here your api key"
            )

            runBlocking {
            val response = generativeModel.generateContent(prompt)
            tVResult.text= response.text
                }
        }

    }
}