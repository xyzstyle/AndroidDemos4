package c.xyz.livedata4;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransformationSwitchMapFragment extends Fragment {


    private TextView mFragmentTv;

    public TransformationSwitchMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transformation_switch_map, container, false);

        mFragmentTv = view.findViewById(R.id.txt_fragment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TransformationSwitchMapActivity activity = (TransformationSwitchMapActivity) getActivity();
        if (activity != null) {
            LiveData<String> transformSwitchedLiveData = Transformations.switchMap(activity.getSwitchLiveData(),
                    new Function<Boolean, LiveData<String>>() {
                        @Override
                        public LiveData apply(Boolean input) {
                            MutableLiveData mutableLiveData;
                            if (input) {
                                mutableLiveData = activity.getLiveDataA();
                            } else {
                                mutableLiveData = activity.getLiveDataB();

                            }
                            return mutableLiveData;
                        }
                    });
            transformSwitchedLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    mFragmentTv.setText(s);
                }
            });
        }
    }
}
