package com.cbnu.dementiadiagnosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import QuizPage.memoryInput_Page;
import QuizPage.orientation_Page;

public class QuizHOME extends AppCompatActivity {

    TextView Title;
    TextView Announce;
    TextView Intend_value;
    Button sttBtn;
    private boolean isDone[];
    private int current = 0;
    private ArrayList<String> first, second;
    public List<String> announce;
    private long backBtnTime = 0;
    private int total_score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_home);

        announce = new ArrayList();
        isDone = new boolean[8];
        Arrays.fill(isDone,false);

        sttBtn = findViewById(R.id.sttStart);
        Title = findViewById(R.id.title);
        Announce = findViewById(R.id.announce);
        Intend_value = findViewById(R.id.intent);

        first = new ArrayList<>();
        second = new ArrayList<>();

        init_Announce();

        Title.setText(announce.get(current));

        sttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switchPage(intent, view);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);
        if (requestCode == 100 && resultCode == 1) {
            isDone[current] = resultIntent.getBooleanExtra("isDone", false);
            total_score += resultIntent.getIntExtra("score", 0);
        }
        if (requestCode == 100 && resultCode == 2) {
            isDone[current] = resultIntent.getBooleanExtra("isDone", false);
            first = resultIntent.getStringArrayListExtra("First");
            second = resultIntent.getStringArrayListExtra("Second");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isDone[current]){
            current ++;
            Title.setText(announce.get(current));
            if(current == 1){
                Intend_value.setText(Integer.toString(total_score));
            }
            else if(current == 2){
                String tem = "맞춘 말 : ";
                for(int i = 0; i< first.size(); i++) {
                    tem += first.get(i);
                }
                Intend_value.setText(tem);
            }
            Toast.makeText(this, "결과가 저장되었습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void setCurrent(int score){
        isDone[current] = true;
        total_score += score;
    }

    private void init_Announce(){

        announce.add("지금부터 '지남력'을\n검사해보겠습니다.");
        announce.add("지금부터 '기억 등록'을\n시행하겠습니다.");
        announce.add("지금부터 '주의력'을\n검사해보겠습니다.");
        announce.add("지금부터 '시공간 기능'을\n검사해보겠습니다.");
        announce.add("지금부터 '집행 기능'을\n검사해보겠습니다.");
        announce.add("지금부터 '기억력'을\n검사해보겠습니다.");
        announce.add("지금부터 '언어 기능'을\n검사해보겠습니다.");
        announce.add("지금부터 '유창성'을\n검사해보겠습니다.");

    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this, "지금 나가시면 진행된 검사가 저장되지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void switchPage(Intent intent, View view){
        switch (current){

            case 0:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 1:
                intent = new Intent(view.getContext(), memoryInput_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 2:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 3:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 4:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 5:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 6:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 7:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;
            case 8:
                intent = new Intent(view.getContext(), orientation_Page.class);
                startActivityForResult(intent, 100);
                break;

        }


    }
}