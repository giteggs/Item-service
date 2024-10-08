package com.org.Item;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.org.Item.entity.User;
import com.org.Item.repo.UserRepository;
import com.org.Item.service.UserService;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
    private UserService userService;

	 @Mock
	    private UserRepository userRepository; 

	    @InjectMocks
	    private UserService userServiceUnderTest; 

	    private User user;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        user = new User();
	        user.setId(1L);
	        user.setName("John Doe");
	        user.setEmail("john.doe@example.com");
	    }

	    @Test
	    void testSearchUsers() {
	        List<User> userList = new ArrayList<>();
	        userList.add(user);
	        Page<User> page = new PageImpl<>(userList, PageRequest.of(0, 10), userList.size());

	        when(userRepository.findByNameContainingIgnoreCase("John", PageRequest.of(0, 10))).thenReturn(page);

	        Page<User> users = userServiceUnderTest.searchUsers("John", 0, 10);
	        assertNotNull(users);
	        assertEquals(1, users.getTotalElements());
	        assertEquals("John Doe", users.getContent().get(0).getName());
	    }

	    @Test
	    void testGetUserById_ReturnsUser_WhenExists() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

	        Optional<User> foundUser = userServiceUnderTest.getUserById(1L);
	        assertTrue(foundUser.isPresent());
	        assertEquals("John Doe", foundUser.get().getName());
	    }

	    @Test
	    void testGetUserById_ReturnsEmpty_WhenNotExists() {
	        when(userRepository.findById(2L)).thenReturn(Optional.empty());

	        Optional<User> foundUser = userServiceUnderTest.getUserById(2L);
	        assertFalse(foundUser.isPresent());
	    }

	    @Test
	    void testCreateUser() {
	        when(userRepository.save(user)).thenReturn(user);

	        User createdUser = userServiceUnderTest.createUser(user);
	        assertNotNull(createdUser);
	        assertEquals("John Doe", createdUser.getName());
	    }

	    @Test
	    void testUpdateUser() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
	        when(userRepository.save(user)).thenReturn(user);

	        User updatedUser = userServiceUnderTest.updateUser(1L, user);
	        assertNotNull(updatedUser);
	        assertEquals("John Doe", updatedUser.getName());
	    }

	    @Test
	    void testDeleteUser() {
	        doNothing().when(userRepository).deleteById(1L);

	        assertDoesNotThrow(() -> userServiceUnderTest.deleteUser(1L));
	        verify(userRepository, times(1)).deleteById(1L);
	    }
	}

