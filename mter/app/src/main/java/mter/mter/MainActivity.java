package mter.mter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import mter.QRCode.QRCodeGenerater;
import mter.network.HttpClient;

public class MainActivity extends AppCompatActivity {
    boolean isEntered;
    private AsyncHttpClient client = new AsyncHttpClient();
    private Bitmap profileBitmap , QRCodeBitmap;
    private String ImageName;

//    profileImg; 이미지프로필
//    name;       이름
//    number;     군번
//    category;   구분

    ImageView profileImg;
    TextView name;
    TextView number;
    TextView category;


    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        profileImg =(ImageView)findViewById(R.id.profile_img);
        name = (TextView)findViewById(R.id.name);
        number = (TextView)findViewById(R.id.number);
        category = (TextView)findViewById(R.id.category);

        name.setText(getIntent().getExtras().getString("name"));
        number.setText(getIntent().getExtras().getString("number"));
        ImageName = getIntent().getExtras().getString("id");
        ImageView QRCode = (ImageView)findViewById(R.id.qr_scan_btn);
        ImageDownload();

        QRCodeBitmap = QRCodeGenerater.generateQRCode(number.getText().toString());
        QRCode.setImageBitmap(QRCodeBitmap);

        QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isEntered) {
                    isEntered = true;
                    Toast.makeText(getApplicationContext(), "입영하셨습니다", Toast.LENGTH_SHORT ).show();
                    //db에 입영 기록 남기기
                    //blocking service 실행 - boot receiver 호출(blocking service 계속실행)
                }else{
                    isEntered = false;
                    Toast.makeText(getApplicationContext(), "퇴영하셨습니다", Toast.LENGTH_SHORT).show();
                    //db에 퇴영 기록 남기기
                    //blocking service 종료 - boot receiver 종료
                }



            }
        });




        ImageButton inoutRecordBtn = (ImageButton)findViewById(R.id.inout_record_btn);
        ImageButton settingsBtn    = (ImageButton)findViewById(R.id.settings_btn);
        inoutRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InoutRecordActivity.class);
                startActivity(i);
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
            }
        });


    }


    public void ImageDownload()
    {

        String URL =  HttpClient.getAbsoluteUrl(ImageName+".jpg");

        Log.i("이미지",URL);
        client.get(URL, new AsyncHttpResponseHandler()
        {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                profileBitmap = byteArrayToBitmap(responseBody);
                profileImg.setImageBitmap(profileBitmap);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }



    public Bitmap byteArrayToBitmap( byte[] byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }

}

