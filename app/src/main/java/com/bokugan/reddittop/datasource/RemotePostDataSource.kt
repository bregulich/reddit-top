package com.bokugan.reddittop.datasource

import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.redditapi.RedditAPI
import com.bokugan.reddittop.redditapi.RedditAPIProvider
import com.bokugan.reddittop.redditapi.TopJsonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

sealed class FetchResult
class Success(val posts: List<Post>, val after: String?, val before: String?) : FetchResult()
class Error(val errorCode: Type) : FetchResult() {
    enum class Type { PARSE, CONNECTION }
}

interface RemotePostDataSource {
    suspend fun fetchPosts(): FetchResult
}

private class TopRemotePostDataSource(private val redditAPI: RedditAPI) : RemotePostDataSource {

    override suspend fun fetchPosts(): FetchResult {
        lateinit var topJson: TopJsonResponse

        val error: Error? = withContext(Dispatchers.IO) {
            try {
                topJson = redditAPI.top()
                null
            } catch (e: UnknownHostException) {
                Error(Error.Type.CONNECTION)
            } catch (e: Exception) {
                Error(Error.Type.PARSE)
            }
        }

        return error ?: withContext(Dispatchers.Default) {
            try {
                with(topJson.data) {
                    Success(children.map { it.post }, after, before)
                }
            } catch (e: Exception) {
                Error(Error.Type.PARSE)
            }
        }
    }
}

private val TopRemotePostDataSourceInstance by lazy {
    TopRemotePostDataSource(RedditAPIProvider.provide())
}

object RemotePostDataSourceProvider {
    fun provide(): RemotePostDataSource = TopRemotePostDataSourceInstance
}