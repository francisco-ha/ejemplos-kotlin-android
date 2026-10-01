package com.cursokotlin.retrofitkotlinexample.shimmer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.cursokotlin.retrofitkotlinexample.R
import com.cursokotlin.retrofitkotlinexample.databinding.ActivityMainShimmerBinding
import kotlinx.coroutines.delay

class MainShimmerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainShimmerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainShimmerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler(Looper.getMainLooper()).postDelayed({
            binding.viewLoading.isVisible = false
            binding.viewContainer.isVisible = true
        }, 3000 )
    }
}