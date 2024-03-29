package com.sean.chien.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mMessageEdiText;
    private TextView mReplyHeadTextView;
    private TextView mRelyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageEdiText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mRelyTextView = findViewById(R.id.text_message_reply);
        Log.d(LOG_TAG, "------");
        Log.d(LOG_TAG, "onCreate");
        if (savedInstanceState != null){
            boolean isvisible = savedInstanceState.getBoolean("reply_visible");
            if (isvisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mRelyTextView.setText(savedInstanceState.getString("reply_text"));
                mRelyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestory");
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEdiText.getText().toString();
        intent.putExtra(EXTA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode == TEXT_REQUEST){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mRelyTextView.setText(reply);
                mRelyTextView.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",mRelyTextView.getText().toString());
        }
    }


}
