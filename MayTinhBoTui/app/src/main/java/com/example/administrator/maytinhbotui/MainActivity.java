package com.example.administrator.maytinhbotui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity" ;
    private EditText edtInput;
    private TextView txtResuil;
    private Button Number1, Number2, Number3, Number0, Number4, Number5, Number6, Number7, Number8, Number9,btnXoa,btnCong, btnTru, btnChia, btnNhan, btnCham,btnBang, btnRestar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initEvent();
    }

    private void initEvent() {

        Number0.setOnClickListener(this);
        Number1.setOnClickListener(this);
        Number2.setOnClickListener(this);
        Number3.setOnClickListener(this);
        Number4.setOnClickListener(this);
        Number5.setOnClickListener(this);
        Number6.setOnClickListener(this);
        Number7.setOnClickListener(this);
        Number8.setOnClickListener(this);
        Number9.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btnCham.setOnClickListener(this);
        btnBang.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    private void initview() {
        edtInput = findViewById(R.id.edt_input);
        txtResuil = findViewById(R.id.tv_inputResuil);

        Number1 = findViewById(R.id.btn_1);
        Number0 = findViewById(R.id.btn_0);
        Number2 = findViewById(R.id.btn_2);
        Number3 = findViewById(R.id.btn_3);
        Number4 = findViewById(R.id.btn_4);
        Number5 = findViewById(R.id.btn_5);
        Number6 = findViewById(R.id.btn_6);
        Number7 = findViewById(R.id.btn_7);
        Number8 = findViewById(R.id.btn_8);
        Number9 = findViewById(R.id.btn_9);

        btnCong = findViewById(R.id.btn_cong);
        btnTru = findViewById(R.id.btn_tru);
        btnChia = findViewById(R.id.btn_chia);
        btnNhan = findViewById(R.id.btn_nhan);
        btnCham = findViewById(R.id.btn_cham);
        btnRestar = findViewById(R.id.btn_resar);
        btnBang = findViewById(R.id.btn_bang);
        btnXoa = findViewById(R.id.btn_c);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_0:
                edtInput.append("0");
                break;

            case R.id.btn_1:
                edtInput.append("1");
                break;

            case R.id.btn_2:
                edtInput.append("2");
                break;
            case R.id.btn_3:
                edtInput.append("3");
                break;
            case R.id.btn_4:
                edtInput.append("4");
                break;
            case R.id.btn_5:
                edtInput.append("5");
                break;
            case R.id.btn_6:
                edtInput.append("6");
                break;
            case R.id.btn_7:
                edtInput.append("7");
                break;
            case R.id.btn_8:
                edtInput.append("8");
                break;
            case R.id.btn_9:
                edtInput.append("9");
                break;
            case R.id.btn_cong:
                String textCong = btnCong.getText().toString();
                //Log.d(TAG, "onClick: " + textCong);

                edtInput.append(textCong);
                break;
            case R.id.btn_nhan:
                edtInput.append("x");
                Toast.makeText(this, "Nhan ", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_chia:
                Toast.makeText(this, "chia ", Toast.LENGTH_LONG).show();
                edtInput.append("/");
                break;
            case R.id.btn_tru:
                Toast.makeText(this, "Tru ", Toast.LENGTH_LONG).show();
                edtInput.append("-");
                break;
            case R.id.btn_cham:
                edtInput.append(".");
                break;

            case R.id.btn_c:
                BaseInputConnection baseText = new BaseInputConnection(edtInput, true);
                baseText.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
//               String dele= delete(edtInput.getText().toString());
//               edtInput.setText(dele);
//
                break;

            case R.id.btn_resar:
                edtInput.setText("");
                txtResuil.setText("");
                break;

            case R.id.btn_bang:
                DecimalFormat fo = new DecimalFormat("###.#######"); // làm tròn kết quả
                double resuil = 0;
                addOperation(edtInput.getText().toString());
                addNumber(edtInput.getText().toString());
                if (arropreration.size() >= arrnumber.size() || arropreration.size() < 1){
                    txtResuil.setText("Loi dinh dang");
                    resuil = 0;
                }
                for (int i =0; i < arrnumber.size()-1; i++){
                    switch (arropreration.get(i)){
                        case "+":
                            if (i ==0){
                                resuil = arrnumber.get(i) + arrnumber.get(i+1);
                            }else {
                                resuil = resuil + arrnumber.get(i + 1);
                            }
                            break;
                        case "-":
                            if (i ==0){
                                resuil = arrnumber.get(i) - arrnumber.get(i+1);
                            }else {
                                resuil = resuil - arrnumber.get(i + 1);
                            }
                            break;

                        case "x":
                            if (i ==0){
                                resuil = arrnumber.get(i) * arrnumber.get(i+1);
                            }else {
                                resuil = resuil * arrnumber.get(i + 1);
                            }
                            break;

                        case "/":
                            if (i ==0){
                                resuil = arrnumber.get(i) / arrnumber.get(i+1);
                            }else {
                                resuil = resuil / arrnumber.get(i + 1);
                            }
                            break;

                         default:
                             break;

                    }

                }
                txtResuil.setText(fo.format(resuil + ""));

            default:
                break;

        }
    }

//    public String delete(String number){
//        int length = number.length();
//        String temp = number.substring(0, length-1);
//        return temp;
//    }

    private ArrayList<String> arropreration; // arr lưu dấu
    private ArrayList<Double> arrnumber; // arr lưu số

    public int addOperation (String input){
        arropreration = new ArrayList<>();
        char[] Array = input.toCharArray();
        for (int i = 0; i < Array.length ; i++){
            switch (Array[i]){
                case '+':
                    arropreration.add(Array[i] + " ");
                    break;
                case '-':
                    arropreration.add(Array[i] + " ");
                    break;
                case 'x':
                    arropreration.add(Array[i] + " ");
                    break;
                case '/':
                    arropreration.add(Array[i] + " ");
                    break;

                 default:
                     break;

            }
        }
        return  0;
    }

    public void addNumber(String stringInput){
        arrnumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\d +\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()){
            arrnumber.add(Double.valueOf(matcher.group(1)));
        }

    }

}
