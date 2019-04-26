package com.techyourchance.mvc.screens.common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.mvc.screens.questiondetails.QuestionsDetailsMvc;
import com.techyourchance.mvc.screens.questiondetails.QuestionsDetailsMvcImpl;
import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvc;
import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvcImpl;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListViewMvcImpl(mLayoutInflater, parent, this);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListItemViewMvcImpl(mLayoutInflater, parent);
    }


    public QuestionsDetailsMvc getQuestionDetailsMvc(@Nullable ViewGroup parent) {
        return new QuestionsDetailsMvcImpl(mLayoutInflater, parent);
    }
}
