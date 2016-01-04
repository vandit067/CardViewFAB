package com.example.android.cardview.jbsm;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.cardview.R;

/**
 * Created by PatelV1 on 12/15/2015.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_article_detail);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            ArticleDetailFragment articleDetailFragment = ArticleDetailFragment.newInstance();
            fragmentTransaction.replace(R.id.activity_article_detail_container, articleDetailFragment);
            fragmentTransaction.addToBackStack(articleDetailFragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }
    }
}
