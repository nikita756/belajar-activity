package io.github.nikita756.praktikumfintech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSubmit :Button
    private lateinit var etNama:EditText
    private lateinit var etNim:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    buttonSubmit=findViewById(R.id.btn_submit)

        buttonSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            Toast.makeText(this,nama,Toast.LENGTH_SHORT).show()

            val intentGotoData = Intent(this,DataActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
           startActivity(intentGotoData)
        }

    }
}