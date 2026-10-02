package com.cursokotlin.retrofitkotlinexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


        binding.composeView.setContent {
            var text by remember { mutableStateOf("") }

            Column (modifier = Modifier.fillMaxSize().background(Color.Cyan),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Contenido en compose", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(32.dp))
                TextField(value= text, onValueChange = {text = it })
            }
        }

        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
        //binding.btnShimer.setOnClickListener { startActivity(Intent(this,::class.java)) }
    }
}