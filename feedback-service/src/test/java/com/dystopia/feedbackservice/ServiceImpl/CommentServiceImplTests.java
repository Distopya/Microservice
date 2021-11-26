package com.dystopia.feedbackservice.ServiceImpl;

import com.dystopia.feedbackservice.core.entity.Comment;
import com.dystopia.feedbackservice.core.repository.CommentRepository;
import com.dystopia.feedbackservice.core.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTests {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void saveTestComment() {
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment("1","Story","Story","Jaime");
        given(commentRepository.save(comment)).willReturn(comment);
        Comment savedComment = null;
        savedComment = commentService.saveComment(comment,"1","2");
        assertThat(savedComment).isNotNull();
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void findByIdTestComment() throws Exception {
        String id = "1";
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment("1","Story","Story","Jaime");
        given(commentRepository.findById(id)).willReturn(Optional.of(comment));
        Optional<Comment> expected = commentService.getCommentById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTestComment() throws Exception {
        List<Comment> commentList = new ArrayList<>();
        Comment comment1 = new Comment("1","Story","Story","Jaime");
        Comment comment2 = new Comment("2","Story","Story","Jaime");
        Comment comment3 = new Comment("3","Story","Story","Jaime");
        List<Comment> commentList1 = new ArrayList<>();
        commentList1.add(comment1);
        commentList1.add(comment2);
        commentList1.add(comment3);
        given(commentRepository.findAll()).willReturn(commentList);
        List<Comment> expected = commentService.getAllCommentsByUserId("1");
        assertEquals(expected, commentList1);
    }

    @Test
    void deleteTestComment() throws Exception {
        String id = "1";
        commentService.deleteComment(id);
        verify(commentRepository, times(1)).deleteById(id);
    }
}
