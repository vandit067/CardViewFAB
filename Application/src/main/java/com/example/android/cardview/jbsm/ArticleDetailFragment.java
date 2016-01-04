package com.example.android.cardview.jbsm;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.cardview.GridCardViewFragment;
import com.example.android.cardview.R;
import com.example.android.cardview.fab.FloatingActionButton;
import com.example.android.cardview.fab.FloatingActionMenu;

/**
 * Created by PatelV1 on 12/15/2015.
 */
public class ArticleDetailFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fabImage;
    private FloatingActionButton fabAudio;
    private FloatingActionButton fabVideo;
    private FloatingActionButton fabFigure;
    private FloatingActionButton fabTable;
    private FloatingActionButton fabOtherFiles;
    private FloatingActionMenu famAttachments;
    private WebView wvArticle;
    private Handler mUiHandler = new Handler();
    private final int FAB_MENU_DISPLAY_TIME_DELAY = 400;
    private GestureDetector mGestureDetector;
    private View.OnTouchListener mGestureListener;
    private boolean mIsScrolling = false;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArticleDetailFragment.
     */
    public static ArticleDetailFragment newInstance() {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public ArticleDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        this.famAttachments = (FloatingActionMenu) view.findViewById(R.id.fragment_article_detail_fam_attachments);
        wvArticle = (WebView) view.findViewById(R.id.fragment_article_detail_wv_article);

        this.fabImage = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_image);
        this.fabAudio = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_audio);
        this.fabFigure = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_figure);
        this.fabOtherFiles = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_other_files);
        this.fabTable = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_table);
        this.fabVideo = (FloatingActionButton) view.findViewById(R.id.fragment_article_detail_fab_attachment_video);

        this.fabImage.setOnClickListener(this);
        this.fabAudio.setOnClickListener(this);
        this.fabFigure.setOnClickListener(this);
        this.fabOtherFiles.setOnClickListener(this);
        this.fabTable.setOnClickListener(this);
        this.fabVideo.setOnClickListener(this);
        this.famAttachments.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famAttachments.toggle(true);
                famAttachments.getMenuIconView().setImageResource(famAttachments.isOpened()
                        ? R.drawable.ic_attachment : R.drawable.ic_close);

            }
        });
        this.famAttachments.hideMenuButton(false);
        this.famAttachments.setClosedOnTouchOutside(true);
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                famAttachments.showMenuButton(true);
            }
        }, FAB_MENU_DISPLAY_TIME_DELAY);
        this.famAttachments.setIconAnimated(false);
//        initGestureDetection();
        initWebViews();
        wvArticle.loadUrl("file:///android_asset/S2213858714701342/main.html");
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
        this.wvArticle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_SCROLL:
                    case MotionEvent.ACTION_MOVE:
                        if (null != famAttachments && famAttachments.isShown()) {
                            famAttachments.hideMenuButton(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (null != famAttachments && famAttachments.isShown()) {
                            famAttachments.showMenuButton(false);
                        }
                        break;

                }
//                if(null != famAttachments) {
//                    famAttachments.getMenuIconView().setImageResource(famAttachments.isOpened()
//                            ? R.drawable.ic_close : R.drawable.ic_attachment);
//                }
                return false;
            }
        });
//
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_article_detail_fab_attachment_image:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_article_detail_fab_attachment_table:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_article_detail_fab_attachment_audio:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_article_detail_fab_attachment_figure:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_article_detail_fab_attachment_other_files:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_article_detail_fab_attachment_video:
                startNewFragment();
                Toast.makeText(getActivity(), ((FloatingActionButton) view).getLabelText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startNewFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        GridCardViewFragment gridCardViewFragment = GridCardViewFragment.newInstance();
        fragmentTransaction.replace(R.id.container, gridCardViewFragment);
        fragmentTransaction.addToBackStack(ArticleDetailFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    private void handleScrollFinished() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != famAttachments) {
                    famAttachments.showMenuButton(false);
                    famAttachments.getMenuIconView().setImageResource(famAttachments.isOpened()
                            ? R.drawable.ic_close : R.drawable.ic_attachment);
                }
            }
        });

    }

    private void handleScrollStart() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != famAttachments) {
                    famAttachments.hideMenuButton(false);
                }
            }
        });

    }

    private void handleSingleTap() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != famAttachments && famAttachments.isOpened()) {
                    famAttachments.toggle(true);
                    famAttachments.showMenuButton(false);
                    famAttachments.getMenuIconView().setImageResource(famAttachments.isOpened()
                            ? R.drawable.ic_close : R.drawable.ic_attachment);
                }
            }
        });
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

    public void initGestureDetection() {
        // Gesture detection
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
//                handleDoubleTap(e);
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                handleSingleTap();
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // i'm only scrolling along the X axis
                mIsScrolling = true;
//                Toast.makeText(getActivity(),"On Scroll called..", Toast.LENGTH_SHORT).show();
//                handleScroll(Math.round((e2.getX() - e1.getX())));
                handleScrollStart();
                return true;
            }

            @Override
            /**
             * Don't know why but we need to intercept this guy and return true so that the other gestures are handled.
             * https://code.google.com/p/android/issues/detail?id=8233
             */
            public boolean onDown(MotionEvent e) {
//                Log.d("GestureDetector --> onDown");
                return true;
            }
        });

        mGestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mIsScrolling) {
//                        Log.d("OnTouchListener --> onTouch ACTION_UP");
                        mIsScrolling = false;
//                        Toast.makeText(getActivity(),"On Finish called..", Toast.LENGTH_SHORT).show();
                        handleScrollFinished();
                    }
                }

                return false;
            }
        };

        wvArticle.setOnTouchListener(mGestureListener);
    }

}
