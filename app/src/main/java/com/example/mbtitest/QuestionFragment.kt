package com.example.mbtitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    //질문 타입을 저장하는 변수
    private var questionType: Int = 0

    // 질문 제목 리스트
    private val questionTile = listOf(
        R.string.question1_title,
        R.string.question2_title,
        R.string.question3_title,
        R.string.question4_title
    )

    // 각 질문에 대한 텍스트 리스트
    private val questionTexts = listOf(
        listOf(R.string.question1_1, R.string.question1_2, R.string.question1_3),
        listOf(R.string.question2_1, R.string.question2_2, R.string.question2_3),
        listOf(R.string.question3_1, R.string.question3_2, R.string.question3_3),
        listOf(R.string.question4_1, R.string.question4_2, R.string.question4_3)
    )

    // 각 질문의 답변 리스트
    private val questionAnswers = listOf(
        listOf(
            listOf(R.string.question1_1_answer1, R.string.question1_1_answer2),
            listOf(R.string.question1_2_answer1, R.string.question1_2_answer2),
            listOf(R.string.question1_3_answer1, R.string.question1_3_answer2)
        ),
        listOf(
            listOf(R.string.question2_1_answer1, R.string.question2_1_answer2),
            listOf(R.string.question2_2_answer1, R.string.question2_2_answer2),
            listOf(R.string.question2_3_answer1, R.string.question2_3_answer2)
        ), listOf(
            listOf(R.string.question3_1_answer1, R.string.question3_1_answer2),
            listOf(R.string.question3_2_answer1, R.string.question3_2_answer2),
            listOf(R.string.question3_3_answer1, R.string.question3_3_answer2)
        ),
        listOf(
            listOf(R.string.question4_1_answer1, R.string.question4_1_answer2),
            listOf(R.string.question4_2_answer1, R.string.question4_2_answer2),
            listOf(R.string.question4_3_answer1, R.string.question4_3_answer2)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // arguments에서 questionType 값을 가져옴
        arguments?.let {
            questionType = it.getInt(ARG_QUESTION_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflater를 통해 설정
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // 질문 제목 설정
        val title: TextView = view.findViewById(R.id.tv_question_title)
        title.text = getString(questionTile[questionType])

        // 각 질문 텍스트뷰와 라디오 그룹을 리스트로 설정
        val questionTextView = listOf<TextView>(
            view.findViewById(R.id.tv_question_1),
            view.findViewById(R.id.tv_question_2),
            view.findViewById(R.id.tv_question_3)
        )

        val answerRadioGroup = listOf<RadioGroup>(
            view.findViewById(R.id.rg_answer_1),
            view.findViewById(R.id.rg_answer_2),
            view.findViewById(R.id.rg_answer_3)
        )
        // 각 질문과 답변 설정
        for (i in questionTextView.indices) {
            questionTextView[i].text = getString(questionTexts[questionType][i])

            val radioButton1 = answerRadioGroup[i].getChildAt(0) as RadioButton
            val radioButton2 = answerRadioGroup[i].getChildAt(1) as RadioButton

            radioButton1.text = getString(questionAnswers[questionType][i][0])
            radioButton2.text = getString(questionAnswers[questionType][i][1])
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 각 라디오 그룹과 버튼을 설정
        val anserRadioGroup = listOf<RadioGroup>(
            view.findViewById(R.id.rg_answer_1),
            view.findViewById(R.id.rg_answer_2),
            view.findViewById(R.id.rg_answer_3)
        )
        val btn_next: Button = view.findViewById(R.id.btn_next)

        btn_next.setOnClickListener {
            // 모든 질문이 답변되었는지 확인
            val isAllAnswered = anserRadioGroup.all { it.checkedRadioButtonId != -1 }

            if (isAllAnswered) {
                // 각 질문에 대한 응답을 저장
                val responses = anserRadioGroup.map { radioGroup ->
                    val firstRadioButton = radioGroup.getChildAt(0) as RadioButton
                    if (firstRadioButton.isChecked) 1 else 2
                }
                (activity as? TestActivity)?.questionnaireResults?.addResponses(responses)
                (activity as? TestActivity)?.moveToNextQuestion()
            } else {
                Toast.makeText(context, "모든 질문에 답해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ARG_QUESTION_TYPE = "questionType"

        // QuestionFragment 인스턴스를 생성하는 메소드
        fun newInstance(questionType: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(ARG_QUESTION_TYPE, questionType)
            fragment.arguments = args
            return fragment
        }
    }
}