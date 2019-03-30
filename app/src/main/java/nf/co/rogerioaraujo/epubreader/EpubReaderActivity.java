package nf.co.rogerioaraujo.epubreader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class EpubReaderActivity extends Activity {
    private WebView web;
    private ProgressDialog dialog;
    private String book_title;
    private String url;

    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get title name from user input
        book_title = getIntent().getStringExtra("title");
        url = "http://lirb.000webhostapp.com/lirb/reader/bib/i/?book=" + book_title;

        Log.d("BOOK_TITLE", book_title);
        Log.d("BOOK_URL", url);

        //Hide title bar - this should be done before SetContentView
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_epub_reader);

        // web view settings
        web = findViewById(R.id.webview);
        WebSettings webSettings = web.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        web.setWebViewClient(new WebViewClient() {
            // This method will be triggered when the Page Started Loading
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(EpubReaderActivity.this, null,
                        "Please Wait... Your book is Loading...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(EpubReaderActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                web.loadUrl("http://arunimmanuel.blogspot.in");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        web.loadUrl(url);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
    }
}