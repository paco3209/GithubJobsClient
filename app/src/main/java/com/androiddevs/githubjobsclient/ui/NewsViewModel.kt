package com.androiddevs.githubjobsclient.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.githubjobsclient.models.JobsItem
import com.androiddevs.githubjobsclient.models.JobsResponse
import com.androiddevs.githubjobsclient.repository.NewsRepository
import com.androiddevs.githubjobsclient.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<JobsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val gitHubJob: MutableLiveData<Resource<JobsItem>> = MutableLiveData()

    val searchNews: MutableLiveData<Resource<JobsResponse>> = MutableLiveData()
    var searchNewsPage = 1

        init {
            getBreakingNews()
        }

    fun getBreakingNews() = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews()
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    fun getGithubJob(id: String) = viewModelScope.launch {
        gitHubJob.postValue(Resource.Loading())
        val response = newsRepository.getJob(id)
        gitHubJob.postValue(handleGitHubJobResponse(response))
    }

    private fun handleGitHubJobResponse(response: Response<JobsItem>) : Resource<JobsItem>{
        if(response.isSuccessful){
            response.body()?.let {
                return  Resource.Success(it)
            }
        }
        return  Resource.Error(response.message())
    }

    private fun handleBreakingNewsResponse(response: Response<JobsResponse>) : Resource<JobsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<JobsResponse>) : Resource<JobsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveJob(job: JobsItem) = viewModelScope.launch {
        newsRepository.upsert(job)
    }

    fun getSavedJobs() = newsRepository.getSaveJobs()

    fun deleteJob(job: JobsItem) = viewModelScope.launch {
        newsRepository.deleteArticle(job)
    }


}












