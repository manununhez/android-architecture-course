package com.techyourchance.mvc.screens.questiondetails;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseViewMvc;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;

public class QuestionsDetailsMvcImpl extends BaseViewMvc implements QuestionsDetailsMvc {
    private final TextView mTxtTitle;
    private final TextView mTxtBody;

    public QuestionsDetailsMvcImpl(LayoutInflater mLayoutInflater, ViewGroup parent) {
        setRootView(mLayoutInflater.inflate(R.layout.layout_question_details, parent, false));

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtBody = findViewById(R.id.txtBody);

        mTxtBody.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public void bindQuestionDetails(QuestionDetails questionDetails) {
        mTxtTitle.setText(questionDetails.getTitle());
        mTxtBody.setText(questionDetails.getBody());
    }
}
