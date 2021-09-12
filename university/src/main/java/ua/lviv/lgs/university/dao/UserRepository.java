package ua.lviv.lgs.university.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.university.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
		
	Optional<User> findByEmail(String email);


}
