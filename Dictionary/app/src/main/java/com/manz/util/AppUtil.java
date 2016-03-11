package com.manz.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.manz.db.DaoMaster;
import com.manz.db.DaoSession;
import com.manz.db.Words;
import com.manz.db.WordsDao;
import com.manz.dictionary.AppController;
import com.manz.dictionary.HomeActivity;
import com.manz.model.WordList;
import com.manz.model.WordModel;

import org.json.JSONObject;

import java.util.List;

public class AppUtil {

    private static final String TAG = "AppUtil";
    private static AppUtil mInstance;

    public static synchronized AppUtil getInstance() {
        if (mInstance == null) {
            mInstance = new AppUtil();
        }
        return mInstance;
    }

    public List<Words> getWords(final Context ctx) {
        List<Words> words = getWordsFromDb(ctx);
        if(words != null && words.size() > 0){
            return words;
        }
        final WordList[] wordList = new WordList[1];
        String url = "http://appsculture.com/vocab/words.json";

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG, response.toString());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                wordList[0] = gson.fromJson(response.toString(), WordList.class);
                                loadWordsIntoDb(ctx, wordList[0].getWords());
                                final List<Words> words = getWordsFromDb(ctx);
                                //((HomeActivity)ctx).getAdapter().setData((wordList[0] != null) ? wordList[0].getWords() : null);
                                ((HomeActivity) ctx).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((HomeActivity) ctx).getAdapter().setData(words);
                                        pDialog.hide();
                                    }
                                });
                            }
                        }).start();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

        // return (wordList[0] != null) ? wordList[0].getWords() : null;
        return null;
    }

    public List<Words> getWordsFromDb(Context ctx) {
        DaoSession daoSession = AppController.getInstance().getDaoSession(ctx);
        WordsDao dicDao = daoSession.getWordsDao();
        List<Words> dicList = dicDao.loadAll();

        return dicList;
    }

    public void loadWordsIntoDb(Context ctx, List<WordModel> words) {
        if (words != null) {
            DaoSession daoSession = AppController.getInstance().getDaoSession(ctx);
            WordsDao dicDao = daoSession.getWordsDao();
            for (WordModel w : words) {
                dicDao.insertOrReplace(w.cloneDao());
            }
        }
    }
}
