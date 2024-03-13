package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.nextPage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openActivity2();
            }

        });
    }
    public void openActivity2() {
        //Explicit Intent
        Intent intent = new Intent(this, Activity_2.class);
        startActivity(intent);
    }

    public void printText(View view){
        TextView ans = (TextView) findViewById(R.id.ans);
        String t = ans.getText().toString();
        String s = (String) view.getTag();
        t = t+s;
        ans.setText(t);

    }
    public void calcClear(View view){
        TextView ans = (TextView) findViewById(R.id.ans);
        String t = ans.getText().toString();
        String res = t.substring(0, t.length()-1);
        ans.setText(res);
    }

//    @Override
//    public void onClick(View view){
//        if(view.getId() == R.id.buttoneq){
//                TextView ans = (TextView) findViewById(R.id.ans);
//                String t = ans.getText().toString();
//                Expression expression = new ExpressionBuilder(t).build();
//                double res = expression.evaluate();
//                t = " "+res;
//                ans.setText(t);
//
//
//        }
//    }
    public void returnRes(View view){
        TextView ans = (TextView) findViewById(R.id.ans);
        String t = ans.getText().toString();
        Expression expression = new ExpressionBuilder(t).build();
        double res = expression.evaluate();
        t = " "+res;
        ans.setText(t);
    }

}