package com.example.cipher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.cipher.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_choose)

        binding.textBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.imageBtn.setOnClickListener {
            startActivity(Intent(this,ImageCryptographyActivity::class.java))
        }
    }
}