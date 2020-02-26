package com.callor.naver.adapter

import android.os.Build
import android.text.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.callor.naver.R
import com.callor.naver.domain.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_view.view.*


class MovieAdapter(private val mList : Array<Movie.Items>) : Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(itemView: View) : ViewHolder(itemView){

        val txtTitle:TextView = itemView.m_item_title
        val txtDirector:TextView = itemView.m_item_director
        val txtActor:TextView = itemView.m_item_actor
        val txtRating:TextView = itemView.m_item_rating
        val mImage:ImageView = itemView.m_item_image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_view,parent,false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        holder.txtTitle.text = getHTML("<font color=blue>${mList[position].title}</font>")
        holder.txtDirector.text = getHTML("<b>감독 : </b>${mList[position].director}")
        holder.txtActor.text = getHTML("<b>주연 : </b>${mList[position].actor}")

        try {
            val intRating = (mList[position].userRating.toDouble() / 2).toInt()
            var strRating = ""
            for (i in 0 until intRating) {
                strRating += "★"
            }
            strRating = "<b>평점 : </b><font color=blue>$strRating</font>"
            holder.txtRating.text = getHTML(strRating)
        } catch (e: Exception) {
            Log.d("ADAPTER",e.message.toString())
        }

        if(!mList[position].image.isNullOrEmpty()) {
            Picasso.get().load(mList[position].image).into(holder.mImage)
        }
    }

    private fun getHTML(str:String): Spanned? {
//        var spannable : Spannable = SpannableStringBuilder.valueOf(str)
//        var backgroundColorSpan = BackgroundColorSpan(Color.BLUE)
//        spannable.setSpan(backgroundColorSpan,0,str.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //
//        BackgroundColorSpan(Color.BLUE)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(str, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(str)
        }
    }

    override fun getItemCount(): Int = mList.size

}