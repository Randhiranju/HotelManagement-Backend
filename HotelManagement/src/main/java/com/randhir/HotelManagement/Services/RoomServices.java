package com.randhir.HotelManagement.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.randhir.HotelManagement.Entity.Room;
import com.randhir.HotelManagement.Repository.RoomRepository;

@Service
public class RoomServices {
	
	@Autowired
	RoomRepository roomRepo;
	
	
	//addRoom
	public ResponseEntity<String> addRoom(Room room) {
		roomRepo.save(room);
		return new ResponseEntity<>("Room Added Successfully",HttpStatus.CREATED);
	}
	
	//change Room Status from occupied to vacant and viceVersa
	public ResponseEntity<String> updateStatus(Integer roomNumber) {
		Room room= roomRepo.findById(roomNumber).get();
		
		room.setStatus(room.getStatus().equals("Occupied")? "Vacant" : "Occupied");
		roomRepo.save(room);
		return new ResponseEntity<>("Updated Room Status",HttpStatus.OK);
	}
	//update all parameters of Room
	public ResponseEntity<String> updateRoom(Integer roomNumber, Room room) {
		Room rm = roomRepo.findById(roomNumber).get();
		rm.setPrice(room.getPrice());
		rm.setStatus(room.getStatus());
		roomRepo.save(rm);
		return new ResponseEntity<>("Room data Updated Successfully",HttpStatus.OK);
	}
	
	// delete room 
	public ResponseEntity<String> deleteRoom(Integer roomNumber) {
		roomRepo.deleteById(roomNumber);
		return new ResponseEntity<>("Room Deleted Successfully",HttpStatus.OK);
	}
	
	//View All Room
	public ResponseEntity<List<Room>> viewRooms(){
		return new ResponseEntity<>(roomRepo.findAll(),HttpStatus.OK);
	}
	
	// View All Room using Pagination 
	public ResponseEntity<Page<Room>> viewRoomWithPagination(int pageNumber,int pageSize){
		Page<Room> rooms= roomRepo.findAll(PageRequest.of(pageNumber, pageSize));
		return new ResponseEntity<>(rooms,HttpStatus.OK);
	}
	
	//View Rooms in sorted Order of Status
	public ResponseEntity<List<Room>> viewRoomSortedByStatus(String status){
		List<Room> rooms= roomRepo.findAll(Sort.by(Sort.Direction.ASC,status));
		return new ResponseEntity<>(rooms,HttpStatus.OK);
	}
	
	//View Rooms with Pagination and sorting
	public ResponseEntity<List<Room>> viewRoomWithPaginationAndSorting(int pageNumber,int pageSize,String sortField,String sortDir){
		if(sortDir=="asc") {
			Page<Room> page= roomRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC, sortField));
			List<Room> rooms= page.getContent();
			return new ResponseEntity<>(rooms,HttpStatus.OK);
		}else {
			Page<Room> page= roomRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.DESC, sortField));
			List<Room> rooms= page.getContent();
			return new ResponseEntity<>(rooms,HttpStatus.OK);
		}
		
	}
	
	//View only available Room
	public ResponseEntity<List<Room>> viewAvailableRooms(){
		List<Room> rooms= roomRepo.findAll();
		List<Room> availableRoom= new ArrayList<>();
		for(Room room:rooms) {
			if(room.getStatus().equals("Vacant")) {
				availableRoom.add(room);
			}
		}		return new ResponseEntity<>(availableRoom,HttpStatus.OK);
	}
	
}
