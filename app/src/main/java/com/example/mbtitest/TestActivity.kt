package com.example.mbtitest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class TestActivity : AppCompatActivity() {

    // ViewPager2 객체 초기화
    private lateinit var viewPager: ViewPager2

    // 질문 결과를 저장하는 객체
    val questionnaireResults = QuestionnaireResults()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // ViewPager2 설정
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
    }

    // 다음 질문으로 이동하는 함수
    fun moveToNextQuestion() {
        if (viewPager.currentItem == 3) {
            // 마지막페이지 -> 결과 화면으로 이동
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionnaireResults.results))
            startActivity(intent)
        } else {
            // currentItem = 현재 페이지 번호
            // 현재 페이지 번호를 가져옴
            val nextItem = viewPager.currentItem + 1
            // 다음 페이지로 이동
            if (nextItem < viewPager.adapter?.itemCount ?: 0) {
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }
}

// 질문 결과를 저장하는 클래스
class QuestionnaireResults {
    val results = mutableListOf<Int>()

    // 가장 빈도가 높은 응답을 선택
    fun addResponses(response: List<Int>) {
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        // 가장 빈도가 높은 응답을 결과 리스트에 추가
        mostFrequent?.let { results.add(it) }
    }
}