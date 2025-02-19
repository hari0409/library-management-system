package com.example.library_management_system.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_management_system.ExceptionHandler.GlobalException;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.entity.UserAddress;
import com.example.library_management_system.repository.UserAddressRepository;
import com.example.library_management_system.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserAddressRepository userAddressRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserService(UserRepository userRepository, UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
    }

    public String createOrUpdateUser(JsonNode userData) {
        User user = objectMapper.convertValue(userData.get("user"), User.class);
        if (user.getId() != null) {
            // Check if the user already exists
            Optional<User> existingUserOpt = userRepository.findById(user.getId());
            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();

                // Update only the fields that should be updated
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPhone(user.getPhone());
                existingUser.setMembershipType(user.getMembershipType());
                existingUser.setUpdatedAt(LocalDateTime.now()); // Set update time
                userRepository.save(existingUser);
                return "User Updated, ID: " + existingUser.getId();
            }
        }

        // If no existing user, create a new one
        user.setMembershipDate(LocalDateTime.now());
        userRepository.save(user);
        return "User Created, ID: " + user.getId();
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new GlobalException("User not found for the Id: " + id, 404));
    }

    public User createOrUpdateUserAddress(JsonNode address) {
        int userId = address.get("userId").asInt();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        UserAddress userAddress = objectMapper.convertValue(address.get("address"), UserAddress.class);

        // Check if the user already has an address
        if (user.getUserAddress() != null) {
            // Update the existing address
            UserAddress existingAddress = user.getUserAddress();
            existingAddress.setStreet(userAddress.getStreet());
            existingAddress.setCity(userAddress.getCity());
            existingAddress.setState(userAddress.getState());
            existingAddress.setPostalCode(userAddress.getPostalCode());
            existingAddress.setCountry(userAddress.getCountry());

            userAddressRepository.save(existingAddress);
        } else {
            // Create a new address
            userAddress.setUser(user);
            user.setUserAddress(userAddress);
            userAddressRepository.save(userAddress);
        }
        return user;
    }

    public UserAddress findUserAddressById(Integer id) {
        User user = userRepository.findById(id).get();
        return user.getUserAddress();
    }
}
