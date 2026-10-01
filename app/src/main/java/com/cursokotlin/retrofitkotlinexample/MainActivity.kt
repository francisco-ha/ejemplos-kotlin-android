package com.cursokotlin.retrofitkotlinexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cursokotlin.retrofitkotlinexample.databinding.ActivityMainBinding
import com.cursokotlin.retrofitkotlinexample.shareSheet.MainShareSheetActivity
import com.cursokotlin.retrofitkotlinexample.shimmer.MainShimmerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnShareFiles.setOnClickListener {
            startActivity(Intent(this, MainShareSheetActivity::class.java))
        }

        binding.btnShimer.setOnClickListener {
            startActivity(Intent(this, MainShimmerActivity::class.java))
        }

        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
    }
}