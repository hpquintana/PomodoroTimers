package io.github.hpquintana.pomodorotimer

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebViewActivity : AppCompatActivity() {
    private lateinit var showOrHideWebViewInitialUse: String
    private lateinit var webview: WebView
    private lateinit var spinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showOrHideWebViewInitialUse = "show"
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webView)
        spinner = findViewById(R.id.spinner)
        webview.webViewClient = CustomWebViewClient()

//        webview.setWebViewClient(WebViewClient())
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.overScrollMode = WebView.OVER_SCROLL_NEVER
        webview.loadUrl("https://demoprojects-7a844.firebaseapp.com/")
    }

    private inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(webview: WebView, url: String, favicon: Bitmap?) {
            if (showOrHideWebViewInitialUse.equals("show")) {
                webview.visibility = View.INVISIBLE
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            showOrHideWebViewInitialUse = "hide"
            spinner.visibility = View.GONE

            view.visibility = View.VISIBLE
            super.onPageFinished(view, url)

        }
    }
}
