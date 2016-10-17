package com.apress.pss.terrormovies.service;

import java.util.Collection;
import java.util.List;

import com.apress.pss.terrormovies.model.Movie;

public interface MoviesService {
	Movie getMovieByName(String name);

	void addNewMovies(List<String> movies);

	Collection<Movie> getAllMovies();
}
