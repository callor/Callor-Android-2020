package com.biz.memo

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biz.memo.adaper.MemoViewAdapter
import com.biz.memo.adaper.MemoViewModel
import com.biz.memo.domain.MemoVO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // List<MemoVO> memoList = null;
    private lateinit var txtMemoInputText: TextInputEditText
    private lateinit var memoListView: RecyclerView
    private lateinit var viewAdapter: MemoViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    /*
    DB 연동을 위한 변수들 선언
     */
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)

        setSupportActionBar(toolbar)
        val btnSave = findViewById<Button?>(R.id.memo_save)

        btnSave.setOnClickListener(this)
        txtMemoInputText = findViewById(R.id.m_input_text)
        memoListView = findViewById(R.id.memo_list_view)
        var memoList : MutableList<MemoVO> = mutableListOf()
        viewAdapter = MemoViewAdapter(this,memoList) { post -> memoViewModel.delete(post as Long) }
        memoListView.adapter = viewAdapter

        memoViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MemoViewModel::class.java)
//         memoViewModel: MemoViewModelby viewModels

        /**
         * let 사용 규칙
         * 다음과 같은 경우에 let 을 사용합니다.
         * 지정된 값이 null 이 아닌 경우에 코드를 실행해야 하는 경우.
         * Nullable 객체를 다른 Nullable 객체로 변환하는 경우.
         * 단일 지역 변수의 범위를 제한 하는 경우.
         * 현재 선택된 객체가 null 이 아니면 onClick을 실행
         */
        memoViewModel.selectAll()?.observe(this, {
            it?.let {
                viewAdapter.setMemoList(it as MutableList<MemoVO>)
            }
            // viewAdapter.notifyDataSetChanged()
        })

        val layoutManager = LinearLayoutManager(this)
        memoListView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(memoListView.context,
                LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
                this.resources.getDrawable(
                        R.drawable.decoration_line, application.theme))
        memoListView.addItemDecoration(itemDecoration)
        val fab = findViewById<FloatingActionButton?>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id : Int? = item?.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    // @Override
    override fun onClick(v: View?) {
        val mMemoText = txtMemoInputText.text.toString()
        if (mMemoText.isEmpty()) {
            Toast.makeText(this@MainActivity,
                    "메모를 입력하세요", Toast.LENGTH_SHORT).show()
//            txtMemoInputText.setFocusable(true)
            return
        }
        val sd = SimpleDateFormat("yyyy-MM-dd")
        val st = SimpleDateFormat("HH:mm:ss")
        val date = Date(System.currentTimeMillis())
        val memoVO = MemoVO();

        memoVO.m_date = sd.format(date).toString();
        memoVO.m_time = st.format(date)
        memoVO.m_text = mMemoText
        memoViewModel.insert(memoVO)
        // memoList.add(memoVO);

        // RecyclerView의 Adapter한테 데이터가 변경되었으니 리스트를
        // 다시 그려라 라는 통보
        // view_adapter.notifyDataSetChanged();
//        txtMemoInputText.text = ""
    }
}