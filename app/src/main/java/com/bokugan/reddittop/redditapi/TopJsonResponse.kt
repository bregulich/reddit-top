package com.bokugan.reddittop.redditapi

import com.bokugan.reddittop.dataobject.Post
import com.google.gson.annotations.SerializedName

// TODO. Not the cleanest solution.
class TopJsonResponse {
    @SerializedName("data")
    lateinit var data: Data
        private set

    class Data {
        class Child {
            @SerializedName("data")
            lateinit var post: Post
                private set
        }

        @SerializedName("children")
        lateinit var children: List<Child>
            private set

        @SerializedName("before")
        var before: String? = null
            private set

        @SerializedName("after")
        var after: String? = null
            private set
    }
}