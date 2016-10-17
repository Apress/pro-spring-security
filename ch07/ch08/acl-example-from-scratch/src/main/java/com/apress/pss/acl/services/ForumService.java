package com.apress.pss.acl.services;

import java.util.Map;

import com.apress.pss.acl.domain.Post;

public interface ForumService {
	void createPost(Post post);
	Map<Integer, Post> getPosts();
}
