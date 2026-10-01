package com.cursokotlin.retrofitkotlinexample.shareSheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cursokotlin.retrofitkotlinexample.R
import com.cursokotlin.retrofitkotlinexample.databinding.ActivityMainShareSheetBinding

class MainShareSheetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainShareSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainShareSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        binding.btnShareText.setOnClickListener { sharetext() }
        binding.btnShareImage.setOnClickListener { shareImage() }
    }

    fun sharetext() {
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"EXTRA")
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE,"Titulo del Share texto")
        }

        /**
         * el selector nativo de Android) para que el usuario elija
         * con qué aplicación quiere compartir el contenido
         */
        val shareIntent = Intent.createChooser(intent,"Selecciona una app para enviar")
        startActivity(shareIntent)
    }

    private fun shareImage(){

        val fakeUri = Uri.parse("content://media/external/images/media/0")

        // Si quisieras probar múltiples, usas arrayListOf en lugar de 'as ArrayList'
        val uriArray: ArrayList<Uri> = arrayListOf(fakeUri, fakeUri)

        val intent = Intent().apply{

            //action = Intent.ACTION_SEND
            //putExtra(Intent.EXTRA_STREAM,fakeUri)
            //type = "image/jpeg"
            action = Intent.ACTION_SEND_MULTIPLE //para multiples imagenes
            putParcelableArrayListExtra(Intent.EXTRA_STREAM,uriArray)  //para multiples imagenes
            type = "image/*"//imagenes con formatos distintos  //para multiples imagenes
            putExtra(Intent.EXTRA_TITLE,"Titulo del Share imagenes")

            // Concede permisos temporales de lectura obligatorios para apps como WhatsApp
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        /**
         * el selector nativo de Android) para que el usuario elija
         * con qué aplicación quiere compartir el contenido
         */
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }

}

/**
 * Sin apply
 *
 *  // 1. Creas el objeto de forma normal
 *     val intent = Intent()
 *
 *     // 2. Tienes que repetir "intent." en cada línea para configurar sus datos
 *     intent.action = Intent.ACTION_SEND
 *     intent.putExtra(Intent.EXTRA_STREAM, fakeUri)
 *     intent.type = "image/jpeg"
 */