package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie) {
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()) {

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie newMovie = optMovie.get();
            newMovie.setTitle(updateMovie.getTitle());
            newMovie.setDescription(updateMovie.getDescription());
            newMovie.setReleaseDate(updateMovie.getReleaseDate());
            newMovie.setRating(updateMovie.getRating());

            newMovie.getCategories().clear();
            newMovie.getCategories().addAll(categories);

            newMovie.getStreamings().clear();
            newMovie.getStreamings().addAll(streamings);

            Movie updatedMovie = movieRepository.save(newMovie);
            return Optional.of(updatedMovie);
        }
            return Optional.empty();
    }

    //Method to find Categories
    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> {categoryService.findById(category.getId()).ifPresent(categoriesFound::add);});
        return categoriesFound;
    }

    //Method to find Streamings
    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> {streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);});
        return streamingsFound;
    }

}
