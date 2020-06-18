package com.example.mobileapplicationsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplicationsproject.model.Answer;
import com.example.mobileapplicationsproject.model.Question;
import com.example.mobileapplicationsproject.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    EditText editTextName;
    TextView textViewScore;
    ListView listView;
    Button buttonShow;
    Button buttonBack;

    ArrayList<Answer> answers;
    ArrayList<Question> questions;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        radioGroup = findViewById(R.id.rg);
        editTextName = findViewById(R.id.editTextName);
        textViewScore = findViewById(R.id.textViewScore);
        listView = findViewById(R.id.listView);

        buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(this);

        buttonShow = findViewById(R.id.btnShow);
        buttonShow.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("intentExtra");
        Serializable bundledUser = bundle.getSerializable("bundleExtra");
        user = (User) bundledUser;
        answers = user.getAnswers();

        showDataInListView(answers);
    }

    private void showDataInListView(ArrayList<Answer> a) {
        String[] data = new String[a.size()];
        for (int i = 0; i < a.size(); i++) {
            data[i] = a.get(i).toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ResultActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    public void radioGroup(View view) {
        int selectedRadioBtn = radioGroup.getCheckedRadioButtonId();
        switch (selectedRadioBtn) {
            case R.id.radioButtonSortD:
                Comparator c = Collections.reverseOrder();
                Collections.sort(answers, c);
                showDataInListView(answers);
                break;
            case R.id.radioButtonSortA:
                Collections.sort(answers);
                showDataInListView(answers);
                break;
            case R.id.radioButtonAll:
                showDataInListView(answers);
                break;
            case R.id.radioButtonRight:
                boolean bt = true;
                showDataInListView(filterAnswersByResult(answers, bt));
                break;
            case R.id.radioButtonWrong:
                boolean bf = false;
                showDataInListView(filterAnswersByResult(answers, bf));
                break;
        }
    }

    private ArrayList<Answer> filterAnswersByResult(ArrayList<Answer> answers, boolean isCorrect) {
        ArrayList<Answer> answerListByResult = new ArrayList<>();
        for (Answer a : answers) {
            if (a.isCorrect() == isCorrect) {
                answerListByResult.add(a);
            }
        }
        return answerListByResult;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShow:
                textViewScore.setText(user.getScore() + "%");
                break;
            case R.id.btnBack:
                if (editTextName.getText().length() == 0) {
                    Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_LONG).show();
                } else {
                    user.setName(editTextName.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("return_result_tag", user.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
}