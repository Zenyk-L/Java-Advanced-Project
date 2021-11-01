package ua.lviv.lgs.university.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.UserRepository;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.domain.UserRole;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        logger.info("Save user " + user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        logger.info("Get user by email " + email);

        return userRepository.findByEmail(email).get();
    }

    public User getById(Integer userId) {
        logger.info("Get user by id " + userId);

        return userRepository.getById(userId);
    }

}
