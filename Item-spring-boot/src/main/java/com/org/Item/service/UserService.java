package com.org.Item.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.org.Item.entity.User;
import com.org.Item.repo.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Page<User> searchUsers(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByNameContainingIgnoreCase(query, pageable);
    }	
    public User createUser(User user) {
        return userRepository.save(user); // This will save and generate the ID
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Fetch user from the database
    }
    
    public String callExternalService() {
        String url = "https://api.example.com/external"; // Replace with your actual external API URL
        return restTemplate.getForObject(url, String.class); // Modify response type as needed
    }
	public Object deleteUser(long l) {
		
		return null;
	}
	public User updateUser(long l, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
