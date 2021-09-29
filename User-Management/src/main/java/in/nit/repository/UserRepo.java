package in.nit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.User;

public interface UserRepo extends JpaRepository<User, Serializable> {

	public User findByEmailAndPassword(String email, String password );
}
