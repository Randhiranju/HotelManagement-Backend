package com.randhir.HotelManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randhir.HotelManagement.Entity.Room;
import com.randhir.HotelManagement.Services.RoomServices;
import com.randhir.HotelManagement.Services.UserServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	RoomServices roomServ;
	
	@Autowired
	UserServices userServ;
	
	// Admin can add Room 
	@PostMapping("/addRoom")
	public ResponseEntity<String> addRoom(@RequestBody Room room){
		return roomServ.addRoom(room);
	}
	
	//Update room status
	@PutMapping("/updateStatus/{roomNumber}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer roomNumber){
		return roomServ.updateStatus(roomNumber);
	}
	
	//update all parameter
	@PutMapping("/updateRoom/{roomNumber}")
	public ResponseEntity<String> updateRoom(@PathVariable Integer roomNumber,@RequestBody Room room){
		return roomServ.updateRoom(roomNumber,room);
	}
	
	//Admin can Delete Room
	@DeleteMapping("/deleteRoom/{roomNumber}")
	public ResponseEntity<String> DeleteRoom(@PathVariable Integer roomNumber){
		return roomServ.deleteRoom(roomNumber);
	}
	
	//Admin can delete User also
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		return userServ.deleteUser(userId);
	}
	
	//Admin can view Room
	@GetMapping("/viewRooms")
	public ResponseEntity<List<Room>> viewRooms(){
		return roomServ.viewRooms();
	}
	//Admin can view available room
	@GetMapping("/viewAvlRooms")
	public ResponseEntity<List<Room>> viewAvailableRooms(){
		return roomServ.viewAvailableRooms();
	}
}
