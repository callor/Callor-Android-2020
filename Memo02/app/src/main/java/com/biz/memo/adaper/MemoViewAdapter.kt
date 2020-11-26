package com.biz.memo.adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.biz.memo.R
import com.biz.memo.domain.MemoVO

class MemoViewAdapter(memoList: MutableList<MemoVO>, onDeleteButtonClickListener: (Any) -> Unit) : RecyclerView.Adapter<MemoViewAdapter.MemoHolder?>() {


    // item 삭제를 위한 이벤트 선언
//    interface OnDeleteButtonClickListener {
//        fun onDeleteButtonClicked(id:Long)
//    }


    private var memoList: MutableList<MemoVO> = memoList
    private val onDeleteButtonClickListener: (Any) -> Unit = onDeleteButtonClickListener

    fun setMemoList(memoList: MutableList<MemoVO>?) {
        this.memoList = memoList!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoHolder {

        // memo_item.xml 을 확장하여 item view holder로 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return MemoHolder(view)

    }

    override fun getItemCount(): Int {
        return if (memoList != null) memoList.size else 0
    }

    /*
        memoList의 개수만큼 반복문으로 호출되는 메서드
        반복문이 호출되면서 몇번째 데이터인가를 posion 변수에 주입해 준다.
        데이터 개수만큼 반복 호출된다.
        단, 화면에 보이는 개수만큼 호출된다.
    */
    override fun onBindViewHolder(holder: MemoHolder, position: Int) {
        /*
          RecyclerView.ViewHolder를 MemoHolder로 형변환 하여
          MemoHoder에 직접 접근할 수 있도록 한다.
       */
        val memoHolder = holder

        /*
        memoList의 각 아이템 요소를 한개씩 읽어서
        TextView setText() method를 이용해서 문자열을 채워 넣어준다.
         */
        memoHolder.item_view_date.text = memoList[position].m_date.toString()
        memoHolder.item_view_time.text = memoList[position].m_time.toString()
        memoHolder.item_view_text.text = memoList[position].m_text.toString()
        memoHolder.item_delete.setOnClickListener(View.OnClickListener { memoList[position].id.let { it1 -> onDeleteButtonClickListener(it1.toLong()) } })
    }


    /*
        memo_item.xml에 설정한 여러가지 view들을 사용할수있도록 초기화 하는 과정
    */
    class MemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_view_time: TextView = itemView.findViewById(R.id.item_time)
        var item_view_date: TextView = itemView.findViewById(R.id.item_date)
        var item_view_text: TextView = itemView.findViewById(R.id.item_text)
        var item_delete: Button = itemView.findViewById(R.id.btn_delete)

    }


    object MemoVODiffCallback : DiffUtil.ItemCallback<MemoVO>() {
        override fun areItemsTheSame(oldItem: MemoVO, newItem: MemoVO): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: MemoVO, newItem: MemoVO): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MemoListDiffCallback(
            private val oldItems: List<Any>,
            private val newItems: List<Any>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return this.oldItems.size
        }

        override fun getNewListSize(): Int {
            return this.newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

             return oldItem == newItem // 대신 사용
            
//            return if (oldItem is SampleAdapterSelectViewModel.SelectItem.Section && newItem is SampleAdapterSelectViewModel.SelectItem.Section) {
//                oldItem.title == newItem.title
//            } else if (oldItem is SampleAdapterSelectViewModel.SelectItem.Item && newItem is SampleAdapterSelectViewModel.SelectItem.Item) {
//                oldItem.title == newItem.title
//            } else {
//                // 위 2개 조건이 맞지 않다면 서로 상호 아이템을 체크한다.
//                oldItem == newItem
//            }
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem == newItem
        }
    }



}


