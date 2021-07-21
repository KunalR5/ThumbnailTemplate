package com.shinchannohara.thumbnail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView first = (ImageView)findViewById(R.id.first);
        ImageView second = (ImageView)findViewById(R.id.second);
        ImageView third = (ImageView)findViewById(R.id.third);
        ImageView fourth = (ImageView)findViewById(R.id.fourth);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(start.this,MainActivity.class));
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(start.this,Main2Activity.class));
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(start.this,Main3Activity.class));
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(start.this,Main4Activity.class));
            }
        });

    }
}
