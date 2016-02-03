package com.example.nano1.washingtonpost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Article> mArrayOfArticles;
    ListView mListView;
    NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        FetchArticles downloader = new FetchArticles(this);
        downloader.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.sortTitle:
                Collections.sort(mArrayOfArticles, new Comparator<Article>() {
                    @Override
                    public int compare(Article lhs, Article rhs) {
                        return lhs.articleTitle.compareTo(rhs.articleTitle);
                    }
                });
                break;
            case R.id.sortDate:
                Collections.sort(mArrayOfArticles, new Comparator<Article>() {
                    @Override
                    public int compare(Article lhs, Article rhs) {
                        return lhs.articleDate.compareTo(rhs.articleDate);
                    }
                });
                break;
        }
        mAdapter.notifyDataSetChanged();
        mListView.invalidateViews();
        mListView.refreshDrawableState();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, NewsArticle.class);
        intent.putExtra("article", mArrayOfArticles.get(position));
        startActivity(intent);
    }

    public void loadNews(ArrayList<Article> articles){
        this.mArrayOfArticles = articles;
        mAdapter = new NewsAdapter(this, articles);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }
}
