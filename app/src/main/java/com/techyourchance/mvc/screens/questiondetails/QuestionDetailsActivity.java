package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private StackoverflowApi mStackoverflowApi;
    private String mQuestionId;
    private QuestionsDetailsMvc mViewMvc;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mQuestionId = intent.getStringExtra(EXTRA_QUESTION_ID);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsMvc(null);

        mStackoverflowApi = getCompositionRoot().getStackoverflowApi();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionDetails();
    }

    private void fetchQuestionDetails() {
        mStackoverflowApi.fetchQuestionDetails(mQuestionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful()) {
                            bindQuestions(response.body().getQuestion());
                        } else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        networkCallFailed();
                    }
                });
    }

    private void bindQuestions(QuestionSchema questionSchemas) {
        QuestionDetails questionDetails = new QuestionDetails(questionSchemas.getId(),
                questionSchemas.getTitle(), questionSchemas.getBody());

        mViewMvc.bindQuestionDetails(questionDetails);
    }

    private void networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
