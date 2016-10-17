package com.apress.pss.terrormovies.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
