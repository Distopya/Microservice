package com.dystopia.userservice.ServiceImpl;

import com.dystopia.userservice.core.entity.User;
import com.dystopia.userservice.core.repository.UserRepository;
import com.dystopia.userservice.service.UserServiceImpl;
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
public class UserServiceImplTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveTest() {
        User user = new User("1", "Razor", "123", "Raul", "Carlos", "jose@gmail.com", "123456789", "2", "2");
        given(userRepository.save(user)).willReturn(user);
        User savedUser = null;
        savedUser = userService.saveUser(user);
        assertThat(savedUser).isNotNull();
        verify(userRepository).save(any(User.class));
    }

    @Test
    void findByIdTest() throws Exception {
        String id = "1";
        User user = new User("1", "Razor", "123", "Raul", "Carlos", "jose@gmail.com", "123456789", "2", "2");
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        Optional<User> expected = userService.getUserById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        User user = new User("1", "Razor", "123", "Raul", "Carlos", "jose@gmail.com", "123456789", "2", "2");
        User user2 = new User("2", "Razor2", "123", "Raul", "Carlos", "jose@gmail.com", "123456789", "2", "2");
        User user3 = new User("3", "Razor3", "123", "Raul", "Carlos", "jose@gmail.com", "123456789", "2", "2");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        given(userRepository.findAll()).willReturn(userList);
        List<User> expected = userService.getAllUsers();
        assertEquals(expected, userList);
    }

    @Test
    void deleteTest() throws Exception {
        String id = "1";
        userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}
