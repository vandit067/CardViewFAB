package com.example.android.cardview.circularfam;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.cardview.R;

public class MenuWithFABActivity extends AppCompatActivity {
    private WebView wvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_fab);
        wvArticle = (WebView) findViewById(R.id.activity_menu_with_fab_wv_article);

        initWebViews();
        wvArticle.loadUrl("file:///android_asset/S2213858714701342/main.html");

        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);

        // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconAttachment = new ImageView(this);
        fabIconAttachment.setImageDrawable(getResources().getDrawable(R.drawable.ic_attachment));

//        CircularFloatingActionButton.LayoutParams attachmentParams = new CircularFloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
//        attachmentParams.setMargins(redActionButtonMargin,
//                redActionButtonMargin,
//                redActionButtonMargin,
//                redActionButtonMargin);
//        fabIconAttachment.setLayoutParams(attachmentParams);

//        CircularFloatingActionButton.LayoutParams attachmentIconParams = new CircularFloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
//        attachmentIconParams.setMargins(redActionButtonContentMargin,
//                redActionButtonContentMargin,
//                redActionButtonContentMargin,
//                redActionButtonContentMargin);

        final CircularFloatingActionButton rightLowerButton = new CircularFloatingActionButton.Builder(this)
//                .setContentView(fabIconAttachment, attachmentIconParams)
                .setContentView(fabIconAttachment)
                .setBackgroundDrawable(R.drawable.button_action_red_selector)
                .setPosition(CircularFloatingActionButton.POSITION_BOTTOM_RIGHT)
//                .setLayoutParams(attachmentParams)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        rLSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_action_blue_selector));
        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin);
        rLSubBuilder.setLayoutParams(blueContentParams);

        // Set custom layout params
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        rLSubBuilder.setLayoutParams(blueParams);
        ImageView rlIcon1 = new ImageView(this);

        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        ImageView rlIcon5 = new ImageView(this);
        ImageView rlIcon6 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_headphones));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_picture));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        rlIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat));
        rlIcon6.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_location_found));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final CircularFloatingActionMenu rightLowerMenu = new CircularFloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon5, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon6, blueContentParams).build())
                .setRadius(redActionMenuRadius)
                .setStartAngle(160)
                .setEndAngle(290)
                .attachTo(rightLowerButton)
                .build();

//        .addSubActionView(lCSubBuilder.setContentView(lcIcon1, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon2, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon3, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon4, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon5, blueContentParams).build())
//                .setRadius(redActionMenuRadius)
//                .setStartAngle(70)
//                .setEndAngle(-70)
        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new CircularFloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(CircularFloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
//                fabIconAttachment.setRotation(0);
                fabIconAttachment.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cancel));
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconAttachment, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(CircularFloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
//                fabIconAttachment.setRotation(45);
                fabIconAttachment.setImageDrawable(getResources().getDrawable(R.drawable.ic_attachment));
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconAttachment, pvhR);
                animation.start();
            }
        });

        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuWithFABActivity.this, "Audio Clicked", Toast.LENGTH_SHORT).show();
            }
        });
/*
        // Set up the large red button on the center right side
        // With custom button and content sizes and margins
        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);
            
        ImageView fabIconStar = new ImageView(this);
        fabIconStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_important));

        CircularFloatingActionButton.LayoutParams starParams = new CircularFloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
        starParams.setMargins(redActionButtonMargin,
                              redActionButtonMargin,
                              redActionButtonMargin,
                              redActionButtonMargin);
        fabIconStar.setLayoutParams(starParams);

        CircularFloatingActionButton.LayoutParams fabIconStarParams = new CircularFloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
        fabIconStarParams.setMargins(redActionButtonContentMargin,
                                    redActionButtonContentMargin,
                                    redActionButtonContentMargin,
                                    redActionButtonContentMargin);

        final CircularFloatingActionButton leftCenterButton = new CircularFloatingActionButton.Builder(this)
                                                .setContentView(fabIconStar, fabIconStarParams)
                                                .setBackgroundDrawable(R.drawable.button_action_red_selector)
                                                .setPosition(CircularFloatingActionButton.POSITION_LEFT_CENTER)
                                                .setLayoutParams(starParams)
                                                .build();

        // Set up customized SubActionButtons for the right center menu
        SubActionButton.Builder lCSubBuilder = new SubActionButton.Builder(this);
        lCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_action_blue_selector));

        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin,
                          blueSubActionButtonContentMargin);
        lCSubBuilder.setLayoutParams(blueContentParams);
        // Set custom layout params
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        lCSubBuilder.setLayoutParams(blueParams);

        ImageView lcIcon1 = new ImageView(this);
        ImageView lcIcon2 = new ImageView(this);
        ImageView lcIcon3 = new ImageView(this);
        ImageView lcIcon4 = new ImageView(this);
        ImageView lcIcon5 = new ImageView(this);

        lcIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        lcIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_picture));
        lcIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        lcIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_location_found));
        lcIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_headphones));

        // Build another menu with custom options
        final CircularFloatingActionMenu leftCenterMenu = new CircularFloatingActionMenu.Builder(this)
                .addSubActionView(lCSubBuilder.setContentView(lcIcon1, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon2, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon3, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon4, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon5, blueContentParams).build())
                .setRadius(redActionMenuRadius)
                .setStartAngle(70)
                .setEndAngle(-70)
                .attachTo(leftCenterButton)
                .build();*/


    }

    /**
     * Initialize webview components
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViews() {
        this.wvArticle.setWebViewClient(new MyContentWebViewClient());
        this.wvArticle.getSettings().setAllowContentAccess(true);
        this.wvArticle.getSettings().setJavaScriptEnabled(true);
        this.wvArticle.getSettings().setLoadsImagesAutomatically(true);
        this.wvArticle.getSettings().setLoadWithOverviewMode(true);
        this.wvArticle.getSettings().setUseWideViewPort(true);
        this.wvArticle.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * Class to handle webview events
     */
    private class MyContentWebViewClient extends WebViewClient {


        public MyContentWebViewClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            super.onPageFinished(view, url);
        }

    }
}
