package com.cdp.relativeex

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.cdp.relativeex.databinding.ActivityInfoAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class Info_admin : AppCompatActivity() {
    private lateinit var binding: ActivityInfoAdminBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        revisar()
/*
        val bundle = intent.extras
        val obtener = bundle?.getString("Admin")
         binding.infoa1.text = obtener
        Toast.makeText(this, obtener, Toast.LENGTH_SHORT).show()

 */
    }

    private fun revisar(){
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            firebaseUser?.let {
                val email = firebaseUser.email
                val uid = firebaseUser.uid
                binding.infoa3.text = email
                binding.infoa2.text = uid
            }
        }
    }

}