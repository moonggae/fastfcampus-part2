package fastcampus.aop.part2.chapter08

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import fastcampus.aop.part2.chapter08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }


    private fun initViews() {
        binding.webView.apply {
            webViewClient = CustomWebViewClient()
            webChromeClient = CustomWebChromeClient()
            settings.javaScriptEnabled = true
            loadUrl(DEFAULT_URL)
        }
    }

    private fun bindViews() {
        binding.goHomeButton.setOnClickListener {
            binding.webView.loadUrl(DEFAULT_URL)
        }

        binding.addressBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val loadingUrl = v.text.toString()
                if(URLUtil.isNetworkUrl(loadingUrl)){
                    binding.webView.loadUrl(loadingUrl)
                } else {
                    binding.webView.loadUrl("http://$loadingUrl")
                }
            }
            return@setOnEditorActionListener false
        }

        binding.goBackButton.setOnClickListener {
            binding.webView.goBack()
        }

        binding.goForwardButton.setOnClickListener {
            binding.webView.goForward()
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.webView.reload()
        }
    }

    inner class CustomWebViewClient : android.webkit.WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.refreshLayout.isRefreshing = false
            binding.progressBar.hide()

            binding.goBackButton.isEnabled = binding.webView.canGoBack()
            binding.goForwardButton.isEnabled = binding.webView.canGoForward()
            binding.addressBar.setText(url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBar.show()
        }

    }

    inner class CustomWebChromeClient : android.webkit.WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.progressBar.progress = newProgress
        }
    }

    companion object {
        private const val DEFAULT_URL = "https://www.google.com"
    }

}