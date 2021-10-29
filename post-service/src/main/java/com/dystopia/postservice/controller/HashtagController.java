package com.dystopia.postservice.controller;

import com.dystopia.postservice.core.entity.Hashtag;
import com.dystopia.postservice.core.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    @GetMapping()
    public ResponseEntity<List<Hashtag>> findAllHashtags() {
        try {
            List<Hashtag> hashtags = hashtagService.getAllHashtags();
            if (hashtags.size() > 0)
                return ResponseEntity.ok(hashtags);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(value = "/{hashtagId}")
    public ResponseEntity<Hashtag> findHashtagById(@PathVariable("hashtagId") String hashtagId) {
        try {
            Optional<Hashtag> hashtag = hashtagService.getHashtagById(hashtagId);
            return hashtag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Hashtag> insertHashtag(@RequestBody Hashtag hashtag) {
        try {
            Hashtag hashtagNew = hashtagService.saveHashtag(hashtag);
            return ResponseEntity.status(HttpStatus.CREATED).body(hashtagNew);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{hashtagId}")
    public ResponseEntity<Hashtag> updateHashtag(@PathVariable("hashtagId") String hashtagId,
                                                 @Valid @RequestBody Hashtag hashtag) {
        try {
            Optional<Hashtag> hashtagUp = hashtagService.getHashtagById(hashtagId);
            if (hashtagUp.isEmpty())
                return ResponseEntity.notFound().build();
            hashtag.setId(hashtagId);
            hashtagService.saveHashtag(hashtag);
            return ResponseEntity.ok(hashtag);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{hashtagId}")
    public ResponseEntity<Hashtag> deleteHashtag(@PathVariable("hashtagId") String hashtagId) {
        try {
            Optional<Hashtag> hashtagDelete = hashtagService.getHashtagById(hashtagId);
            if (hashtagDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            hashtagService.deleteHashtag(hashtagId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
