package com.dystopia.followservice.controller;

import com.dystopia.followservice.core.entity.Follow;
import com.dystopia.followservice.core.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/follows")
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping()
    public ResponseEntity<List<Follow>> findAllFollows() {
        try {
            List<Follow> follows = followService.getAllFollows();
            if (follows.size() > 0)
                return ResponseEntity.ok(follows);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{followId}")
    public ResponseEntity<Follow> findFollowById(@PathVariable("followId") String followId) {
        try {
            Optional<Follow> follow = followService.getFollowById(followId);
            return follow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Follow> insertFollow(@RequestBody Follow follow) {
        try {
            Follow followNew = followService.saveFollow(follow);
            return ResponseEntity.status(HttpStatus.CREATED).body(followNew);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{followId}")
    public ResponseEntity<Follow> updateFollow(
            @PathVariable("followId") String followId, @Valid @RequestBody Follow follow) {
        try {
            Optional<Follow> followUp = followService.getFollowById(followId);
            if (followUp.isEmpty())
                return ResponseEntity.notFound().build();
            followService.saveFollow(follow);
            follow.setId(followId);
            return ResponseEntity.ok(follow);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{followId}")
    public ResponseEntity<Follow> deleteFollow(@PathVariable("followId") String followId) {
        try {
            Optional<Follow> followDelete = followService.getFollowById(followId);
            if (followDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            followService.deleteFollow(followId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
