package com.example.mbtitest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// ViewPager2 어댑터 클래스
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    // 총 페이지 수를 반환하는 메소드
    override fun getItemCount(): Int {
        return 4 // 총 4개의 질문 프래그먼트가 있음
    }

    // 주어진 위치에 맞는 프래그먼트를 생성하여 반환하는 메소드
    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position)// 해당 위치에 맞는 QuestionFragment 인스턴스를 반환
    }

}