package com.example.nano1.washingtonpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewsArticle extends AppCompatActivity {

    TextView txtTitle;
    TextView txtDate;
    TextView txtContent;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDate = (TextView) findViewById(R.id.txtDate);
        //txtContent = (TextView) findViewById(R.id.txtContent);
        webView = (WebView) findViewById(R.id.webView);


        Article selectedArticle = getIntent().getParcelableExtra("article");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy KK:mm a", Locale.US);
        String dateTime = dateFormat.format(selectedArticle.articleDate);

        txtTitle.setText(selectedArticle.articleTitle);
        txtDate.setText(dateTime);
        //txtContent.setText(Html.fromHtml(selectedArticle.articleContent), TextView.BufferType.SPANNABLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", selectedArticle.articleContent, "text/html", "UTF-8", "");
    }
}
