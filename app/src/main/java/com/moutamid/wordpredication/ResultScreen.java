package com.moutamid.wordpredication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ResultScreen extends AppCompatActivity {

    AppCompatButton retryBtn,nextBtn;
    private TextView resultTxt,selectedValue,correctValue;
    private TextView homeTxt;
    private LinearLayout correctLayout,incorrectLayout;
    private String letter ="";
    private ArrayList<String> list;
    private int id;
    int interval = 1;
    private TextView keywordTxt;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        retryBtn = findViewById(R.id.retry);
        nextBtn = findViewById(R.id.next);
        resultTxt = findViewById(R.id.result);
        selectedValue = findViewById(R.id.textView);
        correctValue = findViewById(R.id.textView1);
        keywordTxt = findViewById(R.id.keyword);
        homeTxt = findViewById(R.id.home);
        correctLayout = findViewById(R.id.correct_layout);
        incorrectLayout = findViewById(R.id.incorrect_layout);
        letter = getIntent().getStringExtra("letter");
        i = getIntent().getIntExtra("number",0);
        getKeyword();
        readLetter(letter);
        selectedValue.setText("You selected: " + letter);
        homeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultScreen.this,SplashScreen.class));
            }
        });
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ResultScreen.this,MainActivity.class);
                intent.putExtra("number",i);
                startActivity(intent);

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ResultScreen.this,MainActivity.class);
                intent.putExtra("number",i+1);
                startActivity(intent);

            }
        });
     }

    private void getKeyword() {
        try {
            AssetManager assetManager = getAssets();
            InputStream myInput = assetManager.open("words.xls");
            Workbook wb = Workbook.getWorkbook(myInput);
            Sheet sheet = wb.getSheet(0);
            Cell symbol = sheet.getCell(1, i);
            keywordTxt.setText(symbol.getContents());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (BiffException e) {
            e.printStackTrace();
        }
    }

     String text = "";
    private void readLetter(String letter) {
        try {
            AssetManager assetManager = getAssets();
            InputStream myInput = assetManager.open("words.xls");
            Workbook wb = Workbook.getWorkbook(myInput);

            //    if (wb != null) {
            // used to store data

            // get the first sheet
            Sheet sheet = wb.getSheet(0);
            Cell symbol = sheet.getCell(2, i);
            text = symbol.getContents();

            if (text.equals(letter)){
                //Cell word = sheet.getCell(1, i);
                resultTxt.setText("CORRECT");
                correctLayout.setVisibility(View.VISIBLE);
                incorrectLayout.setVisibility(View.GONE);
                correctValue.setText("Correct Value: " + text);
            }
            else {
                resultTxt.setText("INCORRECT");
                correctLayout.setVisibility(View.GONE);
                incorrectLayout.setVisibility(View.VISIBLE);
                correctValue.setText("Correct Value: " + text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (BiffException e) {
            e.printStackTrace();
        }
    }
}