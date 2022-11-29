package com.repository;

import com.table.BoardItem;
import com.table.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer> {
}
