package com.randhir.HotelManagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.randhir.HotelManagement.Entity.Room;
import com.randhir.HotelManagement.Entity.User;
import com.randhir.HotelManagement.Repository.RoomRepository;
import com.randhir.HotelManagement.Repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	//Register User
	public ResponseEntity<String> registerUser(User user) {
		user.setRole("user");
		userRepo.save(user);
		return new ResponseEntity<>("User Registered Successfully",HttpStatus.CREATED);
	}
	
	//Login
	public ResponseEntity<String> loginUser(Integer id, String name, String password) {
		User user= userRepo.findById(id).get();
		if(user.getPassword().equals(password) && user.getUserName().equals(name)) {
			return new ResponseEntity<>("User Verified",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Invalid User",HttpStatus.BAD_REQUEST);
		}
	}
	
	//Delete User
	public ResponseEntity<String> deleteUser(Integer userId) {
		userRepo.deleteById(userId);
		return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
	}

	//User can book a room 
	public ResponseEntity<String> bookRoom(Integer roomNumber) {
		Room room =roomRepo.findById(roomNumber).get();
		if(room.getStatus().equals("Vacant")) {
			return new ResponseEntity<>("Pay and Book",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Please Select any other Room ",HttpStatus.BAD_REQUEST);
		}
	}


}
