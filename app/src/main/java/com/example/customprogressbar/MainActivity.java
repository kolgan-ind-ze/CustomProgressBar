package com.example.customprogressbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ProgressBar standartPB, customPB;
    TextView showStandart, showCustom;
    Drawable drawableCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawableCustom = ContextCompat.getDrawable(this, R.drawable.custom_pb); // установка ресурса для заполненого участка прогресс бара
        showStandart = findViewById(R.id.showProgressStandartPB);
        standartPB = findViewById(R.id.progressBarStandart);
        standartPB.setMax(100); // установка максимального значения прогресс бара
        standartPB.setProgress(0);  // установка начального прогресса
        showCustom = findViewById(R.id.showProgressCustomPB);
        customPB = findViewById(R.id.progressBarCustom);
        customPB.setProgressDrawable(drawableCustom); // установка ресурса для прогресс бара
        customPB.setMax(100); // установка максимального значения прогресс бара
        customPB.setProgress(0); // установка начального прогресса

        setCustomPB();
        setStandartPB();
    }

    public void setCustomPB(){
        Thread custom = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 110; i+=10){
                    int countCustom = i;
                    customPB.post(()-> customPB.setProgress(countCustom));
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        custom.start();
    }

    public void setStandartPB(){
        Thread standart = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i+=10){
                    int countStandart = i;
                    standartPB.post(()-> standartPB.setProgress(countStandart));
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        standart.start();
    }
}