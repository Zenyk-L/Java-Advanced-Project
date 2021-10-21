package ua.lviv.lgs.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.UserRepository;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.domain.UserRole;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
		user.setRole(UserRole.ROLE_USER);
		userRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}

	public User getById(Integer userId) {

		return userRepository.getById(userId);
	}

}
