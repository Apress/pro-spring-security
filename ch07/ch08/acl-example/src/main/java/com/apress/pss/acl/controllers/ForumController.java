package com.apress.pss.acl.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apress.pss.acl.domain.Post;
import com.apress.pss.acl.services.ForumService;

@Controller
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	private ForumService forumService;
	@RequestMapping(method = RequestMethod.POST, value = "/post")
	public ModelAndView createPost(@RequestBody String postContent){
		forumService.createPost(new Post(postContent));
		return showForm();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/post/delete")
	public ModelAndView deletePost(@RequestParam Integer postId){
		Post post = new Post("non-relevant");
		post.setId(postId);
		forumService.deletePost(post);
		return showForm();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView showForm(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("posts", forumService.getPosts());
		return new ModelAndView("form",model);
	}
}
