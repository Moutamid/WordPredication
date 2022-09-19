package com.moutamid.wordpredication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {


    AppCompatButton btnRecognize, btnClear;
    DrawView drawView;
    private TextView homeTxt,keywordTxt;
    TextView textView1,textView2,textView3,textView4,textView5;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = getIntent().getIntExtra("number",0);
        btnRecognize = findViewById(R.id.buttonRecognize);
        btnClear = findViewById(R.id.buttonClear);
        drawView = findViewById(R.id.draw_view);
        textView1 = findViewById(R.id.one);
        textView2 = findViewById(R.id.two);
        textView3 = findViewById(R.id.three);
        textView4 = findViewById(R.id.four);
        textView5 = findViewById(R.id.five);
        keywordTxt = findViewById(R.id.keyword);
        homeTxt = findViewById(R.id.home);

        homeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SplashScreen.class));
            }
        });
        if (i < 100) {
            getKeyword();
        }else {
            startActivity(new Intent(MainActivity.this,SplashScreen.class));
            finish();
        }
        StrokeManager.download();
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textView1.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                    intent.putExtra("letter", textView1.getText().toString());
                    intent.putExtra("number", i);
                    startActivity(intent);
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textView2.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                    intent.putExtra("letter", textView2.getText().toString());
                    intent.putExtra("number", i);
                    startActivity(intent);
                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textView3.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                    intent.putExtra("letter", textView3.getText().toString());
                    intent.putExtra("number", i);
                    startActivity(intent);
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textView4.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                    intent.putExtra("letter", textView4.getText().toString());
                    intent.putExtra("number", i);
                    startActivity(intent);
                }
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textView5.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                    intent.putExtra("letter", textView5.getText().toString());
                    intent.putExtra("number", i);
                    startActivity(intent);
                }
            }
        });

        btnRecognize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrokeManager.recognize(MainActivity.this, textView1,textView2,textView3,textView4,textView5);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.clear();
                StrokeManager.clear();
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
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

    private void hideTitleBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        hideTitleBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideTitleBar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideTitleBar();
    }

}