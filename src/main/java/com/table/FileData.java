package com.table;

import lombok.Data;

import javax.persistence.*;

@Table(name = "FileData")
@Entity
@Data
public class FileData {
    @Id
    @Column(name = "file_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "board_item_id")
    private Integer boardItemId;

    @Column(name = "file_orig_name", nullable = false)
    private String fileOrigName;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_up_date")
    private String fileUpDate;
}
