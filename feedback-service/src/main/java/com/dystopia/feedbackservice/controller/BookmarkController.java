package com.dystopia.feedbackservice.controller;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Bookmark;
import com.dystopia.feedbackservice.core.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/posts/{postId}/bookmarks")
    public ResponseEntity<List<Bookmark>> findAllBookmarksByPostId(@PathVariable String postId) {
        try {
            List<Bookmark> bookmarks = bookmarkService.getAllBookmarksByPostId(postId);
            if (bookmarks.size() > 0)
                return ResponseEntity.ok(bookmarks);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/users/{userId}/bookmarks")
    public ResponseEntity<List<Bookmark>> findAllBookmarksByUserId(@PathVariable String userId) {
        try {
            List<Bookmark> bookmarks = bookmarkService.getAllBookmarksByUserId(userId);
            if (bookmarks.size() > 0)
                return ResponseEntity.ok(bookmarks);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users/{userId}/posts/{postId}/bookmarks")
    public ResponseEntity<Bookmark> insertBookmark(@PathVariable("userId") String userId, @PathVariable("postId") String postId, @Valid @RequestBody Bookmark bookmark) {
        try {
            User userExist = bookmarkService.findUserById(userId);
            Post postExist = bookmarkService.findPostById(postId);
            if (userExist != null && postExist != null) {
                Bookmark bookmarkNew = bookmarkService.saveBookmark(bookmark, userId, postId);
                return ResponseEntity.status(HttpStatus.CREATED).body(bookmarkNew);
            } else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/users/{userId}/posts/{postId}/bookmarks/{bookmarkId}")
    public ResponseEntity<Bookmark> updateUser(
            @PathVariable("userId") String userId, @PathVariable("postId") String postId, @PathVariable("bookmarkId") String bookmarkId, @Valid @RequestBody Bookmark bookmark) {
        try {
            Optional<Bookmark> bookmarkUp = bookmarkService.getBookmarkById(bookmarkId);
            if (bookmarkUp.isEmpty())
                return ResponseEntity.notFound().build();
            bookmarkService.saveBookmark(bookmark, userId, postId);
            bookmark.setId(bookmarkId);
            return ResponseEntity.ok(bookmark);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/bookmarks/{bookmarkId}")
    public ResponseEntity<Bookmark> deleteBookmark(@PathVariable("bookmarkId") String bookmarkId) {
        try {
            Optional<Bookmark> bookmarkDelete = bookmarkService.getBookmarkById(bookmarkId);
            if (bookmarkDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            bookmarkService.deleteBookmark(bookmarkId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
