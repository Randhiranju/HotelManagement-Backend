package com.randhir.HotelManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.randhir.HotelManagement.Entity.Room;
import com.randhir.HotelManagement.Entity.User;
import com.randhir.HotelManagement.Services.RoomServices;
import com.randhir.HotelManagement.Services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices userServ;
	
	@Autowired
	RoomServices roomServ;
	
	// Register User
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
		return userServ.registerUser(user);
	}
	
	//login : url (http://localhost:8080/user/login?id=1&name=Randhir&password=randhir@123)
	@GetMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String password){
		return userServ.loginUser(id, name,password);
	}
	
	//View List of Room with Pagination
	@GetMapping("/viewRoomsPagination/{pageNum}/{pageSize}")
	public ResponseEntity<Page<Room>> viewRoomsWithPagination(@PathVariable int pageNum, @PathVariable int pageSize){
		return roomServ.viewRoomWithPagination(pageNum, pageSize);
	}
	
	//View Room sorted By Status in Ascending order
	@GetMapping("/viewRoomsSorted")
	public ResponseEntity<List<Room>> viewRoomsSortedByStatus(@RequestParam String status){
		return roomServ.viewRoomSortedByStatus(status);
	}
	
	//View room with paging and sorting
	@GetMapping("/viewRoomsPaginationAndSorting")
	public ResponseEntity<List<Room>> viewRoomsWithPaginationAndSorting(@RequestParam int pageNum, @RequestParam int pageSize,@RequestParam String sortField,@RequestParam String sortDir){
		return roomServ.viewRoomWithPaginationAndSorting(pageNum, pageSize, sortField,sortDir);
	}
	
	//View only Vacant Room
	@GetMapping("/viewAvlRooms")
	public ResponseEntity<List<Room>> viewAvailableRooms(){
		return roomServ.viewAvailableRooms();
	}
	
	//Booking Room 
	@PutMapping("/bookRoom/{roomNumber}")
	public ResponseEntity<String> bookRoom(@PathVariable Integer roomNumber){
		return userServ.bookRoom(roomNumber);
	}
}
