package mter.mter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.CookieStore;
import mter.network.HttpClient;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    EditText id ;
    EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = (Button)findViewById(R.id.btn_login);
        login_btn.setOnClickListener(this);
        AsyncHttpClient client = HttpClient.getInstance();

        // 쿠키 영구 저장
        CookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);

        //쿠키값 제거
        //client.setCookieStore(null);
    }

    @Override
    public void onClick(View v) {

        RequestParams params = new RequestParams();
        EditText id = (EditText)findViewById(R.id.id);
        EditText pwd = (EditText)findViewById(R.id.pwd);

        params.put("check", 0);
        params.put("id", id.getText().toString());
        params.put("pwd", pwd.getText().toString());
        Log.i("Msg","Clicked Login Btn id : "+id.getText()+" pwd : "+pwd.getText());
        HttpClient.get("", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                TextView status = (TextView) findViewById(R.id.textView);
                String result = new String(bytes,0,bytes.length);


                if(!result.equals("[]")) {
                    status.setText(result);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    setResultExtra(intent, result);
                    startActivity(intent);
                }
                else
                {

                    faildLogin();

                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }


            public void setResultExtra(Intent intent,String result)
            {
                try {

                    JSONArray jsonArray = new JSONArray(result);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject item = jsonArray.getJSONObject(i);
                        intent.putExtra("id",item.getString("id"));
                        intent.putExtra("name",item.getString("name"));
                        intent.putExtra("class",item.getString("mil_class"));
                        intent.putExtra("number",item.getString("army_number").toString());
                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

        });



    }



    public void faildLogin()
    {
        Toast.makeText(this,"올바르지 않은 정보입니다.",Toast.LENGTH_SHORT).show();
    }



   
}
