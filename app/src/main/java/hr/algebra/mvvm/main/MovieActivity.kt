package hr.algebra.mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hr.algebra.mvvm.databinding.ActivityMainBinding
import hr.algebra.mvvm.model.Movie
import hr.algebra.mvvm.repositories.MovieRepository
import javax.inject.Inject


@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

   //  private lateinit var viewModel: MovieViewModel

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        setupListeners()
        bind()
    }

    private fun setupListeners(){
        binding.btnAdd.setOnClickListener { addMovie() }
    }

    private fun bind(){
        viewModel.getMovies().observe(this, {
            binding.tvMovies.text = it.joinToString("\n")
        })

        viewModel.addedMoviesCount.observe(this, {
            Toast.makeText(this, "Dodano $it filmova", Toast.LENGTH_SHORT).show()
        })

        viewModel.errors.observe(this, { error ->
            error?.let { Toast.makeText(this,it, Toast.LENGTH_SHORT).show() }
        })
    }

    private fun addMovie(){
        val title = binding.etMovieTitle.text.toString()
        val director = binding.etMovieDirector.text.toString()

        if(title.isNotBlank() && director.isNotBlank()){
            viewModel.addMovie(title, director)
        }
    }
}