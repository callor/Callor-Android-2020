package com.biz.naver.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.naver.R;
import com.biz.naver.domain.NaverMovieVO;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Parameter;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    List<NaverMovieVO> movieVOList;
    Context context ;

    public MovieAdapter(List<NaverMovieVO> movieVOList) {
        this.movieVOList = movieVOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.movie_item_view,
                                parent,
                                false
                        );
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieHolder mHolder = (MovieHolder) holder;

        mHolder.txt_title.setText(this.getHTML("<font color=blue>" + movieVOList.get(position).getTitle() + "</font>"));
        mHolder.txt_director.setText(movieVOList.get(position).getDirector());
        mHolder.txt_actor.setText(movieVOList.get(position).getActor());

        int intRating = (int)(Float.valueOf(movieVOList.get(position).getUserRating())/2);

        String strRating = "";
        for(int i = 0 ; i < intRating ; i++) {
            strRating += "★ ";
        }
        strRating = "<b>평점</b> : <font color=blue>" + strRating + "</font></b>";
        mHolder.txt_rating.setText(getHTML(strRating));

        // 이미지 세팅 잠시 보류
        if(!movieVOList.get(position).getImage().isEmpty()) {
            // Picasso.get().load(movieVOList.get(position).getImage()).into(mHolder.m_image);
        }

        if(!movieVOList.get(position).getImage().isEmpty()) {
            Glide.with(mHolder.itemView.getContext()).load(movieVOList.get(position).getImage()).into(mHolder.m_image);
        }




    }

    // 문자열 속에 포함된 HTML 코드를 실제 화면에 보이도록 변경
    private Spanned getHTML(String strText) {
        Spanned spText ;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spText = Html.fromHtml(strText,Html.FROM_HTML_MODE_LEGACY);
        } else {
            spText = Html.fromHtml(strText);
        }
        return spText ;
    }


    @Override
    public int getItemCount() {
        return movieVOList == null ? 0 : movieVOList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_director;
        TextView txt_actor;
        TextView txt_rating;
        ImageView m_image;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.m_item_title);
            txt_director = itemView.findViewById(R.id.m_item_director);
            txt_actor = itemView.findViewById(R.id.m_item_actor);
            txt_rating = itemView.findViewById(R.id.m_item_rating);
            m_image = itemView.findViewById(R.id.m_item_image);


        }
    }

}
