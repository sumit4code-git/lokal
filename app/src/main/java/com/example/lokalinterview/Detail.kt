package com.example.lokalinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card.view.*

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var url: String? =intent.getStringExtra("url")
        var title= intent.getStringExtra("title")
        Log.d("sss", "onCreate: "+url+title)
        Glide.with(applicationContext).load(url).into(imageView2)
        textView2.text= title
    }
}