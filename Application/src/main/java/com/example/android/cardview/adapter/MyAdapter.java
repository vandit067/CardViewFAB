package com.example.android.cardview.adapter;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cardview.CardViewActivity;
import com.example.android.cardview.MainActivity;
import com.example.android.cardview.R;
import com.example.android.cardview.circularfam.MenuWithFABActivity;
import com.example.android.cardview.jbsm.ArticleDetailFragment;

/**
 * Created by PatelV1 on 7/10/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        private View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            mTextView = (TextView) v.findViewById(R.id.info_text);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    ((CardViewActivity)view.getContext()).getFragmentManager().beginTransaction()
////                            .add(R.id.container, ArticleDetailFragment.newInstance())
////                            .commit();
//                    view.getContext().startActivity(new Intent(view.getContext(), ArticleDetailActivity.class));
//                }
//            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    ((CardViewActivity)view.getContext()).getFragmentManager().beginTransaction()
//                            .add(R.id.container, ArticleDetailFragment.newInstance())
//                            .commit();
                if (position == 0) {
                    view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
                } else if (position == 1) {
                    view.getContext().startActivity(new Intent(view.getContext(), MenuWithFABActivity.class));
                } else {
                    FragmentTransaction fragmentTransaction = ((CardViewActivity) view.getContext()).getFragmentManager().beginTransaction();
                    ArticleDetailFragment articleDetailFragment = ArticleDetailFragment.newInstance();
                    fragmentTransaction.replace(R.id.container, articleDetailFragment);
                    fragmentTransaction.addToBackStack(articleDetailFragment.getClass().getSimpleName());
                    fragmentTransaction.commit();
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
