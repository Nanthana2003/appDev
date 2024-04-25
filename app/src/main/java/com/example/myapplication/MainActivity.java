package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    OkHttpClient okHttpClient;
    Request request;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.nextPage);
        okHttpClient = new OkHttpClient();
        sharedPref = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        // building a request

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

    public void openActivity3() {
        //Explicit Intent
        Intent intent = new Intent(this, Activity_3.class);
        startActivity(intent);
    }

    public void loginFunc(View view){
        openActivity2();

//        EditText uname = (EditText)findViewById(R.id.name);
//        EditText upass = (EditText)findViewById(R.id.password);
//        String username = uname.getText().toString();
//        String password = upass.getText().toString();
//        String uri = "http://10.0.2.2:5000/login";
//        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
//        JSONObject postData = new JSONObject();
//        try {
//            postData.put("name", username);
//            postData.put("passwd", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, postData.toString());
//
//        Request request = new Request.Builder()
//                .url(uri)
//                .post(body)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            // called if server is unreachable
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.w("server", e.getMessage());
//                        Toast.makeText(MainActivity.this, "server down", Toast.LENGTH_SHORT).show();
////                        pagenameTextView.setText("error connecting to the server");
//                    }
//                });
//            }
//
//            @Override
//            // called if we get a
//            // response from the server
//            public void onResponse(
//                    @NotNull Call call,
//                    @NotNull Response response)
//                    throws IOException {
//                if (response.isSuccessful()) {
//                    String responseData = response.body().string();
//                    // Do something with the response data
//                    Log.w("Response", responseData);
//                    editor.putString("name", username);
//                    editor.apply();
//                    String myname = sharedPref.getString("name", "default");
//                    Log.w("shared preference", myname);
//                    openActivity3();
//
//                } else {
//                    // Handle unsuccessful response
//                    Log.w("Response", "Unsuccessful: " + response);
//                }
//            }
//        });

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