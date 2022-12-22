package com.example.app_ecommerce.adapter;
import android.widget.ImageView;
import com.example.app_ecommerce.model.Products;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ecommerce.R;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductVeiwHolder> {
    Context context;
    List<Products>productsList;

    public productAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(com.example.app_ecommerce.R.layout.products_row_item,parent,false);
        return new ProductVeiwHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVeiwHolder holder, int position) {
        holder.prodImage.setImageResource(productsList.get(position).getImageUrl());
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodPrice.setText(productsList.get(position).getPrice().toString());
        holder.prodQty.setText(productsList.get(position).getStorage());
        holder.productId.setText(productsList.get(position).getProductId().toString());
        holder.title.setText(productsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public  static final class ProductVeiwHolder extends  RecyclerView.ViewHolder{
        ImageView prodImage;
        TextView prodName,prodQty,prodPrice,productId,title;
        public ProductVeiwHolder(@NonNull View itemView) {
            super(itemView);
            prodImage=itemView.findViewById(R.id.product_image);
            prodName=itemView.findViewById(R.id.Name);
            prodPrice=itemView.findViewById(R.id.product_price);
            prodQty=itemView.findViewById(R.id.product_qty);
            productId=itemView.findViewById(R.id.product_id);
            title=itemView.findViewById(R.id.product_name);
        }
    }
}
