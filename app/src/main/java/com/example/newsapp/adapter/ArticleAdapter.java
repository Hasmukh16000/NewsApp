package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.OnRecyclerViewItemClickListener;
import com.example.newsapp.R;
import com.example.newsapp.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articleArrayList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public ArticleAdapter(List<Article> articleArrayList){
        this.articleArrayList=articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false);
        return new ArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article article=articleArrayList.get(position);
        holder.titleText.setText(article.getTitle());
        holder.descriptionText.setText(article.getDescription());
        holder.linearLayout.setTag(article);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView descriptionText;
        private LinearLayout linearLayout;

        ViewHolder(View view) {
            super(view);
            descriptionText = view.findViewById(R.id.tvdescription);
            titleText = view.findViewById(R.id.tvtitle);
            linearLayout = view.findViewById(R.id.linearlayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener!=null){
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}

