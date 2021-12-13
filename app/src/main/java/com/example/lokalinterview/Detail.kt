package com.example.lokalinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card.view.*

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var url=intent.extras?.getString("url","null")
        var title= intent.extras?.getString("title","null")
        Glide.with(this).load(url).into(imageView2)
        textView2.text= title
    }
}