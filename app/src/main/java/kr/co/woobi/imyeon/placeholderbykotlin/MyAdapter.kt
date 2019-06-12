package kr.co.woobi.imyeon.placeholderbykotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MyAdapter(
    internal var context: Context,
    internal var modelList: List<Model>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  modelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       Picasso.get()
           .load(modelList[position].thumbnailUrl)
           .into(holder.thumbnail)

        holder.title.text=modelList[position].title
        holder.url.text=modelList[position].url
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: ImageView
        var title: TextView
        var url: TextView

        init {
            thumbnail=itemView.thumbnail
            title=itemView.title
            url=itemView.url
        }
    }
}