package com.apress.pss.terrormovies.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apress.pss.terrormovies.model.Movie;

@Controller
@RequestMapping("/movies")
public class MovieController {
	
	private Map<Integer, String[]> likedMovies;
	
	public MovieController(){
		likedMovies = new HashMap<Integer, String[]>();
		likedMovies.put(1, new String[]{"Die Hard", "Lethal Weapon"});
	}
	@RequestMapping(method = RequestMethod.GET, value = "/member/{id}")
	@ResponseBody
	public String getMoviesForMember(@PathVariable int id) {
		StringBuilder builder = new StringBuilder();
		for(String movie:likedMovies.get(id)){
			builder.append(movie);
		}
		return builder.toString();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView getAllMovies() {
		ModelAndView mv = new ModelAndView("movies");
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Die hard", "25000000"));
		movies.add(new Movie("Lethatl Weapon", "30000000"));
		mv.addObject("movies", movies);
		return mv;
	}
}
