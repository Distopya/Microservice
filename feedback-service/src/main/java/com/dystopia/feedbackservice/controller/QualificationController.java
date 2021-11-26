package com.dystopia.feedbackservice.controller;

import com.dystopia.feedbackservice.config.model.Post;
import com.dystopia.feedbackservice.config.model.User;
import com.dystopia.feedbackservice.core.entity.Qualification;
import com.dystopia.feedbackservice.core.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QualificationController {
    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/posts/{postId}/qualifications")
    public ResponseEntity<List<Qualification>> findAllQualificationsByPostId(@PathVariable String postId) {
        try {
            List<Qualification> qualifications = qualificationService.getAllQualificationsByPostId(postId);
            if (qualifications.size() > 0)
                return ResponseEntity.ok(qualifications);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/users/{userId}/qualifications")
    public ResponseEntity<List<Qualification>> findAllQualificationsByUserId(@PathVariable String userId) {
        try {
            List<Qualification> qualifications = qualificationService.getAllQualificationsByUserId(userId);
            if (qualifications.size() > 0)
                return ResponseEntity.ok(qualifications);
            else
                return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users/{userId}/posts/{postId}/qualifications")
    public ResponseEntity<Qualification> insertQualification(@PathVariable("userId") String userId, @PathVariable("postId") String postId, @Valid @RequestBody Qualification qualification) {
        try {
            User userExist = qualificationService.findUserById(userId);
            Post postExist = qualificationService.findPostById(postId);
            if (userExist != null && postExist != null) {
                Qualification qualificationNew = qualificationService.saveQualification(qualification, userId, postId);
                return ResponseEntity.status(HttpStatus.CREATED).body(qualificationNew);
            } else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/users/{userId}/posts/{postId}/qualifications/{qualificationId}")
    public ResponseEntity<Qualification> updateUser(
            @PathVariable("userId") String userId, @PathVariable("postId") String postId, @PathVariable("qualificationId") String qualificationId, @Valid @RequestBody Qualification qualification) {
        try {
            Optional<Qualification> qualificationUp = qualificationService.getQualificationById(qualificationId);
            if (qualificationUp.isEmpty())
                return ResponseEntity.notFound().build();
            qualificationService.saveQualification(qualification, userId, postId);
            qualification.setId(qualificationId);
            return ResponseEntity.ok(qualification);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/qualifications/{qualificationId}")
    public ResponseEntity<Qualification> deleteQualification(@PathVariable("qualificationId") String qualificationId) {
        try {
            Optional<Qualification> qualificationDelete = qualificationService.getQualificationById(qualificationId);
            if (qualificationDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            qualificationService.deleteQualification(qualificationId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
