package com.cdp.relativeex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var usuarioi: EditText
    private lateinit var contrasena: EditText
    private lateinit var inicio1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.reg)

        usuarioi = findViewById(R.id.usuarioi)
        contrasena = findViewById(R.id.contrasena)
        inicio1 =   findViewById(R.id.inicio1)


       button.setOnClickListener {
            val intent1 = Intent(this, RegistroE::class.java)
            startActivity(intent1)
        }


        inicio1.setOnClickListener {
            val usuar = usuarioi.text.toString().trim()
            val contra = contrasena.text.toString().trim()
            if (usuar.isEmpty()){
                usuarioi.error = "Usuario Requerido"
                return@setOnClickListener
            }
            else if (contra.isEmpty()){
                contrasena.error = "Contraseña Requerida"
                return@setOnClickListener
            }
            else {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(usuarioi.text.toString(),
                    contrasena.text.toString()).addOnCompleteListener{

                        if (it.isSuccessful){
                            setContentView(R.layout.inicio)
                        } else {
                            showAlert()
                        }
                }


            }
        }


    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)


    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error a la hora de autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }







}