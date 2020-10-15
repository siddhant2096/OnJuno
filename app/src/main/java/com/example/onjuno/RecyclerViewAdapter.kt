package com.example.onjuno

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onjuno.API.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_layout.view.*


class RecyclerViewAdapter(private var imagesList: MutableList<Photo>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var image_title: TextView
        var image_date: TextView
        var image_description: TextView
var play_button: ImageView
        init {
            imageView = itemView.list_item_image_view
            image_title = itemView.image_title
            image_date=itemView.image_date
            image_description=itemView.description
            play_button=itemView.VideoPreviewPlayButton
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        var itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        return RecyclerViewAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /// loading data into the views
        holder.image_title.text = imagesList[position].title
        holder.image_date.text=imagesList[position].date
        holder.image_description.text=imagesList[position].explanation
        if(imagesList[position].mediaType=="image") /// if media type is image, simply display the image
        {
            Picasso.get().load(imagesList[position].url).into(holder.imageView)
            holder.play_button.setVisibility(View.INVISIBLE);


        }
        else
        {var url=imagesList[position].url /// if media type is video, display the thumbnail, and on click redirect to youTube app
            var video_id=url.substring(30,41)
            var imageurl="https://img.youtube.com/vi/"+ url.substring(30,41) +"/mqdefault.jpg"/// for thumbnail
            Log.i("url",imageurl)
            Picasso.get().load(imageurl).into(holder.imageView)
            holder.play_button.setVisibility(View.VISIBLE);


            holder.imageView.setOnClickListener {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://$video_id")) // to redirect to youtube app
    ContextCompat.startActivity(context, intent, Bundle())

}
        }


        }
    }
