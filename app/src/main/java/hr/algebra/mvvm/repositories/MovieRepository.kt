package hr.algebra.mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.algebra.mvvm.database.AppDatabase
import hr.algebra.mvvm.di.DatabaseIOExecutor
import hr.algebra.mvvm.model.Movie
import hr.algebra.mvvm.networking.SocialNetworkService
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

// u repository ubacujete kod za obradu tih sirovih podataka
// smanjujete kompleksnost ViewModela, Activitya
// primjer, ubacivanje se executea na drugoj dretvi
// filtriranja, obrade podataka itd. isto radite u repozitoriju
class MovieRepository @Inject constructor(
    private val database: AppDatabase,
    @DatabaseIOExecutor private val databaseExecutor: Executor,
    private val api: SocialNetworkService) {

    private val moviesDao = database.moviesDao()

    fun addMovie(movie: Movie){
        databaseExecutor.execute {
            moviesDao.addMovie(movie)
        }
    }

    suspend fun addSuspendMovie(movie: Movie) {
        moviesDao.addSuspendMovie(movie)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return moviesDao.getMovies()
    }

    suspend fun fetchMovies() {
        val posts = api.getBooks()
        moviesDao.saveMovies(posts.map { post -> Movie(post.id, post.title, post.body) })
    }
}