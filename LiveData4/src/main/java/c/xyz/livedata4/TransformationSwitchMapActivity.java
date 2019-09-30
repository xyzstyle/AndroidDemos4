package c.xyz.livedata4;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TransformationSwitchMapActivity extends AppCompatActivity {

    private TextView mTextViewA;
    private TextView mTextViewB;
    private FragmentManager mFragmentManager;
    private Button mCtlFragmentBtn;
    private Button mLiveDataABtn;
    private Button mLiveDataBBtn;
    private MutableLiveData<String> mLiveDataA = new MutableLiveData<>();
    private MutableLiveData<String> mLiveDataB = new MutableLiveData<>();



    private MutableLiveData<Boolean> mSwitchLiveData=new MutableLiveData<>();



    private SwitchCompat mSwitchCompat;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_switch_map);
        this.setTitle("Transformation  Switch Map");
        mFragmentManager=getSupportFragmentManager();
        mCtlFragmentBtn=findViewById(R.id.btn_control_fragment);
        mLiveDataABtn=findViewById(R.id.btn_livedata_a);
        mLiveDataBBtn=findViewById(R.id.btn_livedata_b);
        mTextViewA = findViewById(R.id.txt_livedata_a);
        mTextViewB = findViewById(R.id.txt_livedata_b);
        mSwitchCompat = findViewById(R.id.switch_live_data);
        if (savedInstanceState != null) {
            setFragmentControlButtonText();
        }
        mLiveDataA.observe(this,changeObserverA);
        mLiveDataB.observe(this,changeObserverB);
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
                        mLiveDataA.postValue(String.valueOf((int)(Math.random()*10000)));
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
                        mLiveDataB.postValue(String.valueOf((int)(Math.random()*10000)));
                    }
                }.start();
            }
        });
        mCtlFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentManager.getBackStackEntryCount() == 0) {
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container, new TransformationSwitchMapFragment())
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
        mSwitchLiveData.setValue(mSwitchCompat.isChecked());
        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSwitchLiveData.setValue(isChecked);
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

    public MutableLiveData<String> getLiveDataB() {
        return mLiveDataB;
    }

    public MutableLiveData<Boolean> getSwitchLiveData() {
        return mSwitchLiveData;
    }
}
