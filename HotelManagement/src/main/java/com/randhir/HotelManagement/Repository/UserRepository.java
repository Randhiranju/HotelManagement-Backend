package com.randhir.HotelManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.randhir.HotelManagement.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
