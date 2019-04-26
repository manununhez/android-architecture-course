package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvc;

public interface QuestionsDetailsMvc extends ViewMvc {
    void bindQuestionDetails(QuestionDetails questionDetails);
}
