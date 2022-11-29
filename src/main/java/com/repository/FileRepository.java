package com.repository;


import com.table.File;
import com.table.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {


}
