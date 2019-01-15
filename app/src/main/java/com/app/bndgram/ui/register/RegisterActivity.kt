package com.app.bndgram.ui.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

import com.app.bndgram.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cordinator)


        //val btn_click = findViewById(R.id.btn_click) as Button;


       /* btn_click.setOnClickListener({
            Toast.makeText(applicationContext, "Hi", Toast.LENGTH_LONG).show();
        })*/


        val items = listOf<String>("a", "b")


        for(i in items) {
            Log.e("Items", "" + i)
        }


       // val name : String = null





    }
}
