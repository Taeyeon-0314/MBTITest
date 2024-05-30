package com.example.mbtitest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 엣지 투 엣지 레이아웃을 활성화
        setContentView(R.layout.activity_result)

        // Intent로부터 결과 리스트를 가져옴, 없으면 빈 리스트로 초기화
        val results = intent.getIntegerArrayListExtra("results") ?: arrayListOf()

        // MBTI 결과를 매핑하기 위한 리스트
        val resultTypes = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )

        var resultString = ""

        // 결과 리스트를 통해 MBTI 결과 문자열 생성
        for (i in results.indices) {
            resultString += resultTypes[i][results[i] - 1]
        }

        // 결과를 표시할 TextView를 설정하고 결과 문자열을 설정
        val tv_resValue: TextView = findViewById(R.id.tv_resValue)
        tv_resValue.text = resultString

        val iv_ResImg: ImageView = findViewById(R.id.iv_resImg)
        val imageResource = resources.getIdentifier(
            "ic_${resultString.toLowerCase(Locale.ROOT)}",
            "drawable",
            packageName
        )
        iv_ResImg.setImageResource(imageResource)

        // 재시작 버튼을 설정하고 클릭 리스너를 추가
        val btn_retry: Button = findViewById(R.id.btn_res_retry)
        btn_retry.setOnClickListener {

            // MainActivity로 이동하는 인텐트를 생성하고 시작
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}