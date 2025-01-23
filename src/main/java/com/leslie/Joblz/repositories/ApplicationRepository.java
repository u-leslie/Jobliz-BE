package com.leslie.Joblz.repositories;

import com.leslie.Joblz.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {

}
