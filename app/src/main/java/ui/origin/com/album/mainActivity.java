package ui.origin.com.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ui.origin.com.libaryalbum.activity.UpLoadSimpleActivity;


public class mainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._layout);
        Intent intent =new Intent(mainActivity.this, UpLoadSimpleActivity.class);
        startActivity(intent);
    }
}
