package com.callor.memo;

import android.os.Bundle;

import com.callor.memo.adapter.MemoAdapter;
import com.callor.memo.domain.MemoVO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView memo_list ;
    TextInputEditText txt_memo ;

    List<MemoVO> memos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        memos = new ArrayList<MemoVO>();

        memo_list = findViewById(R.id.memo_list);


        // 리사클러뷰를 목록형태로 배치
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this);

        memo_list.setLayoutManager(layoutManager);

        // 이벤트 리스너에서 adapter를 호출하기 위해서는
        // final 키워드를 붙여준다.
        final RecyclerView.Adapter adapter = new MemoAdapter(MainActivity.this,memos);
        memo_list.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(memo_list.getContext(),
                LinearLayoutManager.VERTICAL);

        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.deco_line));

        memo_list.addItemDecoration(dividerItemDecoration);

        txt_memo = findViewById(R.id.txt_memo);
        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("click");
                // 입력박스에 입력된 값을 꺼내서 strMemo에 임시 보관
                String strMemo = txt_memo.getText().toString();

                // 입력이 되지 않았으면
                if(strMemo.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "메모를 입력하세요",Toast.LENGTH_SHORT).show();
                }

                // 현재 입력된 날짜 가져오기
                long now = System.currentTimeMillis(); // 현재 날짜 시리얼 가져오기

                // 날짜를 원하는 문자열로 변환
                Date date = new Date(now); // 시리얼을 날짜 객체로 변환

                // 날짜를 문자열로 변환하기 위한 형식지정 2018-01-16
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String getDateTime = simpleDateFormat.format(date);

                // list를 보여주기 위해 VO에 추가
                memos.add(new MemoVO(getDateTime,strMemo));

                String memo_count = String.valueOf(memos.size());
                Toast.makeText(MainActivity.this,
                        "메모개수:" + memo_count,Toast.LENGTH_SHORT ).show();

                // 데이터 리스트 내용이 변경되었다는 것을 adapter에게 알리기
                adapter.notifyDataSetChanged();

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
