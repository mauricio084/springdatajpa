package com.example.springdata.springjpa.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springdata.springjpa.model.User;

/**
 * UserRepository demonstrates the method name query generation.
 * 
 * @author Ramesh Fadatare
 *
 */
@Repository
public interface UserRepositoryWithQuery extends JpaRepository<User, Long> {
	
	@Query(value = "select o from User o where o.firstname = :firstname AND o.lastname = :lastname")
	Optional<User> getByNombres(String firstname, String lastname);

	@Query("SELECT u FROM User u WHERE u.firstname = ?1 OR u.lastname = ?2")
	Optional<User> findByNombreOApellido(String firstname, String lastname);

	@Query("SELECT u FROM User u WHERE u.startDate BETWEEN :inicio AND :fin")
	List<User> getUsersByDate(@Param(value = "inicio") Date date1, @Param(value = "fin") Date date2);

	@Query("SELECT u FROM User u WHERE u.age > ?1")
	List<User> findByAgeGreaterThan(int age);

	@Query(nativeQuery = true, value = "SELECT u.* FROM user u WHERE u.age >= ?1")
	List<User> findByAgeGreaterThanEqual(int age);
	
	@Query("select u from User u where u.age is null")
	List<User> findByEdadNull();
	
	
	List<User> findByAgeLessThanEqual(int age);

	List<User> findByStartDateAfter(Date date);

	List<User> findByStartDateBefore(Date date);
	
	List<User> findByFirstnameLike(String firstname);

	List<User> findByFirstnameNotLike(String firstname);

	Optional<User> findByFirstnameStartingWith(String firstname);

	List<User> findByFirstnameEndingWith(String firstname);

	List<User> findByFirstnameContaining(String firstname);

	Optional<User> findByAgeOrderByLastnameDesc(int age);

	List<User> findByLastnameNot(String lastname);

	List<User> findByAgeIn(Collection<Integer> ages);

	List<User> findByAgeNotIn(Collection<Integer> ages);

	List<User> findByActiveTrue();

	List<User> findByActiveFalse();

	List<User> findByFirstnameIgnoreCase(String firstname);
}
