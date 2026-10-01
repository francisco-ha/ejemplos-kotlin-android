package com.cursokotlin.retrofitkotlinexample.retrofit2

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.retrofitkotlinexample.BuildConfig
import com.cursokotlin.retrofitkotlinexample.databinding.ActivityMainRetrofitBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRetrofitActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var imagesPuppies: List<String>
    lateinit var dogsAdapter: DogsAdapter

    private lateinit var binding: ActivityMainRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBreed.setOnQueryTextListener(this)


        /**Una buildVariants es la suma de los builTypes y flavors*/
        if (BuildConfig.BUILD_TYPE == "debug"){
            //Segun el buildType inicializo alguna libreria o no
        }
        if (BuildConfig.FLAVOR.equals("premium")){
            //muestra una pantalla premium
        }
        else{
            //muestra una pantalla cualquiera
        }

        val urlAUtilizar = BuildConfig.URL
        val tienePermiso = BuildConfig.isAllow
        print("Url: $urlAUtilizar, Tiene permiso: $tienePermiso")
    }

    private fun initCharacter(puppies: DogsResponse) {
        imagesPuppies = puppies.images
        dogsAdapter = DogsAdapter(imagesPuppies)
        binding.rvDogs.setHasFixedSize(true)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = dogsAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query.lowercase())
        return true
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val puppies =
                    getRetrofit().create(APIService::class.java).getCharacterByName("$query/images")
                runOnUiThread {
                    if (puppies.status == "success") {
                        initCharacter(puppies)
                    } else {
                        showErrorDialog()
                    }
                    hideKeyboard()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showErrorDialog()
                    hideKeyboard()
                }
            }
        }
    }

    private fun showErrorDialog() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}