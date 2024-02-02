package com.microservice.Comment.service;

import com.microservice.Comment.config.RestTemplateConfig;
import com.microservice.Comment.entity.Comment;
import com.microservice.Comment.payload.Post;
import com.microservice.Comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

   @Autowired
   private RestTemplateConfig restTemplateConfig;
   @Autowired
   private CommentRepository commentRepository;

   public Comment saveComment(Comment comment) {

      Post post = restTemplateConfig.getRestTemplate().getForObject("http://localhost/api/posts/" + comment.getPostId(), Post.class);
  if(post!= null){
      String commentId= UUID.randomUUID().toString();
      comment.setCommentId(commentId);
     Comment savedComment = commentRepository.save(comment);
     return savedComment;
  }
  else{
     return null;
  }


   }
   public List<Comment> getAllCommentByPostId(String postId){
       List<Comment> comments = commentRepository.findByPostId(postId);
       return comments;
   }

}

