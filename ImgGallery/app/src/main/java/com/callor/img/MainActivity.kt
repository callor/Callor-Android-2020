package com.callor.img

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.callor.img.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        imageView = findViewById(R.id.my_image)


        // 쓰기 권한이 없으면 권한을 획득하도록 팝업 띄우기
        if (ActivityCompat.checkSelfPermission(this@MainActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val strReq = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "저장 권한이 필요합니다"
            )
            ActivityCompat.requestPermissions(this@MainActivity, strReq, 1000)
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { // phone에 있는 기본겔러리를 호출하는 암시적 인텐트

            val imgIntent = Intent(Intent.ACTION_PICK)
            imgIntent.type = MediaStore.Images.Media.CONTENT_TYPE
            imgIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(imgIntent, REQ_CODE_SELECT_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 겔러리에서 보내온 파일이름으로 부터 실제 이미를 가져오기
                try {
                    val `in` = contentResolver.openInputStream(data!!.data!!)
                    val imgBitMap = BitmapFactory.decodeStream(`in`)
                    `in`!!.close()
                    imageView!!.setImageBitmap(imgBitMap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
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
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    companion object {
        private const val REQ_CODE_SELECT_IMAGE = 1
    }
}