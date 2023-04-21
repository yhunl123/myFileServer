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
                "b.boardItemCtDate as boardItemCtDate, " +
                "t.fdThumbPath as fdThumbPath, " +
                "t.fdThumbName as fdThumbName " +
                "FROM BoardItem b " +
                "LEFT OUTER JOIN FileData f ON b.boardItemId = f.boardItemId " +
                "LEFT OUTER JOIN FileThumbImage t ON f.fileId = t.fkFileSeq"
    )
    List<Map<String, Object>> getBoardItemList(@Param(value = "pagination") Map<String, Object> pageInfo);
}
