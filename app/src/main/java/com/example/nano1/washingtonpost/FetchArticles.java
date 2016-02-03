package com.example.nano1.washingtonpost;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nano1 on 1/29/2016.
 */
public class FetchArticles extends AsyncTask<Void, Void, Void> {

    ArrayList<Article> mArrayOfArticles;
    MainActivity mContext;
    private ProgressDialog pDialog;
    private final String TAG_POSTS = "posts";
    private final String TAG_TITLE = "title";
    private final String TAG_DATE = "date";
    private final String TAG_CONTENT = "content";
    private final String ARTICLES_URL = "http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json";


    FetchArticles(MainActivity context){
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mArrayOfArticles = new ArrayList<>();
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Loading ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL mUrl = new URL(ARTICLES_URL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(mUrl.openConnection().getInputStream(), "UTF-8"));
            String json;
            StringBuilder builder = new StringBuilder();
            while ((json = reader.readLine()) != null) {
                builder.append(json);
            }
            JSONObject newsObject = new JSONObject(builder.toString());
            JSONArray newsArray = newsObject.getJSONArray(TAG_POSTS);

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject mPostObject = newsArray.getJSONObject(i);
                String mTitle = mPostObject.getString(TAG_TITLE);
                String mRawDate = mPostObject.getString(TAG_DATE);
                String mContent = mPostObject.getString(TAG_CONTENT);

                SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                Date mDate = mDateFormat.parse(mRawDate);
                Article mArticle = new Article(mTitle, mContent, mDate);
                mArrayOfArticles.add(mArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pDialog.dismiss();
        mContext.loadNews(mArrayOfArticles);
    }
}
