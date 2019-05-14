package com.example.livestreamfails.mvp.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.livestreamfails.R
import com.example.livestreamfails.VideoModel
import com.squareup.picasso.Picasso

class VideoAdapters : RecyclerView.Adapter<VideoAdapters.VideoVH>() {

    var items = ArrayList<VideoModel>()
        set(value) {
            if (value.size > field.size) {
                val itemsCount = value.size - items.size
                field.addAll(value.subList(field.size, value.size))
                notifyItemRangeChanged(field.size - itemsCount, itemsCount)
            }else {
                field = value
                notifyDataSetChanged()
            }
        }

    var itemClickListener: ((pos: Int) -> Unit)? = null

    fun setOnItemClick(callback:  ((pos: Int) -> Unit)){
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): VideoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.videoitem, parent, false)
        return VideoVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(vh: VideoVH, pos: Int) {
        val item = items[pos]
        Picasso.get().load(item.imageLink)
            .into(vh.image)
        vh.image.setOnClickListener { itemClickListener?.invoke(pos) }
    }

    class VideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_image)
    }
}