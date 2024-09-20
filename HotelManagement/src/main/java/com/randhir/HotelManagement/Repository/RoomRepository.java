package com.randhir.HotelManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.randhir.HotelManagement.Entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{

}
