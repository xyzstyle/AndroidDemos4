package c.xyz.livedata4;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * This File Created by xyz on 2019/9/28.
 */
public class MutableLiveDataFragment extends Fragment {


    TextView mTextView;
    MutableLiveDataActivity mMutableLiveDataActivity;
    MutableLiveData<String> abc;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMutableLiveDataActivity=((MutableLiveDataActivity)context);
        abc=mMutableLiveDataActivity.getLiveDataA();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_mutable_live_data,container,false);
        mTextView = view.findViewById(R.id.txt_fragment);
        abc.observe(mMutableLiveDataActivity, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mTextView.setText(s);
            }
        });
        return view;
    }
}
