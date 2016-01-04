package com.example.android.cardview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cardview.R;

/**
 * Created by PatelV1 on 12/21/2015.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    public GridAdapter() {
        super();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_gridview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if(i%2 == 0){
            viewHolder.txtImageName.setText("Text2");
            viewHolder.imgThumbnail.setImageResource(R.drawable.image2);
        } else {
            viewHolder.txtImageName.setText("Text1");
            viewHolder.imgThumbnail.setImageResource(R.drawable.image1);
        }

    }

    @Override
    public int getItemCount() {

        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView txtImageName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.row_gridview_img_thumbnail);
            txtImageName = (TextView)itemView.findViewById(R.id.row_gridview_tv_image_name);
        }
    }
}
