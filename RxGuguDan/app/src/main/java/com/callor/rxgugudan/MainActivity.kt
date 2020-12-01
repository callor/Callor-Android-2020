package com.callor.rxgugudan


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.subjects.BehaviorSubject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed = findViewById<EditText>(R.id.ed)
        var tv = findViewById<TextView>(R.id.tv)

        val subject: BehaviorSubject<String> = BehaviorSubject.createDefault("0")

        ed.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject.map { ed.text.toString() == "" }
                        .flatMap({ BehaviorSubject.range(1, 9) }
                        ) { _, row -> "0 x $row = 0\n" }
                        .scan { x, y -> x + y }
                        .subscribe { text -> tv.text = text }

                subject.map {ed.text.toString() == ""}
                        .flatMap( {BehaviorSubject.range(1,9)})
                        { d, row -> "$d x $row = 0\n"}

                subject.map { ed.text.toString().toLong() }
                        .flatMap({ BehaviorSubject.range(1, 9) }
                        ) { dan, row -> dan.toString() + " x " + row + " = " + dan * row + "\n" }
                        .scan { x, y -> x + y }
                        .doOnNext { data -> Log.d("onNext()", data) }
                        .subscribe({ text -> tv.text = text }) { obj: Throwable -> obj.message }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }
}