package xyz.livedata2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity:xyz";
    private MutableLiveData<Integer> mNumberLiveData;
    private LiveData<Integer> mLiveData;
    private TextView mTvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvNumber = findViewById(R.id.tv_number);
        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        mNumberLiveData = new MutableLiveData<>();
        mNumberLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTvNumber.setText("" + integer);
                Log.d(TAG, "onChanged: " + integer);
            }
        });

    }

    @Override
    public void onClick(View v) {

        new Thread() {
            @Override
            public void run() {
                int number = 0;
                while (number < 5) {
                    try {

                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    number++;
                    mNumberLiveData.postValue(number);
                }
            }
        }.start();
    }
}
