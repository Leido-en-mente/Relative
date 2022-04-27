package com.cdp.relativeex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.registro_e.*


enum class ProviderType {
    BASIC
}

class MainActivity : AppCompatActivity() {

    private lateinit var usuarioi: EditText
    private lateinit var contrasena: EditText
    private lateinit var inicio1: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


/*

auth = Firebase.auth
        val pasoa = Firebase.auth.currentUser
        if (pasoa != null) {
            registro2()
        }



        val bundle2 = intent.extras
        val obtener = bundle2?.getString("Admin")?:"Sin rol"
        binding.infoa1.text = obtener
        Toast.makeText(this, obtener, Toast.LENGTH_SHORT).show()


                        val data = HashMap<String, Any>()
                        val newCityRef = db.collection("users").document()
                        newCityRef.set(data)


        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Completado")
        analytics.logEvent("InitScreen", bundle)*/
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
                usuarioi.error = "Correo Requerido"
                return@setOnClickListener
            }
            else if (contra.isEmpty()){
                contrasena.error = "Contraseña Requerida"
                return@setOnClickListener
            }
            else {
                registro()

            }
        }

    }




    private fun registro() {
        inicio1.setOnClickListener {
            usuarioi = findViewById(R.id.usuarioi)
            contrasena = findViewById(R.id.contrasena)
            auth.signInWithEmailAndPassword(
                usuarioi.text.toString(),
                contrasena.text.toString()
            ).addOnCompleteListener {

                if (it.isSuccessful) {

                    val user = Firebase.auth.currentUser
                    if (user!!.isEmailVerified){


                        exito() }
                    else if (!user!!.isEmailVerified){
                        alerta2()
                    }

                }
                else{
                    alerta()
                }

            }



        }
    }








    private fun registro2() {

        val user = Firebase.auth.currentUser
        if (user!!.isEmailVerified){
            exito() }

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

    private fun alerta() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error a la hora de autenticar al usuario.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun alerta2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error a la hora de autenticar el Correo Electrónico.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun exito() {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Inicio")
        builder.setMessage("Inicio de sesión completado exitosamente")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
            checkUser()
        })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun checkUser(){
        val currentUser = Firebase.auth.currentUser

        if(currentUser != null){
            val intent = Intent(this, Principal::class.java)
            intent.putExtra("user", currentUser.email)
            startActivity(intent)

            finish()
        }
    }



}






