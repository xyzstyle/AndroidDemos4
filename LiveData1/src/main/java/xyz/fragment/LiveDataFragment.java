package xyz.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.livedata1.R;
import xyz.model.NameViewModel;

/**
 * Created by xyz on 2019/8/13.
 * Project Name:AndroidDemos4
 */
public class LiveDataFragment extends Fragment {

    private static final String TAG = "LiveDataFragment";
    private NameViewModel mNameViewModel;
    @BindView(R.id.tv_name)
    TextView mTvName;

    public static LiveDataFragment getInstance() {
        return new LiveDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
//        mNameViewModel.getCurrentName().observe(this, (String name) -> {
//            mTvName.setText(name);
//            Log.d(TAG, "currentName: " + name);
//        }); // 订阅LiveData中当前Name数据变化，以lambda形式定义Observer
        mNameViewModel.getNameList().observe(this, (List<String> nameList) -> {
            for (String item : nameList) {
                Log.d(TAG, "name: " + item);
            }
        });
        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mTvName.setText(s);
                Log.d(TAG, "currentName: " + s);
            }
        });

        // 订阅LiveData中Name列表数据变化，以lambda形式定义Observer
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livedata, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_change_name, R.id.btn_update_list})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_name:
                mNameViewModel.getCurrentName().setValue("Jane");
                break;
            case R.id.btn_update_list:
                List<String> nameList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    nameList.add("Jane<" + i + ">");
                }
                mNameViewModel.getNameList().setValue(nameList);
                break;
        }
    }


}
