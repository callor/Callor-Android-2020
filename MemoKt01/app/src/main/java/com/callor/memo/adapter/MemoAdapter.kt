package com.callor.memo.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.callor.memo.R
import com.callor.memo.domain.MemoVO

class MemoAdapter// 외부에서 Adapter에 데이터를 보내주기 위한 통로
(context: Context, memos: List<MemoVO>) : RecyclerView.Adapter<*>() {

    internal var context: Context? = null
    internal var memos: List<MemoVO>? = null

    init {
        this.context = context
        this.memos = memos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(context)
                .inflate(R.layout.memo_item, parent, false)

        return MemoHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // RecyclerView.ViewHolder 를 MemoHoder로 변환
        val memoHolder = holder as MemoHolder

        memoHolder.txt_date.setText(memos!![position].getStrDate())
        memoHolder.txt_memo.setText(memos!![position].getStrMemo())

    }

    override fun getItemCount(): Int {
        return memos!!.size
    }


    internal inner class MemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_date: TextView
        var txt_memo: TextView

        init {

            txt_date = itemView.findViewById(R.id.txt_item_date)
            txt_memo = itemView.findViewById(R.id.txt_item_memo)

        }
    }


}
