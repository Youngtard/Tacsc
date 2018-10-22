package com.youngtard.tacsc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youngtard.tacsc.R;
import com.youngtard.tacsc.ReportDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class BrandsRecyclerAdapter extends RecyclerView.Adapter<BrandsRecyclerAdapter.BrandsViewHolder> {
    private Context context;
    private List<String> brands;

    public BrandsRecyclerAdapter(Context context, ArrayList<String> brands) {
        this.context = context;
        this.brands = brands;

    }


    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_brands_list_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Working", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ReportDetailActivity.class);
                context.startActivity(intent);
            }
        });

        return new BrandsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        holder.bind(brands.get(position));
    }


    @Override
    public int getItemCount() {
        return brands.size();
    }

    class BrandsViewHolder extends RecyclerView.ViewHolder {
        private TextView brandName;

        public BrandsViewHolder(View itemView) {
            super(itemView);

            brandName = itemView.findViewById(R.id.tv_brand_name);



        }

        void bind(String brandName) {
            this.brandName.setText(brandName);
        }

    }
}
