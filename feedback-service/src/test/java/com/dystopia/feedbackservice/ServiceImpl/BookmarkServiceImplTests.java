package com.dystopia.feedbackservice.ServiceImpl;
import com.dystopia.feedbackservice.core.entity.Bookmark;
import com.dystopia.feedbackservice.core.repository.BookmarkRepository;
import com.dystopia.feedbackservice.service.BookmarkServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BookmarkServiceImplTests {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkServiceImpl bookmarkService;

    @Test
    public void saveTest(){

        Bookmark bookmark=new Bookmark("1","marcador1","Razor");
        given(bookmarkRepository.save(bookmark)).willReturn(bookmark);
        Bookmark savedBookmark=null;
        savedBookmark=bookmarkService.saveBookmark(bookmark,"1","1");
        assertThat(savedBookmark).isNotNull();
        verify(bookmarkRepository).save(any(Bookmark.class));
    }

    @Test
    void findByIdTest() throws Exception {
        String id = "1";
        Bookmark bookmark=new Bookmark("1","marcador1","Razor");
        given(bookmarkRepository.findById(id)).willReturn(Optional.of(bookmark));
        Optional<Bookmark> expected = bookmarkService.getBookmarkById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllByUserIdTest() throws Exception {
        String id="1";
        Bookmark bookmark1=new Bookmark("1","marcador1","Razor1");
        Bookmark bookmark2=new Bookmark("2","marcador2","Razor2");
        Bookmark bookmark3=new Bookmark("3","marcador3","Razor3");
        List<Bookmark> bookmarkList = new ArrayList<>();
        bookmarkList.add(bookmark1);
        bookmarkList.add(bookmark2);
        bookmarkList.add(bookmark3);
        given(bookmarkRepository.findAll()).willReturn(bookmarkList);
        List<Bookmark> expected = bookmarkService.getAllBookmarksByUserId(id);
        assertEquals(expected, bookmarkList);
    }

    @Test
    void findAllByPostIdTest() throws Exception {
        String id="1";
        Bookmark bookmark1=new Bookmark("1","marcador1","Razor1");
        Bookmark bookmark2=new Bookmark("2","marcador2","Razor2");
        Bookmark bookmark3=new Bookmark("3","marcador3","Razor3");
        List<Bookmark> bookmarkList = new ArrayList<>();
        bookmarkList.add(bookmark1);
        bookmarkList.add(bookmark2);
        bookmarkList.add(bookmark3);
        given(bookmarkRepository.findAll()).willReturn(bookmarkList);
        List<Bookmark> expected = bookmarkService.getAllBookmarksByPostId(id);
        assertEquals(expected, bookmarkList);
    }

    @Test
    void deleteTest() throws Exception {
        String id = "1";
        bookmarkService.deleteBookmark(id);
        verify(bookmarkRepository, times(1)).deleteById(id);
    }


}