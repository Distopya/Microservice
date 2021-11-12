package com.dystopia.postservice.ServiceImpl;

import com.dystopia.postservice.core.entity.Hashtag;
import com.dystopia.postservice.core.entity.Post;
import com.dystopia.postservice.core.repository.PostRepository;
import com.dystopia.postservice.service.PostServiceImpl;
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
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void saveTestPost() {
        List<Hashtag> hashtagList = new ArrayList<>();
        Post post = new Post(hashtagList, "1", "El mejor titulo", "Una buena description", "La historia más epica de todas", "1");
        given(postRepository.save(post)).willReturn(post);
        Post savedPost = null;
        savedPost = postService.savePost(post);
        assertThat(savedPost).isNotNull();
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void findByIdTestPost() throws Exception {
        String id = "1";
        List<Hashtag> hashtagList = new ArrayList<>();
        Post post = new Post(hashtagList, "1", "El mejor titulo", "Una buena description", "La historia más epica de todas", "1");
        given(postRepository.findById(id)).willReturn(Optional.of(post));
        Optional<Post> expected = postService.getPostById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTestPost() throws Exception {
        List<Hashtag> hashtagList = new ArrayList<>();
        Post post = new Post(hashtagList, "1", "El mejor titulo", "Una buena description", "La historia más epica de todas", "1");
        Post post2 = new Post(hashtagList, "2", "El mejor titulo", "Una buena description", "La historia más epica de todas", "1");
        Post post3 = new Post(hashtagList, "3", "El mejor titulo", "Una buena description", "La historia más epica de todas", "1");
        List<Post> postList = new ArrayList<>();
        postList.add(post);
        postList.add(post2);
        postList.add(post3);
        given(postRepository.findAll()).willReturn(postList);
        List<Post> expected = postService.getAllPosts();
        assertEquals(expected, postList);
    }

    @Test
    void deleteTestPost() throws Exception {
        String id = "1";
        postService.deletePost(id);
        verify(postRepository, times(1)).deleteById(id);
    }
}
