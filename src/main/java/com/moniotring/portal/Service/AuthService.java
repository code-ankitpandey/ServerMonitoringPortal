package com.moniotring.portal.Service;

import com.moniotring.portal.Component.JwtUtil;
import com.moniotring.portal.Entity.User;
import com.moniotring.portal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        // Fetch the user by username
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            return username + " does not exist in the system";
        }

        User user = optionalUser.get();

        // Check if passwords match
        if (!passwordService.matchesPassword(password, user.getPassword())) {
            return "Invalid credentials";
        }

        if(!user.isActive()){
            return username+" is inactive, Please connect with administrator";
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getUserName(), user.getRole().name());
    }

}
