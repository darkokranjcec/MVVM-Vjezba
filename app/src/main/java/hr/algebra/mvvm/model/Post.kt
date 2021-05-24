package hr.algebra.mvvm.model

import com.squareup.moshi.Json

data class Post(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "userId")
    val userId: Int?,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "description")
    val body: String,

    // demo for nested objects
    // @field:Json(name = "user")
    // val user: User
)