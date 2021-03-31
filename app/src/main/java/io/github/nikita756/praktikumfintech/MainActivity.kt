package io.github.nikita756.praktikumfintech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSubmit :Button
    private lateinit var etNama:EditText
    private lateinit var etNim:EditText
    private lateinit var rgGender: RadioGroup

    private lateinit var tvwelcome:TextView

    lateinit var prefUtil: PreferenceUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefUtil= PreferenceUtil.newInstance(this)

        if (prefUtil.getBoolean("is_login")==false){
            val intentWelcomeActivity= Intent(this, welcomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intentWelcomeActivity)
        }

        etNama = findViewById(R.id.et_nama)
        etNim = findViewById(R.id.et_nim)
        rgGender = findViewById(R.id.rg_gender)
        buttonSubmit = findViewById(R.id.btn_submit)
        tvwelcome=findViewById(R.id.tv_welcome)

        updateData()

        buttonSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            val nim = etNim.text.toString()
            val gender: String = when (rgGender.checkedRadioButtonId) {
                R.id.rb_male -> "Laki-laki"
                R.id.rb_female -> "Perempuan"
                else -> ""
            }

            Log.e("TAG", "onCreate() called ${rgGender.checkedRadioButtonId}")

            val extras = Bundle().apply {
                putString("nama", nama)
                putString("nim", nim)
                putString("gender", gender)
            }

            val intentGotoData = Intent(this, DataActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtras(extras)
            }
           startActivity(intentGotoData)
        }

    }

    private fun updateData(){
        val nama =prefUtil.getString("nama")
        val welcomeMesage="Selamat Datang,$nama"
        tvwelcome.text=welcomeMesage
        if(nama.isNullOrEmpty()){
            tvwelcome.visibility= View.GONE
        }
        else {
            tvwelcome.visibility=View.VISIBLE
        }
    }
}
