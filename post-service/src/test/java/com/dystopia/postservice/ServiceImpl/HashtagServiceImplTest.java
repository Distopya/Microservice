package com.dystopia.postservice.ServiceImpl;

import com.dystopia.postservice.core.entity.Hashtag;
import com.dystopia.postservice.core.entity.Post;
import com.dystopia.postservice.core.repository.HashtagRepository;
import com.dystopia.postservice.service.HashtagServiceImpl;
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
public class HashtagServiceImplTest {
    @Mock
    private HashtagRepository hashtagRepository;

    @InjectMocks
    private HashtagServiceImpl hashtagService;

    @Test
    public void saveTestHashtag() {
        List<Post> postList = new ArrayList<>();
        Hashtag hashtag = new Hashtag("1", "Story", postList);
        given(hashtagRepository.save(hashtag)).willReturn(hashtag);
        Hashtag savedHashtag = null;
        savedHashtag = hashtagService.saveHashtag(hashtag);
        assertThat(savedHashtag).isNotNull();
        verify(hashtagRepository).save(any(Hashtag.class));
    }

    @Test
    void findByIdTestHashtag() throws Exception {
        String id = "1";
        List<Post> postList = new ArrayList<>();
        Hashtag hashtag = new Hashtag("1", "Story", postList);
        given(hashtagRepository.findById(id)).willReturn(Optional.of(hashtag));
        Optional<Hashtag> expected = hashtagService.getHashtagById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTestHashtag() throws Exception {
        List<Post> postList = new ArrayList<>();
        Hashtag hashtag1 = new Hashtag("1", "Story", postList);
        Hashtag hashtag2 = new Hashtag("2", "Story", postList);
        Hashtag hashtag3 = new Hashtag("3", "Story", postList);
        List<Hashtag> hashtagList = new ArrayList<>();
        hashtagList.add(hashtag1);
        hashtagList.add(hashtag2);
        hashtagList.add(hashtag3);
        given(hashtagRepository.findAll()).willReturn(hashtagList);
        List<Hashtag> expected = hashtagService.getAllHashtags();
        assertEquals(expected, hashtagList);
    }

    @Test
    void deleteTestHashtag() throws Exception {
        String id = "1";
        hashtagService.deleteHashtag(id);
        verify(hashtagRepository, times(1)).deleteById(id);
    }
}