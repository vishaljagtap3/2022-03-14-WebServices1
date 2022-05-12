package com.bitcode.webservices1

class UserResponse(
    var page : Int,
    var per_page : Int,
    var total_pages : Int,
    var data : ArrayList<UserNew>
)