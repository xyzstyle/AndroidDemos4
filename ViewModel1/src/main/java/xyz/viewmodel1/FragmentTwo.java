package xyz.viewmodel1;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xyz on 2019/8/13.
 * Project Name:AndroidDemos4
 */
public class FragmentTwo extends Fragment {
    @BindView(R.id.tv_name)
    TextView mTvName;
    private CommunicateViewModel mCommunicateViewModel;

    public static FragmentTwo getInstance(){
        return new FragmentTwo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommunicateViewModel = ViewModelProviders.of(getActivity()).get(CommunicateViewModel.class);
        mCommunicateViewModel.getName().observe(this, name -> mTvName.setText(name));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}

