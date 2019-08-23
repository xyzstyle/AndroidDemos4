package xyz.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import xyz.fragment.ListFragment;
import xyz.pojo.DataItem;
import xyz.room2.R;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> {

    private List<DataItem> mDataItemList;
    private final ListFragment.OnListFragmentInteractionListener mListener;

    public ListRecyclerViewAdapter(ListFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void setListData(List<DataItem> dataItemList) {
        //setup new list
        if (mDataItemList == null) {
            mDataItemList = new ArrayList<>();
        }
        mDataItemList.clear();
        mDataItemList.addAll(dataItemList);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItem = mDataItemList.get(position);
        holder.mNumberView.setText(String.valueOf(position + 1));
        holder.mNameView.setText(mDataItemList.get(position).getName());
        holder.mSectionView.setText(mDataItemList.get(position).getSection());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListClickItem(holder.mItem);
                }
            }
        });
        final long itemId = holder.mItem.getId();
        //OnClick delete the item from list
        holder.mImageDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentDeleteItemById(itemId);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mDataItemList != null) {
            return mDataItemList.size();
        } else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mNumberView;
        private final TextView mNameView;
        private final TextView mSectionView;
        private final ImageView mImageDeleteView;

        private DataItem mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNumberView = view.findViewById(R.id.item_number);
            mNameView = view.findViewById(R.id.item_name);
            mSectionView = view.findViewById(R.id.item_section);
            mImageDeleteView = view.findViewById(R.id.image_delete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
