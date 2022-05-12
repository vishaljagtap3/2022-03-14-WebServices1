package com.bitcode.webservices1

import com.google.gson.annotations.SerializedName

class UserResponseNew(
    @SerializedName("page")
    var currentPage : Int,

    @SerializedName("per_page")
    var itemsPerPage : Int,

    @SerializedName("pages")
    var totalPages : Int,

    @SerializedName("data")
    var users : ArrayList<UserNew>
)