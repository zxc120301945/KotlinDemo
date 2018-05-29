package com.kotlin.demo

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class NewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.yemian).setText("New页面")
    }

    fun onClickXHToast(view : View){
        Toast.makeText(this, "第一个按钮", Toast.LENGTH_SHORT).show()
    }

    fun onClickNewActivity(view : View){
        Toast.makeText(this, "第二个按钮", Toast.LENGTH_SHORT).show()
    }
}