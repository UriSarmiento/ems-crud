package com.uriel.springboot.ems_backend.ems_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uriel.springboot.ems_backend.ems_backend.entity.Employee;;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
