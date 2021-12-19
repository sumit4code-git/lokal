package com.example.lokalinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokalinterview.Response.article
import com.example.lokalinterview.adapter.listAdapter
import com.example.lokalinterview.model.listViewModelProviderFactory
import com.example.lokalinterview.model.viewModel
import com.example.lokalinterview.repo.listreoisitory
import com.example.lokalinterview.utils.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    lateinit var listAdapter:listAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository= listreoisitory()
        val viewmodelProviderFactory= listViewModelProviderFactory(application,repository)
        var viewModel= ViewModelProvider(this,viewmodelProviderFactory).get(viewModel::class.java)
        setUpRecyclerView()
        listAdapter.setOnItemClickListner {
            val intent=Intent(this@MainActivity, Detail::class.java)
                intent.putExtra("url",it.url)
                intent.putExtra("title",it.title)
                startActivity(intent)

        }
        viewModel.albumList.observe(this, Observer { response->
            Log.d(TAG, "onCreate: ")
            when(response){
                is Resource.Sucess->{
                    response.data?.let { listResponse->
                        listAdapter.differ.submitList(listResponse)
                    }
                }
                is Resource.Error->{

                    response.message?.let { message->
                        Toast.makeText(this,"An error occurred!! $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading->{

                }
            }
        })
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