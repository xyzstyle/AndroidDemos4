package c.xyz.livedata4;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
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
public class TransformationMapFragment extends Fragment {


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

        if (getActivity() != null) {

            LiveData<String> transformedLiveData= Transformations.map(((TransformationMapActivity) getActivity()).getLiveDataA(), new Function<Integer,String>() {
                @Override
                public String apply(Integer input) {
                    String s=String.valueOf(input);
                    return s;
                }
            });
            transformedLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {

                    mFragmentTv.setText(s);
                }
            });
        }
    }

}
