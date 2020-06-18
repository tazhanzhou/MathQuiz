package com.example.mobileapplicationsproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplicationsproject.model.Answer;
import com.example.mobileapplicationsproject.model.Question;
import com.example.mobileapplicationsproject.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static int REQUEST_CODE1 = 1;

    TextView textViewTitle, textViewQuestion;
    EditText editTextInput;
    private static final int[] idArray = {R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
            R.id.btn9};
    private Button btns[] = new Button[idArray.length];
    Button btnPoint, btnMinus, btnGenerate, btnValidate, btnClear,
            btnScore, btnFinish;
    StringBuilder textViewQuestionString = new StringBuilder();
    String rightResult;
    ArrayList<Question> questions;
    ArrayList<Answer> answers;
    String showQuestionString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextInput = findViewById(R.id.editTextInput);
        editTextInput.setInputType(InputType.TYPE_NULL);

        for (int i = 0; i < idArray.length; i++) {
            btns[i] = (Button) findViewById(idArray[i]);
            btns[i].setOnClickListener(this);
        }
        btnPoint = findViewById(R.id.btnPoint);
        btnPoint.setOnClickListener(this);

        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnGenerate = findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(this);

        btnValidate = findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnScore = findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
        setEnableBtn(false);

        questions = new ArrayList<>();
        answers = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //number button click
            case R.id.btn0:
                if (textViewQuestionString.length() == 1 && textViewQuestionString.toString().contains("0")) {
                    Toast.makeText(this, "Illogical Input!", Toast.LENGTH_SHORT).show();
                } else
                    editTextInput.setText((textViewQuestionString.append(0)).toString());
                break;
            case R.id.btn1:
                editTextInput.setText((textViewQuestionString.append(1)).toString());
                break;
            case R.id.btn2:
                editTextInput.setText((textViewQuestionString.append(2)).toString());
                break;
            case R.id.btn3:
                editTextInput.setText((textViewQuestionString.append(3)).toString());
                break;
            case R.id.btn4:
                editTextInput.setText((textViewQuestionString.append(4)).toString());
                break;
            case R.id.btn5:
                editTextInput.setText((textViewQuestionString.append(5)).toString());
                break;
            case R.id.btn6:
                editTextInput.setText((textViewQuestionString.append(6)).toString());
                break;
            case R.id.btn7:
                editTextInput.setText((textViewQuestionString.append(7)).toString());
                break;
            case R.id.btn8:
                editTextInput.setText((textViewQuestionString.append(8)).toString());
                break;
            case R.id.btn9:
                editTextInput.setText((textViewQuestionString.append(9)).toString());
                break;
            //function button click
            case R.id.btnPoint:
                if (textViewQuestionString.length() == 0) {
                    editTextInput.setText((textViewQuestionString.append("0.")).toString());
                } else if (!textViewQuestionString.toString().contains(".")) {
                    editTextInput.setText((textViewQuestionString.append(".")).toString());
                } else
                    Toast.makeText(this, "Illogical Input!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnMinus:
                if (textViewQuestionString.length() == 0) {
                    editTextInput.setText((textViewQuestionString.append("-")).toString());
                } else
                    Toast.makeText(this, "Illogical Input!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnGenerate:
                goGenerate();
                break;
            case R.id.btnValidate:
                goValidate();
                break;
            case R.id.btnClear:
                goClear();
                break;
            case R.id.btnFinish:
                finish();
                break;
            case R.id.btnScore:
                goResult();
                break;
        }
    }

    private void goGenerate() {
        goClear();
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        int operationCode = random.nextInt(4);
        if (operationCode == 3 && y == 0) {
            y++;
        }

        Question question = new Question(x, y, operationCode);
        questions.add(question);
        switch (operationCode) {
            case 0:
                rightResult = question.calculateAndRound();
                showQuestionString = String.valueOf(x) + "+" + String.valueOf(y) + "=?";
                break;
            case 1:
                rightResult = question.calculateAndRound();
                showQuestionString = String.valueOf(x) + "-" + String.valueOf(y) + "=?";
                break;
            case 2:
                rightResult = question.calculateAndRound();
                showQuestionString = String.valueOf(x) + "*" + String.valueOf(y) + "=?";
                break;
            case 3:
                rightResult = question.calculateAndRound();
                showQuestionString = String.valueOf(x) + "/" + String.valueOf(y) + "=?";
                break;
        }
        textViewQuestion.setText(showQuestionString);
        setEnableBtn(true);
    }

    private void goValidate() {
        if (editTextInput.getText().length() == 0) {
            Toast.makeText(this, "Enter an Answer first!", Toast.LENGTH_SHORT).show();
        } else {
            String stringUserAnswer = editTextInput.getText().toString();
            String strResult;
            boolean isCorrect;
            if (stringUserAnswer.equals(rightResult)) {
                strResult = "Right Answer!";
                isCorrect = true;
            } else {
                strResult = "Wrong Answer!";
                isCorrect = false;
            }
            Answer answer = new Answer(stringUserAnswer, showQuestionString, isCorrect);
            answers.add(answer);
            Toast.makeText(this, strResult, Toast.LENGTH_SHORT).show();
            textViewQuestion.setText("");
            goClear();
        }
    }

    private void goClear() {
        textViewQuestionString.delete(0, textViewQuestionString.length());
        editTextInput.setText("");
    }

    private void goResult() {
        User user = new User(questions, answers);

        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", user);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("intentExtra", bundle);

        startActivityForResult(intent, REQUEST_CODE1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String receivedData = (String) data.getStringExtra("return_result_tag");
        if (resultCode == RESULT_OK)
            textViewTitle.setText(receivedData);
    }

    private void setEnableBtn(boolean onOff) {
        for (Button b : btns) {
            b.setEnabled(onOff);
        }
        btnPoint.setEnabled(onOff);
        btnMinus.setEnabled(onOff);
        btnValidate.setEnabled(onOff);
        btnClear.setEnabled(onOff);
        btnScore.setEnabled(onOff);
    }
}