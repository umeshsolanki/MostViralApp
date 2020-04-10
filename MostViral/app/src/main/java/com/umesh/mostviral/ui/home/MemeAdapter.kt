package com.umesh.mostviral.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umesh.mostviral.R
import com.umesh.mostviral.databinding.MemeLayoutBinding
import com.umesh.mostviral.modals.Meme


class MemeAdapter(val context:Context,var memes:List<Meme>,val clickListener: MemeClickListener) : RecyclerView.Adapter<MemeAdapter.MemeviewHolder>() {

    lateinit var binding:MemeLayoutBinding

    private val TAG = "MemeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeviewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.meme_layout,parent,
            false)
        return MemeviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memes.size
    }


    override fun onBindViewHolder(holder: MemeviewHolder, position: Int) {
        val meme = memes.get(position)
        holder.bind(meme,clickListener)
    }

    inner class MemeviewHolder(itemView: MemeLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        var image:ImageView = itemView.image
        var points:TextView = itemView.points
        var title:TextView = itemView.title
        fun bind(meme: Meme,clickListener: MemeClickListener) {
            image.setImageDrawable(null)
//            Log.d(TAG,"Type: ${meme.type} ${meme.title}")
            Glide.with(context).load(meme.img).placeholder(R.drawable.loading).into(image)
            points.text = "${meme.points} Points"
            title.text = "${meme.title}"
            itemView.setOnClickListener {
                clickListener.memeClicked(meme)
            }
        }
    }
    companion object {
        interface MemeClickListener {
            fun memeClicked(meme: Meme)
        }
    }
}