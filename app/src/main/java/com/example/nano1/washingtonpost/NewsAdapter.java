package com.example.nano1.washingtonpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by nano1 on 1/29/2016.
 */
public class NewsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Article> mArrayOfArticles;
    NewsAdapter(Context context, ArrayList<Article> articles){
        this.mContext = context;
        this.mArrayOfArticles = articles;
    }

    class ViewHolder{
        TextView title;
        TextView date;

        ViewHolder(View row) {
            title = (TextView) row.findViewById(R.id.rowTitle);
            date = (TextView) row.findViewById(R.id.rowDate);
        }
    }


    @Override
    public int getCount() {
        return mArrayOfArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayOfArticles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_article_row, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Article mArticle = mArrayOfArticles.get(position);
        holder.title.setText(mArticle.articleTitle);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a",Locale.US);
        String datetime = simpleFormat.format(mArticle.articleDate);
        holder.date.setText(datetime);
        return row;
    }
}
