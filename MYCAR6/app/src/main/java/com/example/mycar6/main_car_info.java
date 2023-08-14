package com.example.mycar6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class main_car_info extends AppCompatActivity {

    TextView carName_main;
    TextView maintenance_date_info, engineoil_date_info,
            changingPPF_date_info, changingtire_date_info;

    Button goto_input_info;
    //하단바 버튼 선언
    ImageButton homeButton;
    ImageButton userButton;
    ImageButton mapButton;
    ImageButton guideButton;

    private DatabaseReference mDatabas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_car_info);

        carName_main=findViewById(R.id.carName_main);

        maintenance_date_info = findViewById(R.id.maintenance_date_info);
        engineoil_date_info = findViewById(R.id.engineoil_date_info);
        changingPPF_date_info = findViewById(R.id.changingPPF_date_info);
        changingtire_date_info = findViewById(R.id.changingtire_date_info);

        goto_input_info=findViewById(R.id.goto_input_info);

        //재명 하단바 버튼
        homeButton=findViewById(R.id.homeButton);
        userButton=findViewById(R.id.userButton);
        mapButton=findViewById(R.id.mapButton);
        guideButton=findViewById(R.id.guideButton);

        mDatabas = FirebaseDatabase.getInstance().getReference();

        readUser();

        goto_input_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), input_info.class);
                startActivity(intent);
            }
        });



        //재명
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_car_info.this, Carinfo.class);
                startActivity(intent);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_car_info.this, carmap.class);
                startActivity(intent);
            }
        });

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_car_info.this, car_guide.class);
                startActivity(intent);
            }
        });



        //
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), main_car_info.class);
                startActivity(intent);
            }
        });


    }

    private void readUser() {
        mDatabas.child("VehicleInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //DB데이터를 불러와 형식 메소드로 체크한뒤 setText
                try {
                    carName_main.setText(user.inputCarNum);

                    maintenance_date_info.setText(conformMonth(user.maintenanceDateMmonth , user.maintenanceDateDay,6) + "월"
                            + conformDay(user.maintenanceDateDay) + '일');

                    engineoil_date_info.setText(conformMonth(user.engineoilDateMmonth , user.engineoilDateDay,6) + "월"
                            + conformDay(user.engineoilDateDay) + '일');


                    changingPPF_date_info.setText(conformMonth(user.changingPPFDateMmonth, user.changingPPFDateDay,3) + "월"
                            + conformDay(user.changingPPFDateDay) + '일');
                    changingtire_date_info.setText(conformMonth(user.changingtireDateMmonth, user.changingtireDateDay,3) + "월"
                            + conformDay(user.changingtireDateDay) + '일');
                }catch(Exception e){
                    ;
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "데이터를 가져오는데 실패했습니다", Toast.LENGTH_LONG).show();
            }


        });
    }
    // 날짜 형식 메소드
    public String conformMonth(String month, String day,int period) {
        int cmp1 = Integer.parseInt(month);
        int cmp2 = Integer.parseInt(day);
        cmp1+=period;

        if (cmp2 >= 31) cmp1 += 1;
        if (cmp1 > 12) cmp1 -= 12;

        return Integer.toString(cmp1);
    }

    public String conformDay(String day) {
        int cmp = Integer.parseInt(day);
        if (cmp >= 31) cmp -= 31;
        return Integer.toString(cmp);
    }


    public static class User {
        public String inputCarNum;

        public String maintenanceDateMmonth;
        public String maintenanceDateDay;

        public String engineoilDateMmonth;
        public String engineoilDateDay;

        public String changingtireDateMmonth;
        public String changingtireDateDay;

        public String changingPPFDateMmonth;
        public String changingPPFDateDay;
        public User(){ }


        public User(String inputCarNum,
                    String maintenance_date_month, String maintenance_date_day,
                    String engineoil_date_month, String engineoil_date_day,
                    String changingtire_date_month, String changingtire_date_day,
                    String changingPPF_date_month, String changingPPF_date_day) {
            this.inputCarNum=inputCarNum;

            this.maintenanceDateMmonth = maintenance_date_month;
            this.maintenanceDateDay = maintenance_date_day;

            this.engineoilDateMmonth = engineoil_date_month;
            this.engineoilDateDay = engineoil_date_day;

            this.changingtireDateMmonth = changingtire_date_month;
            this.changingtireDateDay = changingtire_date_day;

            this.changingPPFDateMmonth = changingPPF_date_month;
            this.changingPPFDateDay = changingPPF_date_day;
        }
    }
}
