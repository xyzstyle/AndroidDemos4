package xyz.livedata3;


import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLiveData myLiveData = MyLiveData.getInstance(this);
        myLiveData.observe(MainActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.d("xyz", "abc");
                Toast.makeText(MainActivity.this,""+integer,Toast.LENGTH_LONG).show();
            }
        });
        //requestPermission();
    }



}
