package com.example.mycar6;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class car_guide extends AppCompatActivity {

    LinearLayout matchlayout1,matchlayout2,matchlayout3,matchlayout4;

    ImageButton btn_back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_guide);

        matchlayout1=findViewById(R.id.matchlayout1);
        matchlayout2=findViewById(R.id.matchlayout2);
        matchlayout3=findViewById(R.id.matchlayout3);
        matchlayout4=findViewById(R.id.matchlayout4);

        btn_back=findViewById(R.id.btn_back);

        matchlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg1= new AlertDialog.Builder(car_guide.this);
                dlg1.setTitle("차량 정비 가이드");
                dlg1.setMessage("차량사용 빈도 와 평소 관리 습관에 따라 다르지만 보통 6개월에 한번씩" +
                        "차량을 정비하곤 합니다.");
                dlg1.setPositiveButton("닫기",null);
                dlg1.show();
            }
        });

        matchlayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg1= new AlertDialog.Builder(car_guide.this);
                dlg1.setTitle("엔진오일 가이드");
                dlg1.setMessage("5000km ~ 20000km 로 알려진 엔진오일 교체 주기 엔진오일 의 경우 습관이나 " +
                        "계절에 따라 달라 질수 있습니다.");


                dlg1.setPositiveButton("닫기",null);
                dlg1.show();
            }
        });

        matchlayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg1= new AlertDialog.Builder(car_guide.this);
                dlg1.setTitle("타이어 교체 가이드");
                dlg1.setMessage("보통 40000km~ 50000km 주기로 상태에 따라 교환하곤 하며 어떻게 관리되었는가에" +
                        "따라 다릅니다.");
                dlg1.setPositiveButton("닫기",null);
                dlg1.show();
            }
        });

        matchlayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg1= new AlertDialog.Builder(car_guide.this);
                dlg1.setTitle("PPF필름 교체 가이드");
                dlg1.setMessage(" 'Paint Protection Film' 의 약자인 PPF필름은 차량 스크레치및 흠집으로 부터 보호" +
                        "해주는 역할을 합니다 ");
                dlg1.setPositiveButton("닫기",null);
                dlg1.show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

