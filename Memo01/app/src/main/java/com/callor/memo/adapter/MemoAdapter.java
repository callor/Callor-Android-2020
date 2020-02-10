package com.callor.memo.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.callor.memo.R;
import com.callor.memo.domain.MemoVO;

import java.util.List;

public class MemoAdapter  extends RecyclerView.Adapter {

        Context context = null ;
        List<MemoVO> memos = null;

        // 외부에서 Adapter에 데이터를 보내주기 위한 통로
        public MemoAdapter(Context context, List<MemoVO> memos) {
            this.context = context;
            this.memos = memos ;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.memo_item,parent,false) ;

            MemoHolder holder = new MemoHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            // RecyclerView.ViewHolder 를 MemoHoder로 변환
            MemoHolder memoHolder = (MemoHolder)holder ;

            memoHolder.txt_date.setText(memos.get(position).getStrDate());
            memoHolder.txt_memo.setText(memos.get(position).getStrMemo());

        }

        @Override
        public int getItemCount() {
            return memos.size();
        }


        class MemoHolder extends RecyclerView.ViewHolder {

            public TextView txt_date ;
            public TextView txt_memo ;

            public MemoHolder(View itemView) {
                super(itemView);

                txt_date = itemView.findViewById(R.id.txt_item_date);
                txt_memo = itemView.findViewById(R.id.txt_item_memo);

            }
        }


}
