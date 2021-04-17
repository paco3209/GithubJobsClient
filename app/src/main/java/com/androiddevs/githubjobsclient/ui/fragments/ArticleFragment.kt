package com.androiddevs.githubjobsclient.ui.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.androiddevs.githubjobsclient.R
import com.androiddevs.githubjobsclient.ui.NewsActivity
import com.androiddevs.githubjobsclient.ui.NewsViewModel
import com.androiddevs.githubjobsclient.util.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article




        viewModel.getGithubJob(article.id)

        viewModel.gitHubJob.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {

                    it.data?.let {job ->

                        txtTitle.text =  HtmlCompat.fromHtml(job.title,HtmlCompat.FROM_HTML_MODE_COMPACT)
                        txtDescription.text = HtmlCompat.fromHtml(job.description,HtmlCompat.FROM_HTML_MODE_COMPACT)
                        txtLocation.text = job.location
                        txtPostDate.text = job.created_at

                       btnApply.setOnClickListener {
                            val i = Intent(Intent.ACTION_VIEW, Uri.parse(job.url))
                            startActivity(i)
                        }

                        btnFav.setOnClickListener{
                            viewModel.saveJob(job)
                            Snackbar.make(view, "Job saved successfully", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                is Resource.Error -> {

                    it.message?.let {
                        Log.e(TAG, "An error occured: $it")
                    }
                }
                is Resource.Loading -> {

                }
            }
        })




        /* webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        } */
    }
}