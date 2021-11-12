package com.dystopia.followservice.serviceImpl;

import com.dystopia.followservice.core.entity.Follow;
import com.dystopia.followservice.core.repository.FollowRepository;
import com.dystopia.followservice.service.FollowServiceImpl;
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
public class FollowServiceImplTest {

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowServiceImpl followService;

    @Test
    public void saveTest() {
        Follow follow = new Follow("1","2","3");
        given(followRepository.save(follow)).willReturn(follow);
        Follow savedFollow = null;
        savedFollow = followService.saveFollow(follow);
        assertThat(savedFollow).isNotNull();
        verify(followRepository).save(any(Follow.class));
    }

    @Test
    void findByIdTest() throws Exception {
        String id = "1";
        Follow follow = new Follow("1", "2", "3");
        given(followRepository.findById(id)).willReturn(Optional.of(follow));
        Optional<Follow> expected = followService.getFollowById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        Follow follow = new Follow("1", "2", "3");
        Follow follow2 = new Follow("2", "1", "2");
        Follow follow3 = new Follow("3", "3", "1");
        List<Follow> followList = new ArrayList<>();
        followList.add(follow);
        followList.add(follow2);
        followList.add(follow3);
        given(followRepository.findAll()).willReturn(followList);
        List<Follow> expected = followService.getAllFollows();
        assertEquals(expected, followList);
    }

    @Test
    void deleteTest() throws Exception {
        String id = "1";
        followService.deleteFollow(id);
        verify(followRepository, times(1)).deleteById(id);
    }

}
