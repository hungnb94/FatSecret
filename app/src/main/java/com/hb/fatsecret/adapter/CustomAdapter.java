package com.hb.fatsecret.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hb.fatsecret.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final String TAG = CustomAdapter.class.getSimpleName();

    String[] arrays;
    Context context;

    int selected = -1;

    public CustomAdapter(Context context, String[] arrays) {
        this.context = context;
        this.arrays = arrays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(arrays[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = position;
                notifyDataSetChanged();
            }
        });
        if (selected == position) {
            holder.layout.setBackgroundColor(ActivityCompat.getColor(context, R.color.colorGray));
            holder.textView.setTextColor(ActivityCompat.getColor(context, R.color.colorPrimaryDark));
        } else {
            holder.layout.setBackgroundColor(ActivityCompat.getColor(context, R.color.colorWhite));
            holder.textView.setTextColor(ActivityCompat.getColor(context, R.color.colorBlack));
        }
    }

    @Override
    public int getItemCount() {
        return arrays.length;
    }

    public int getSelected() {
        return selected;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layout;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linearLayout);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selected = getAdapterPosition();
        }
    }
}
