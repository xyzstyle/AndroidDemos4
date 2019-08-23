package xyz.viewmodel1;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xyz on 2019/8/13.
 * Project Name:AndroidDemos4
 */
public class FragmentOne extends Fragment {

    private CommunicateViewModel mCommunicateViewModel;

    public static FragmentOne getInstance(){
        return new FragmentOne();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommunicateViewModel = ViewModelProviders.of(getActivity()).get(CommunicateViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_set_name)
    void onViewClicked(View v){
        switch (v.getId()){
            case R.id.btn_set_name:
                mCommunicateViewModel.setName("Jane");
                break;
        }
    }

}
