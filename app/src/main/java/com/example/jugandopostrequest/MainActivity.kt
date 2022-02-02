package com.example.jugandopostrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jugandopostrequest.adapter.MyAdapter
import com.example.jugandopostrequest.databinding.ActivityMainBinding
import com.example.jugandopostrequest.model.Post
import com.example.jugandopostrequest.repository.MainRepository
import com.example.jugandopostrequest.viewmodel.MainViewModel
import com.example.jugandopostrequest.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val repository = MainRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        //viewModel.getCustomPost(2, "id", "asc")

        //val myPost = Post(2,2,"AgussTkd", "Android Developer")
        viewModel.pushPost2(2,2, "Aguss", "Android")
        viewModel.getPost("111222") //Random parameter

        viewModel.myCustomPost.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.myResponsePost.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("Main Activity", response.body().toString())
                Log.d("Main Activity", response.code().toString())
                Log.d("Main Activity", response.message().toString())
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })


        /*
        viewModel.getPost()
        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        binding.btnSend.setOnClickListener {
            val myNumber = binding.etId.text.toString()
            //viewModel.getCustomPost(Integer.parseInt(myNumber), "id", "desc") //Values hardcode, late implement a spinner with that values
            viewModel.getCustomPost2(Integer.parseInt(myNumber), options)  //Use a QueryMap

            viewModel.myCustomPost.observe(this, Observer { response ->
                if(response.isSuccessful){
                    binding.tvName.text = response.body().toString()

                    response.body()?.forEach {
                        Log.d("Response userId ", it.userId.toString())
                        Log.d("Response id ", it.id.toString())
                        Log.d("Response title ", it.title)
                        Log.d("Response body ", it.body)
                        Log.d("Response: ", "-----------------------------------------")
                    }

                }else{
                    Log.d("Response else: ", response.errorBody().toString())
                    binding.tvName.text = response.code().toString()
                }
            })
        }*/


    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}