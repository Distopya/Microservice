package com.dystopia.feedbackservice.service;

import com.dystopia.feedbackservice.config.client.PostFeignClient;
import com.dystopia.feedbackservice.config.client.UserFeignClient;
import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Bookmark;
import com.dystopia.feedbackservice.core.repository.BookmarkRepository;
import com.dystopia.feedbackservice.core.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PostFeignClient postFeignClient;

    @Override
    public List<Bookmark> getAllBookmarksByPostId(String postId) {
        return bookmarkRepository.findByPost(postId);
    }

    @Override
    public List<Bookmark> getAllBookmarksByUserId(String userId) {
        return bookmarkRepository.findByUser(userId);
    }

    @Override
    public Optional<Bookmark> getBookmarkById(String bookmarkId) {
        return bookmarkRepository.findById(bookmarkId);
    }

    @Override
    @Transactional
    public Bookmark saveBookmark(Bookmark bookmark, String userId, String postId) {
        bookmark.setUser(userId);
        bookmark.setPost(postId);
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void deleteBookmark(String bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

    @Override
    public User findUserById(String userId) {
        return userFeignClient.findUserById(userId);
    }

    @Override
    public Post findPostById(String postId) {
        return postFeignClient.findPostById(postId);
    }
}
