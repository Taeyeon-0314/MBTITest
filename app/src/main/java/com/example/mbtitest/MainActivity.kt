package com.example.mbtitest

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이미지뷰(btn_start) 요소를 가져옴
        val btn_start = findViewById<ImageView>(R.id.iv_start)
        // 이미지뷰 클릭시 실행되는 코드
        btn_start.setOnClickListener {
            // TestActivity로 이동하는 인텐드를 생성하고 시작
            val intent = Intent(this@MainActivity, TestActivity::class.java)
            startActivity(intent)
        }
    }
}