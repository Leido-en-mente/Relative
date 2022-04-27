package com.cdp.relativeex

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class Principal : AppCompatActivity() {

    private lateinit var button2: Button
    private var user = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        intent.getStringExtra("user")?.let { user = it }

        button2 =   findViewById(R.id.button2)

        button2.setOnClickListener {
            checkUser()
        }
    }




    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun checkUser(){
        val currentUser = Firebase.auth.currentUser

        if(currentUser != null){
            val intent = Intent(this, Lista::class.java)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.navigation_perfil -> {
                val intent7 = Intent(this ,Info_admin::class.java)
                startActivity(intent7)
                finish()
            }

            R.id.navigation_salir -> {
                val intent6 = Intent(this ,RegistroE::class.java)
                startActivity(intent6)
                finish()
            }
            R.id.navigation_cerrar -> {
                Firebase.auth.signOut()
                val intent5 = Intent(this ,MainActivity::class.java)
                startActivity(intent5)
                finish()
            }
            R.id.navigation_esp -> {
                setAppLocale(this, "values")
                val intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }
            R.id.navigation_eng -> {
                setAppLocale(this, "en")
                val intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }
            R.id.navigation_deu -> {
                setAppLocale(this, "de")
                val intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }
            R.id.navigation_rus -> {
                setAppLocale(this, "ru")
                val intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}