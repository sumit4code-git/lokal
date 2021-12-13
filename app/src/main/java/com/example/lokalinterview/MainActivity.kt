package com.example.lokalinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokalinterview.adapter.listAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var listAdapter:listAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        listAdapter.setOnItemClickListner {
            Intent(this, Detail::class.java).apply {
                intent.putExtra("url",it.url)
                intent.putExtra("title",it.title)
                startActivity(this)
            }

        }
    }

    //SettingUp RecyclerView initially
    private fun setUpRecyclerView(){
        listAdapter=listAdapter()
        rv_listItem.apply {
            adapter=listAdapter
            layoutManager= LinearLayoutManager(this@MainActivity)
//            addOnScrollListener(this@.scrollListner)
        }
    }
}