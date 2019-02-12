package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {


}
