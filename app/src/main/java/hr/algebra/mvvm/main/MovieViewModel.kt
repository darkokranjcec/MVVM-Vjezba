package hr.algebra.mvvm.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.algebra.mvvm.model.Movie
import hr.algebra.mvvm.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepo: MovieRepository): ViewModel() {

    private val _addedMoviesCount = MutableLiveData<Int>(0)
    public val addedMoviesCount: LiveData<Int> = _addedMoviesCount

    private val _errors = MutableLiveData<String?>(null)
    val errors: LiveData<String?> = _errors

    fun getMovies(): LiveData<List<Movie>> {
        viewModelScope.launch {
            try {
                movieRepo.fetchMovies()
            } catch (e:Throwable) {
                Log.e("MovieViewModel", "getMovies", e)
                _errors.postValue(e.message)
            }
        }
        return movieRepo.getMovies()
    }

    fun addMovie(title: String, director: String) {
//        _addedMoviesCount.postValue(_addedMoviesCount.value?.plus(1))
//        val movie = Movie(0, title, director)
//        movieRepo.addMovie(movie)

        val movie = Movie(0, title, director)
        viewModelScope.launch {
            try {
                movieRepo.addSuspendMovie(movie)
            }catch (e: Throwable){
                _errors.postValue(e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}