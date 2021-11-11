package com.dystopia.feedbackservice.core.service;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Bookmark;

import java.util.List;
import java.util.Optional;

public interface BookmarkService {
    List<Bookmark> getAllBookmarksByPostId(String postId);

    List<Bookmark> getAllBookmarksByUserId(String userId);

    Optional<Bookmark> getBookmarkById(String bookmarkId);

    Bookmark saveBookmark(Bookmark bookmark, String userId, String postId);

    void deleteBookmark(String bookmarkId);

    User findUserById(String userId);

    Post findPostById(String postId);
}
