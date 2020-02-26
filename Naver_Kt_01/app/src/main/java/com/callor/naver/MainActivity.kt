package com.callor.naver

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.callor.naver.adapter.MovieAdapter
import com.callor.naver.config.NaverSecur
import com.callor.naver.domain.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val txtSearch = txt_search
        val recyclerView = movie_list

        // txtSearch.setOnEditorActionListener { v, actionId, event ->
        // 사용하지 않은 파라메터 이름 _ 로 변경
        txtSearch.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                val strSearch = txtSearch.text.toString()
                val naverCall = RetrofitClient.getApiService().getSearch(NaverSecur.NAVER_ID,NaverSecur.NAVER_SEC,"movie.json",strSearch)
                naverCall.enqueue(object : Callback<Movie>{

                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        val mList: Array<Movie.Items>? = response.body()?.items

                        val movieAdapter = mList?.let { MovieAdapter(it) }
                        recyclerView.adapter = movieAdapter

                        val layoutManager =
                            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView.layoutManager = layoutManager

                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
                true
            } else {
                false
            }
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

