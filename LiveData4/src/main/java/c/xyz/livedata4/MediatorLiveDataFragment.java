package c.xyz.livedata4;


import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediatorLiveDataFragment extends Fragment {



    private TextView mFragmentTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mediator_live_data, container, false);
        mFragmentTv = view.findViewById(R.id.txt_fragment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MediatorLiveData<String> mediatorLiveData = new MediatorLiveData();
        if (getActivity() != null) {
            mediatorLiveData.addSource(((MediatorLiveDataActivity) getActivity()).getLiveDataA(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    mediatorLiveData.setValue("A:"+s);
                }
            });

            mediatorLiveData.addSource(((MediatorLiveDataActivity) getActivity()).getLiveDataB(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    mediatorLiveData.setValue("B:"+s);
                }
            });
            mediatorLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {

                    mFragmentTv.setText(s);
                }
            });
        }
    }
}
