package io.github.hpquintana.pomodorotimer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar;
import android.graphics.Bitmap
import android.view.View


class MainActivity : AppCompatActivity() {
    private var ShowOrHideWebViewInitialUse = "show"
    private lateinit var webview: WebView
    private lateinit var spinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webView)
        spinner = findViewById(R.id.spinner)
        webview.webViewClient = CustomWebViewClient()

//        webview.setWebViewClient(WebViewClient())
        webview.getSettings().setJavaScriptEnabled(true)
        webview.getSettings().setDomStorageEnabled(true)
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER)
        webview.loadUrl("https://demoprojects-7a844.firebaseapp.com/")
    }

    private inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(webview: WebView, url: String, favicon: Bitmap?) {
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.visibility = View.INVISIBLE
            }
        }

        override fun onPageFinished(view: WebView, url: String) {

            ShowOrHideWebViewInitialUse = "hide";
            spinner.visibility = View.GONE

            view.visibility = View.VISIBLE
            super.onPageFinished(view, url)

        }
    }
}
