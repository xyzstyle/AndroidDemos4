package c.xyz.livedata4;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;

public class MutableLiveDataActivity extends AppCompatActivity {

    private MutableLiveData<String> mLiveDataA;
    private FragmentManager mFragmentManager;
    private Button mLiveDataABtn;
    private Button mCtlFragmentBtn;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable_live_data);
        setTitle("Mutable Live Data");
        if (savedInstanceState != null) {
            setFragmentControlButtonText();
        }
        mFragmentManager=getSupportFragmentManager();
        mLiveDataABtn = findViewById(R.id.btn_livedata_a);
        mCtlFragmentBtn = findViewById(R.id.btn_control_fragment);
        mTextView=findViewById(R.id.txt_live_data_a);

        mLiveDataA=new MutableLiveData<>();
        mLiveDataA.observe(MutableLiveDataActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mTextView.setText(s);
            }
        });
        mLiveDataABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {

                        for(int i=0;i<100;i++) {
                            try {
                                Thread.sleep(1000);

                            } catch (InterruptedException e) {

                            }
                            mLiveDataA.postValue(String.valueOf((int) (Math.random() * 1000)));
                        }
                    }
                }.start();
            }
        });
        mCtlFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentManager.getBackStackEntryCount() == 0) {
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container, new MutableLiveDataFragment())
                            .addToBackStack("").commit();
                } else {
                    mFragmentManager.popBackStack();
                }
            }
        }) ;

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setFragmentControlButtonText();
            }
        });

    }

    private void setFragmentControlButtonText() {
        if (mFragmentManager.getBackStackEntryCount() == 0) {
            mCtlFragmentBtn.setText( "Add Fragment");
        } else {
            mCtlFragmentBtn.setText("Remove Fragment");
        }
    }
    public MutableLiveData<String> getLiveDataA() {
        return mLiveDataA;
    }
}

