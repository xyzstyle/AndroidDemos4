package c.xyz.livedata4;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransformationMapActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Button mCtlFragmentBtn;



    private MutableLiveData<Integer> mLiveDataA;
    private TextView mLiveDataATv;
    private Button mLiveDataABtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_map);
        setTitle("Transformaton Map");
        mFragmentManager=getSupportFragmentManager();
        mCtlFragmentBtn=findViewById(R.id.btn_control_fragment);
        mLiveDataATv = findViewById(R.id.txt_live_data_a);
        mLiveDataABtn = findViewById(R.id.btn_livedata_a);
        if (savedInstanceState != null) {
            setFragmentControlButtonText();
        }
        mLiveDataA=new MutableLiveData<>();
        mLiveDataA.observe(this,new Observer<Integer>(){
            @Override
            public void onChanged(@Nullable Integer s) {
                mLiveDataATv.setText(s+"");
            }
        });
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
                        mLiveDataA.postValue((int)(Math.random()*10000));
                    }
                }.start();
            }
        });

        mCtlFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentManager.getBackStackEntryCount() == 0) {
                    mFragmentManager.beginTransaction()
                            .replace(R.id.container, new TransformationMapFragment())
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

    public MutableLiveData<Integer> getLiveDataA() {
        return mLiveDataA;
    }

}
