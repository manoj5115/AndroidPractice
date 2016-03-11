package com.manz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.NetworkImageView;
import com.manz.dictionary.AppController;
import com.manz.dictionary.R;
import com.manz.db.Words;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.CustomViewHolder> {
    private List<Words> wordtemList;
    private Context mContext;

    public WordsAdapter(Context context, List<Words> wordtemList) {
        this.wordtemList = wordtemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    public void setData(List<Words> wordtemList){
        this.wordtemList = wordtemList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Words wordItem = wordtemList.get(i);

        //Download image using picasso library
       /* Picasso.with(mContext).load(wordItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(customViewHolder.imageView);
        */
        //Setting text view title
        customViewHolder.title.setText(Html.fromHtml(wordItem.getWord()));
        customViewHolder.meaning.setText(Html.fromHtml(wordItem.getMeaning()));
        customViewHolder.imageView.setDefaultImageResId(R.drawable.placeholder);
        customViewHolder.imageView.setImageUrl(wordItem.getUrl(), AppController.getInstance().getImageLoader());


        //Handle click event on both title and image click
        customViewHolder.title.setOnClickListener(clickListener);
        customViewHolder.meaning.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);

        customViewHolder.title.setTag(customViewHolder);
        customViewHolder.meaning.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);

    }

    @Override
    public int getItemCount() {
        return (null != wordtemList ? wordtemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected NetworkImageView imageView;
        protected TextView title;
        protected TextView meaning;

        public CustomViewHolder(View view) {
            super(view);

            this.imageView = (NetworkImageView) view.findViewById(R.id.thumbnail);
            this.title = (TextView) view.findViewById(R.id.title);
            this.meaning = (TextView) view.findViewById(R.id.meaning);

            ViewGroup.LayoutParams lp = this.title.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            view.setLayoutParams(lp);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getPosition();

            Words wordItem = wordtemList.get(position);
            Toast.makeText(mContext, wordItem.getWord(), Toast.LENGTH_SHORT).show();
        }
    };
}
