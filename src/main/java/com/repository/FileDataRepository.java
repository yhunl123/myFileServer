package com.repository;


import com.VO.FileVO;
import com.table.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Map;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {


    @Query(
        value = "SELECT f.fileId as fileId, " +
                "f.fileName as fileName, " +
                "f.fileOrigName as fileOrigName, " +
                "f.filePath as filePath, " +
                "f.fileSize as fileSize " +
                "FROM FileData f " +
                "WHERE f.fileId = :#{#fileVO.fileId}"
    )
    Map<String, Object> searchFileDataByFileId(@Param(value = "fileVO") String fileVO);

    @Query(
        value = "SELECT f.fileId as fileId, " +
                "f.fileName as fileName, " +
                "f.fileOrigName as fileOrigName, " +
                "f.filePath as filePath, " +
                "f.fileSize as fileSize " +
                "FROM FileData f " +
                "WHERE f.fileName = :fileName"
    )
    Map<String, Object> searchFileDataByFileName(@Param(value = "fileName") String fileName);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO FILE_DATA (FILE_NAME, FILE_PATH, FILE_ORIG_NAME, FILE_SIZE) " +
                "VALUES (:#{#fileVO.fileName}, :#{#fileVO.filePath}, :#{#fileVO.fileOrigName}, :#{#fileVO.fileSize})",
        nativeQuery = true
    )
    void insertFileData(@Param(value = "fileVO") FileVO fileVO);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM FileData f WHERE f.fileName = :fileName"
    )
    void deleteByFileName(String fileName);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE FileData f " +
                    "SET f.boardItemId = :#{#fileVO.boardItemId} " +
                    "WHERE f.fileId = :#{#fileVO.fileId}"
    )
    void updateFileBoardId(@Param(value = "fileVO") FileVO fileVO);
}
