package com.repository;

import com.VO.BoardItemVO;
import com.table.BoardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer> {
    @Query(
        value = "SELECT " +
                "b.boardItemId as boardItemId, " +
                "b.boardItemTitle as boardItemTitle, " +
                "b.boardItemCtDate as boardItemCtDate " +
                "FROM BoardItem b"
    )
    List<Map<String, Object>> getBoardItemList(@Param(value = "pagination") Map<String, Object> pageInfo);
}
