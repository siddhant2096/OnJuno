package com.example.onjuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.onjuno.API.Photo
import com.example.onjuno.API.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var ImageAdapter: RecyclerViewAdapter


    private val dataList: MutableList<Photo> = mutableListOf() /// arrayList to hold all items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /// hitting API using Android Networking
ImageAdapter= RecyclerViewAdapter(dataList) /// initialsing the recylerView Adapter
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://demo0405353.mockable.io/get-nasa-photos")
            .build().getAsObject(
                Result::class.java,
                object : ParsedRequestListener<com.example.onjuno.API.Result> {

                    override fun onError(anError: ANError?) {
                        Log.i("error",anError.toString())
                    }

                    override fun onResponse(response: com.example.onjuno.API.Result?) {
                        if (response != null) {
                            dataList.addAll(response.photos) /// loading all items from API to arrayList
                           ImageAdapter.notifyDataSetChanged() /// notifying the recyveler view, that data set is changed

                            Log.i("success","sss")
                        }
                    }

                }
            )
        /// setting up and loading the adapter into the recylerView and
        images_RecylerView.layoutManager =  LinearLayoutManager(this, LinearLayout.VERTICAL, false)

      images_RecylerView.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
       images_RecylerView.adapter = ImageAdapter
    }

    }

