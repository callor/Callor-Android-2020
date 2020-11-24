package com.callor.memo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.callor.memo.adapter.MemoAdapter
import com.callor.memo.domain.MemoVO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var memos = ArrayList<MemoVO>()

        var memo_list = R.id.memo_list


        // 리사클러뷰를 목록형태로 배치
        val layoutManager = LinearLayoutManager(this@MainActivity)

        memo_list

        // 이벤트 리스너에서 adapter를 호출하기 위해서는
        // final 키워드를 붙여준다.
        val adapter = MemoAdapter(this@MainActivity, memos)
        memo_list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(memo_list.context,
                LinearLayoutManager.VERTICAL)

        dividerItemDecoration.setDrawable(this.resources.getDrawable(R.drawable.deco_line, applicationContext.theme))
        memo_list.addItemDecoration(dividerItemDecoration)

        txt_memo = findViewById(R.id.txt_memo)
        val btn_save = findViewById<Button>(R.id.btn_save)
        btn_save.setOnClickListener {
            println("click")
            // 입력박스에 입력된 값을 꺼내서 strMemo에 임시 보관
            val strMemo = txt_memo.text!!.toString()

            // 입력이 되지 않았으면
            if (strMemo.isEmpty()) {
                Toast.makeText(this@MainActivity,
                        "메모를 입력하세요", Toast.LENGTH_SHORT).show()
            }

            // 현재 입력된 날짜 가져오기
            val now = System.currentTimeMillis() // 현재 날짜 시리얼 가져오기

            // 날짜를 원하는 문자열로 변환
            val date = Date(now) // 시리얼을 날짜 객체로 변환

            // 날짜를 문자열로 변환하기 위한 형식지정 2018-01-16
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val getDateTime = simpleDateFormat.format(date)

            // list를 보여주기 위해 VO에 추가
            memos!!.add(MemoVO(getDateTime, strMemo))

            val memo_count = memos!!.size.toString()
            Toast.makeText(this@MainActivity,
                    "메모개수:$memo_count", Toast.LENGTH_SHORT).show()

            // 데이터 리스트 내용이 변경되었다는 것을 adapter에게 알리기
            adapter.notifyDataSetChanged()
        }


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
