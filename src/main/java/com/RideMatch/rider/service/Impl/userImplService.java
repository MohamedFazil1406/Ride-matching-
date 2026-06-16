package com.RideMatch.rider.service.Impl;

import com.RideMatch.rider.entity.User;
import com.RideMatch.rider.repository.userRepository;
import com.RideMatch.rider.service.userService;
import com.RideMatch.rider.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userImplService implements userService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private userRepository userRepository;

    public userImplService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        User existUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existUser != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String login(User user){
        User existUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existUser == null) {
            return null;
        }

        boolean isPasswordCorrect = passwordEncoder.matches(user.getPassword(), existUser.getPassword());
        if (!isPasswordCorrect){
            return null;
        }

        return jwtUtil.generateToken(existUser.getUsername(),existUser.getId());

    }
}
