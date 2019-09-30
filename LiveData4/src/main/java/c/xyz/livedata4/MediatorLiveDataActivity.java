package c.xyz.livedata4;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;

/**
 * This File Created by xyz on 2019/9/29.
 */
public class MediatorLiveDataActivity extends AppCompatActivity {


    private TextView mTextViewA;
    private TextView mTextViewB;
    private FragmentManager mFragmentManager;
    private Button mCtlFragmentBtn;
    private Button mLiveDataABtn;
    private Button mLiveDataBBtn;
    private MutableLiveData<String> liveDataA = new MutableLiveData<>();



    private MutableLiveData<String> liveDataB = new MutableLiveData<>();

    private Observer<String> changeObserverA = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            mTextViewA.setText(s);
        }
    };

    private Observer<String> changeObserverB = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            mTextViewB.setText(s);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediator_live_data);
        this.setTitle("Mediator Live Data");
        mFragmentManager=getSupportFragmentManager();
        mCtlFragmentBtn=findViewById(R.id.btn_control_fragment);
        mLiveDataABtn=findViewById(R.id.btn_livedata_a);
        mLiveDataBBtn=findViewById(R.id.btn_livedata_b);
        mTextViewA = findViewById(R.id.txt_livedata_a);
        mTextViewB = findViewById(R.id.txt_livedata_b);
        if (savedInstanceState != null) {
            setFragmentControlButtonText();
        }
        liveDataA.observe(this,changeObserverA);
        liveDataB.observe(this,changeObserverB);
        mLiveDataABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {

                        }
                        liveDataA.postValue(String.valueOf((int)(Math.random()*10000)));
                    }
                }.start();
            }
        });
        mLiveDataBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {

                        }
                        liveDataB.postValue(String.valueOf((int)(Math.random()*10000)));
                    }
                }.start();
            }
        });
        mCtlFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentManager.getBackStackEntryCount() == 0) {
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container, new MediatorLiveDataFragment())
                            .addToBackStack("").commit();
                } else {
                    mFragmentManager.popBackStack();
                }
            }
        });
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
        return liveDataA;
    }

    public MutableLiveData<String> getLiveDataB() {
        return liveDataB;
    }
}
