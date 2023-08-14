package com.example.mycar6;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class input_info extends AppCompatActivity {


    ImageButton toPreviousPage;
    TextView pageTitle,carNum;
    EditText inputCarNum, inputCarModelName;
    EditText input_maintenance_date_month,input_maintenance_date_day
            ,input_engineoil_date_month,input_engineoil_date_day
            ,input_changingtire_date_month,input_changingtire_date_day
            ,input_changingPPF_date_month,input_changingPPF_date_day;
    Spinner carBrand,carModel;
    Button input_info_button;

    String[] items_br;
    ArrayAdapter myAdapter;

    AlertDialog.Builder dialog;

    private DatabaseReference mDatabse;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_info);

        toPreviousPage = findViewById(R.id.toPreviousPage);
        pageTitle = findViewById(R.id.pageTitle);
        carNum = findViewById(R.id.carNum);
        inputCarNum = findViewById(R.id.inputCarNum);
        inputCarModelName = findViewById(R.id.inputCarModelName);
        carBrand = findViewById(R.id.carBrand);
        carModel = findViewById(R.id.carModel);
        input_info_button = findViewById(R.id.input_info_button);


        //스피너
        addAddressSpinner();
        items_br = getResources().getStringArray(R.array.brandName);
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_br);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carBrand.setAdapter(myAdapter);


        //재명 뒤로가기버튼
        ImageButton toPreviousPage = findViewById(R.id.toPreviousPage);
        toPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //교체날짜 객체화
        input_maintenance_date_month=findViewById(R.id.input_maintenance_date_month);
        input_maintenance_date_day=findViewById(R.id.input_maintenance_date_day);
        input_engineoil_date_month=findViewById(R.id.input_engineoil_date_month);
        input_engineoil_date_day=findViewById(R.id.input_engineoil_date_day);
        input_changingtire_date_month=findViewById(R.id.input_changingtire_date_month);
        input_changingtire_date_day=findViewById(R.id.input_changingtire_date_day);
        input_changingPPF_date_month=findViewById(R.id.input_changingPPF_date_month);
        input_changingPPF_date_day=findViewById(R.id.input_changingPPF_date_day);

        //데이터베이스 인스턴스
        mDatabse=FirebaseDatabase.getInstance().getReference();



        input_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_CarNum_data = inputCarNum.getText().toString();
                String input_CarModelName_data = inputCarModelName.getText().toString();
                String input_carBrand_data = carBrand.getSelectedItem().toString();
                String input_carModel_data = carModel.getSelectedItem().toString();

                String maintenance_date_month=input_maintenance_date_month.getText().toString();
                String maintenance_date_day=input_maintenance_date_day.getText().toString();

                String engineoil_date_month=input_engineoil_date_month.getText().toString();
                String engineoil_date_day=input_engineoil_date_day.getText().toString();

                String changingtire_date_month=input_changingtire_date_month.getText().toString();
                String changingtire_date_day=input_changingtire_date_day.getText().toString();

                String changingPPF_date_month=input_changingPPF_date_month.getText().toString();
                String changingPPF_date_day=input_changingPPF_date_day.getText().toString();



                if (input_CarNum_data.equals("") || input_CarModelName_data.equals("") ||
                        input_carBrand_data.equals("") || input_carModel_data.equals("")||

                        maintenance_date_month.equals("") || maintenance_date_day.equals("")||
                        engineoil_date_month.equals("") || engineoil_date_day.equals("")||
                        changingtire_date_month.equals("") || changingtire_date_day.equals("")||
                        changingPPF_date_month.equals("") || changingPPF_date_day.equals("")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(input_info.this);
                    dialog = builder.setMessage("모든 정보를 입력해 주세요.");
                    dialog.setNegativeButton("확인", null);
                    dialog.create();
                    dialog.show();
                    return;

                }

                HashMap result = new HashMap<>();
                result.put("inputCarNum",input_CarNum_data);
                result.put("inputCarModelName",input_CarModelName_data);
                result.put("carBrand",input_carBrand_data);
                result.put("carModel",input_carModel_data);

                result.put("maintenance_date_month",maintenance_date_month);
                result.put("maintenance_date_day",maintenance_date_day);

                result.put("engineoil_date_month",engineoil_date_month);
                result.put("engineoil_date_day",engineoil_date_day);

                result.put("changingtire_date_month",changingtire_date_month);
                result.put("changingtire_date_day",changingtire_date_day);

                result.put("changingPPF_date_month",changingPPF_date_month);
                result.put("changingPPF_date_day",changingPPF_date_day);


                writeNewUser(input_CarNum_data,input_CarModelName_data ,
                        input_carBrand_data ,input_carModel_data,
                        maintenance_date_month, maintenance_date_day,
                        engineoil_date_month, engineoil_date_day,
                        changingtire_date_month, changingtire_date_day,
                        changingPPF_date_month, changingPPF_date_day);

                finish();

            }
        });



    }

    public void writeNewUser(String input_CarNum_data,String input_CarModelName_data ,
                             String input_carBrand_data ,String input_carModel_data,
                             String maintenance_date_month, String maintenance_date_day,
                             String engineoil_date_month, String engineoil_date_day,
                             String changingtire_date_month, String changingtire_date_day,
                             String changingPPF_date_month, String changingPPF_date_day)
    {

        User user = new User( input_CarNum_data, input_CarModelName_data ,
                            input_carBrand_data , input_carModel_data,
                maintenance_date_month, maintenance_date_day,
                engineoil_date_month, engineoil_date_day,
                changingtire_date_month, changingtire_date_day,
                changingPPF_date_month, changingPPF_date_day

            );

        mDatabse.child("VehicleInformation").setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(input_info.this, "저장 완료",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(input_info.this, "저장 실패",Toast.LENGTH_SHORT).show();

                    }
                });



    };
    public static class User{

        public String  inputCarNum;
        public String inputCarModelName;
        public String carBrand;
        public String carModel;


        public String maintenanceDateMmonth;
        public String maintenanceDateDay;

        public String engineoilDateMmonth;
        public String engineoilDateDay;

        public String changingtireDateMmonth;
        public String changingtireDateDay;

        public String changingPPFDateMmonth;
        public String changingPPFDateDay;

        public User(String input_CarNum_data,String input_CarModelName_data ,
                    String input_carBrand_data ,String input_carModel_data,

                    String maintenance_date_month, String maintenance_date_day,
                    String engineoil_date_month, String engineoil_date_day,
                    String changingtire_date_month, String changingtire_date_day,
                    String changingPPF_date_month, String changingPPF_date_day){
            this.inputCarNum=input_CarNum_data;
            this.inputCarModelName=input_CarModelName_data;
            this.carBrand=input_carBrand_data;
            this.carModel=input_carModel_data;

            this.maintenanceDateMmonth=maintenance_date_month;
            this.maintenanceDateDay=maintenance_date_day;

            this.engineoilDateMmonth=engineoil_date_month;
            this.engineoilDateDay=engineoil_date_day;

            this.changingtireDateMmonth=changingtire_date_month;
            this.changingtireDateDay=changingtire_date_day;

            this.changingPPFDateMmonth=changingPPF_date_month;
            this.changingPPFDateDay=changingPPF_date_day;

        }
        /*
        public String getInputCarNum(){
            return inputCarNum;
        }

        public void setInputCarNum(){
            this.inputCarNum= inputCarNum;
        }

        public String getInputCarModelName(){
            return inputCarModelName;
        }

        public void setInputCarModelName(){
            this.inputCarModelName= inputCarModelName;
        }

        public String getCarBrand(){
            return carBrand;
        }

        public void setCarBrand(){
            this.carBrand= carBrand;
        }

        public String getCarModel(){
            return carModel;
        }

        public void setCarModel(){
            this.carModel= carModel;
        }


        public String toString() {
            return "User{" +
                    "inputCarNum='" + inputCarNum + '\'' +
                    ", inputCarModelName='" + inputCarModelName + '\'' +
                    ", carBrand='" + carBrand + '\'' +
                    ", carModel='" + carModel + '\'' +
                    '}';
        }*/
    }

    //스피너 설정
    public void addAddressSpinner(){
        carBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 0:
                        carModel.setAdapter(null);
                            break;

                    case 1:
                        setCarModelAdapterItem(R.array.hyundaiModel);
                        break;

                    case 2:
                        setCarModelAdapterItem(R.array.KiaModel);
                        break;

                    case 3:
                        setCarModelAdapterItem(R.array.toyotaModel);
                        break;
                    case 4:
                        setCarModelAdapterItem(R.array.volkswagenModel);
                        break;
                    case 5:
                        setCarModelAdapterItem(R.array.BMWModel);
                        break;



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


    private void setCarModelAdapterItem(int array_resource){
        if(myAdapter!=null){
            carModel.setAdapter(null);
            myAdapter=null;
        }

        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item
                , (String[])getResources().getStringArray(array_resource));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModel.setAdapter(myAdapter);
    }





}
