package bkp.com.studycourseapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quiz1Activity extends AppCompatActivity {

    private TextView question, no_counter;
    private LinearLayout options_layout;
    private Button next_btn;
    private int count = 0;
    private List<QuestionModel>list;
    private int position = 0;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        question = findViewById(R.id.questions);
        no_counter = findViewById(R.id.no_counter);
        options_layout = findViewById(R.id.optionsLayout);
        next_btn = findViewById(R.id.nextButton);

        list = new ArrayList<>();

        list.add(new QuestionModel(getString(R.string.q1),getString(R.string.op11),getString(R.string.op12),getString(R.string.op13),getString(R.string.op14),getString(R.string.ans1)));
        list.add(new QuestionModel(getString(R.string.q2),getString(R.string.op21),getString(R.string.op22),getString(R.string.op23),getString(R.string.op24),getString(R.string.ans2)));
        list.add(new QuestionModel(getString(R.string.q3),getString(R.string.op31),getString(R.string.op32),getString(R.string.op33),getString(R.string.op34),getString(R.string.ans3)));
        list.add(new QuestionModel(getString(R.string.q4),getString(R.string.op41),getString(R.string.op42),getString(R.string.op43),getString(R.string.op44),getString(R.string.ans4)));
        list.add(new QuestionModel(getString(R.string.q5),getString(R.string.op51),getString(R.string.op52),getString(R.string.op53),getString(R.string.op54),getString(R.string.ans5)));




        /*list.add(new QuestionModel("Day","November","Monday","April","March","Monday"));*/

        for(int i=0; i<4 ;i++){
            options_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    checkAnswer((Button)view);
                }
            });
        }

        playAnim(question, 0, list.get(position).getQuestion());
        next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                next_btn.setEnabled(false);
                next_btn.setAlpha(0.07f);
                enableOptions(true);
                position++;
                if(position == list.size()){
                    Intent intent = new Intent(Quiz1Activity.this,ScoreActivity.class);
                    finish();
                    intent.putExtra("score",score);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    return;
                }
                count=0;
                playAnim(question,0,list.get(position).getQuestion());
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOptions(boolean enable) {

        for(int i=0;i<4;i++){
            options_layout.getChildAt(i).setEnabled(enable);
            if(enable){
                options_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3d3636")));
            }
        }

    }

    private void playAnim(final View view, final int value, final String data) {

        view.animate().alpha(value).scaleX(value).scaleY(1).setDuration(500).setStartDelay(50)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                if(value==0 && count<4){
                    String option="";
                    if(count == 0){
                        option = list.get(position).getOptionA();
                    }else if(count == 1){
                        option = list.get(position).getOptionB();
                    }else if(count == 2){
                        option = list.get(position).getOptionC();
                    }else if(count == 3){
                        option = list.get(position).getOptionD();
                    }
                    playAnim(options_layout.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if(value==0){
                    try{
                        ((TextView)view).setText(data);
                        no_counter.setText(position + 1 + "/" + list.size());
                    } catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view,1,data);

                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedoption) {

        enableOptions(false);
        next_btn.setEnabled(true);
        next_btn.setAlpha(1);

        if(selectedoption.getText().toString().equals(list.get(position).getCorrectAns())){
            score++;
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            //StyleableToast.makeText(getApplicationContext(),"Right Answer",R.style.rightToast).show();
            Toast.makeText(this, "Right Answer", Toast.LENGTH_LONG).show();
        }else{
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption = (Button)options_layout.findViewWithTag(list.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#07f513")));
            //StyleableToast.makeText(getApplicationContext(),"Wrong Answer",R.style.wrongToast).show();
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Quiz1Activity.this,QuizActivity.class);
        startActivity(intent);
        finish();
    }
}