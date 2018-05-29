package com.kotlin.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val mBasicDataType: BasicDataType = BasicDataType()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.yemian).setText("Main页面")
    }

    fun onClickXHToast(view: View) {
        //比较两个值和地址是否相等
        mBasicDataType.demo(1,1)
        mBasicDataType.demo2()
    }

    fun onClickNewActivity(view: View) {
        startActivity(Intent(this, NewActivity::class.java))
    }

}
