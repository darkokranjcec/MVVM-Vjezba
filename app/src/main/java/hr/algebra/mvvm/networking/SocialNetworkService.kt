package hr.algebra.mvvm.networking

import hr.algebra.mvvm.model.Post
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SocialNetworkService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("Books")
    suspend fun getBooks(): List<Post>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun getPostsByUser(@Query("userId") userId: Int): Call<List<Post>>

    // Rx verzija
    @GET("posts")
    fun getRxPosts(): Observable<List<Post>>

    @GET("posts")
    fun getRxPostsByUser(@Query("userId") userId: Int): Observable<List<Post>>


}