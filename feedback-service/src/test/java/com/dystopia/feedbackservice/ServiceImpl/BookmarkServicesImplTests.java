package com.dystopia.feedbackservice.ServiceImpl;

import com.dystopia.feedbackservice.core.entity.Bookmark;
import com.dystopia.feedbackservice.core.repository.BookmarkRepository;
import com.dystopia.feedbackservice.core.service.BookmarkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

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
public class BookmarkServicesImplTests {
    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Test
    public void saveTestBookmark() {
        List<Bookmark> postList = new ArrayList<>();
        Bookmark bookmark = new Bookmark("1", "Story", "Jaime");
        given(bookmarkRepository.save(bookmark)).willReturn(bookmark);
        Bookmark savedBookmark = null;
        savedBookmark = bookmarkService.saveBookmark(bookmark,"1","2");
        assertThat(savedBookmark).isNotNull();
        verify(bookmarkRepository).save(any(Bookmark.class));
    }

    @Test
    void findByIdTestBookmark() throws Exception {
        String id = "1";
        List<Bookmark> bookmarkList = new ArrayList<>();
        Bookmark bookmark = new Bookmark("1", "Story", "Jaime");
        given(bookmarkRepository.findById(id)).willReturn(Optional.of(bookmark));
        Optional<Bookmark> expected = bookmarkService.getBookmarkById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTestBookmark() throws Exception {
        List<Bookmark> bookmarkList = new ArrayList<>();
        Bookmark bookmark1 = new Bookmark("1", "Story", "Jaime");
        Bookmark bookmark2 =  new Bookmark("2", "Story", "Jaime");
        Bookmark bookmark3 = new Bookmark("3", "Story", "Jaime");
        List<Bookmark> hashtagList = new ArrayList<>();
        bookmarkList.add(bookmark1);
        bookmarkList.add(bookmark2);
        bookmarkList.add(bookmark3);
        given(bookmarkRepository.findAll()).willReturn(bookmarkList);
        List<Bookmark> expected = bookmarkService.getAllBookmarksByPostId("1");
        assertEquals(expected, hashtagList);
    }

    @Test
    void deleteTestBookmark() throws Exception {
        String id = "1";
        bookmarkService.deleteBookmark(id);
        verify(bookmarkRepository, times(1)).deleteById(id);
    }
}
