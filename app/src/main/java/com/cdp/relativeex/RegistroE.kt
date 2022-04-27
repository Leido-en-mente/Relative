package com.cdp.relativeex

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroE : AppCompatActivity() {
    private lateinit var regusuario: EditText
    private lateinit var regemail: EditText
    private lateinit var regcontra: EditText
    private lateinit var regcontra2: EditText
    private lateinit var registro1: Button
    private lateinit var switchb: Switch
    private lateinit var auth: FirebaseAuth
    private var firebaseAuth: FirebaseAuth? = null
    private var pgr: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_e)
        auth = Firebase.auth
        firebaseAuth = FirebaseAuth.getInstance()
        pgr = ProgressDialog(this)

        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Completado")
        analytics.logEvent("InitScreen", bundle)










        regusuario = findViewById(R.id.regusuario)
        regemail = findViewById(R.id.regemail)
        regcontra = findViewById(R.id.regcontra)
        regcontra2 = findViewById(R.id.regcontra2)
        registro1 =   findViewById(R.id.registro1)


        registro1.setOnClickListener {

            val usuar = regusuario.text.toString().trim()
            val email = regemail.text.toString().trim()
            val contra = regcontra.text.toString().trim()
            val contra2 = regcontra2.text.toString().trim()
            if (usuar.isEmpty()){
                regusuario.error = "Usuario Requerido"
                return@setOnClickListener
            }
            else if (email.isEmpty()){
                regemail.error = "Email Requerido"
                return@setOnClickListener
            }
            else if (contra.isEmpty()){
                regcontra.error = "Contraseña Requerida"
                return@setOnClickListener
            }
            else if (contra2.isEmpty()){
                regcontra2.error = "Contraseña Requerida"
                return@setOnClickListener
            }
           else if (contra.equals(contra2)) {
                registro()


            }
            else {
                Toast.makeText(this, "Error de registro!!", Toast.LENGTH_SHORT).show()
            }
        }




        switchb =  findViewById(R.id.admins)

        switchb.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Administrador Seleccionado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Usuario Normal", Toast.LENGTH_SHORT).show()
            }

        }






    }

    private fun registro() {

        val user = Firebase.auth.currentUser
        registro1.setOnClickListener {

                      auth.createUserWithEmailAndPassword(
                                regemail.text.toString(),
                                regcontra.text.toString()
                            ).addOnCompleteListener {

                                if (it.isSuccessful) {
                                    Log.d(TAG, "createUserWithEmail:success")
                                    if (user != null) {
                                        user.sendEmailVerification()
                                        exito()
                                    }
                                } else {
                                    alerta()
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

    private fun alerta() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error a la hora de autenticar al usuario.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun exito() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Inicio")
        builder.setMessage("Registro completado exitosamente")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->

        })
        val dialog: AlertDialog = builder.create()
        dialog.show()


        switchb =  findViewById(R.id.admins)
        switchb.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                val intent = Intent(this ,MainActivity::class.java)
                intent.putExtra("Admin", "Administrador")
                startActivity(intent)
            }
            else{
                val intent = Intent(this ,MainActivity::class.java)
                intent.putExtra("Admin", "Usuario")
                startActivity(intent)
            }

        }

        val intent = Intent(this ,MainActivity::class.java)
        startActivity(intent)



    }
/*
private fun mostrar(email: String, provider: ProviderType) {
val mostrars = Intent(this, MainActivity::class.java).apply {
    putExtra("email", email)
    putExtra("provider", provider.name)
}
    startActivity(mostrars)
}
*/

















}


