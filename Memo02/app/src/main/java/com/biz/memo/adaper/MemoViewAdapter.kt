package com.biz.memo.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.biz.memo.R
import com.biz.memo.domain.MemoVO

class MemoViewAdapter(context: Context, memoList: MutableList<MemoVO> , onDeleteButtonClickListener: (Any) -> Unit) : RecyclerView.Adapter<MemoViewAdapter.MemoHolder?>() {
    // item 삭제를 위한 이벤트 선
    interface OnDeleteButtonClickListener {
        fun onDeleteButtonClicked(id:Long)
    }

//    private lateinit var context: Context
    private var memoList: MutableList<MemoVO> = memoList
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var onDeleteButtonClickListener: OnDeleteButtonClickListener

    /*
    MainActivity에서 이벤트 인터페이스를 구현하고
    생성자에 주입한다.
     */
//    constructor(context: Context, onDeleteButtonClickListener: OnDeleteButtonClickListener?) {
//        this.context = context
//        if (onDeleteButtonClickListener != null) {
//            this.onDeleteButtonClickListener = onDeleteButtonClickListener
//        }
//    }

    fun setMemoList(memoList: MutableList<MemoVO>?) {
        this.memoList = memoList!!
    }

//    constructor(context: Context) {
//        this.context = context
//    }

    /*
        MainActivity에서 MemoViewAdapter를 만들때 Context와 memoList를 주입할 생성자
         */
//    constructor(context: Context, memoList: MutableList<MemoVO?>) {
//        this.context = context
//        this.memoList = memoList
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoHolder {

        /*
        memo_item.xml파일을 가져와서 view객체로 생성(확장)하기
        View view = LayoutInflater.from(context).inflate(R.layout.memo_item,
                            parent,
                false);
        */
//        val view = layoulayoutInflater.inflate(R.layout.memo_item, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item,parent,false)
        return MemoHolder(view)
    }

    override fun getItemCount(): Int {
        return if(memoList != null) memoList.size else 0
    }

    /*
        memoList의 개수만큼 반복문으로 호출되는 메서드
        반복문이 호출되면서 몇번째 데이터인가를 posion 변수에 주입해 준다.
    */
    override fun onBindViewHolder(holder: MemoHolder, position: Int) {
        /*
          RecyclerView.ViewHolder를 MemoHolder로 형변환 하여
          MemoHoder에 직접 접근할 수 있도록 한다.
       */
        val memoHolder = holder as MemoHolder

        /*
        memoList의 각 아이템 요소를 한개씩 읽어서
        TextView setText() method를 이용해서 문자열을 채워 넣어준다.
         */
        memoHolder.item_view_date.text = memoList[position]?.m_date.toString()
        memoHolder.item_view_time.text = memoList[position]?.m_time.toString()
        memoHolder.item_view_text.text = memoList[position]?.m_text.toString()
        memoHolder.item_delete.setOnClickListener(View.OnClickListener { memoList[position]?.id?.let { it1 -> onDeleteButtonClickListener.onDeleteButtonClicked(it1.toLong() ) } })
    }



    /*
        memo_item.xml에 설정한 여러가지 view들을 사용할수있도록 초기화 하는 과정
    */
    inner class MemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_view_time: TextView = itemView.findViewById(R.id.item_time)
        var item_view_date: TextView = itemView.findViewById(R.id.item_date)
        var item_view_text: TextView = itemView.findViewById(R.id.item_text)
        var item_delete: Button = itemView.findViewById(R.id.btn_delete)

    }



}