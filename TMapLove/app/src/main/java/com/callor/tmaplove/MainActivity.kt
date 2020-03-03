package com.callor.tmaplove

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPOIItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import com.skt.Tmap.TMapView.OnClickListenerCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tmap api 활성화
        var tmapView:TMapView = TMapView(this);
        // api key 설정
        tmapView.setSKTMapApiKey(getString(R.string.apikey))

        // linearView에 map 부착
        linearTmap.addView(tmapView)

        tmapView.setOnClickListenerCallBack(object : OnClickListenerCallback{
            @SuppressLint("RestrictedApi")
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPressEvent(
                p0: ArrayList<TMapMarkerItem>?,
                p1: ArrayList<TMapPOIItem>?,
                p2: TMapPoint?,
                p3: PointF?
            ): Boolean {
                Toast.makeText(this@MainActivity,p3.toString(),Toast.LENGTH_LONG).show() //To change body of created functions use File | Settings | File Templates.
                var marker = TMapMarkerItem()
                var tmapPoint = TMapPoint(p2!!.latitude,p2!!.longitude)


                val bitmap =
                    BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ic_person_pin_circle_black_24dp);

                marker.icon = bitmap
                marker.setPosition(0.5f,1.0f)
                marker.tMapPoint = tmapPoint
                marker.name = "내마커"
                var rnd = Random.nextInt()
                tmapView.addMarkerItem(rnd.toString(),marker)
                // tmapView.setCenterPoint(p2!!.longitude, p2!!.latitude)

                return true
            }

            override fun onPressUpEvent(
                p0: ArrayList<TMapMarkerItem>?,
                p1: ArrayList<TMapPOIItem>?,
                p2: TMapPoint?,
                p3: PointF?
            ): Boolean {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                return true
            }

        })


    }
}
