package com.example.fileProcessing.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileProcessing.models.FileDB;

@Repository
public interface FileDBrepository extends JpaRepository<FileDB, Long>{
	Optional<FileDB> findByName(String name);
}
