package com.table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "File")
@Entity
@Data
public class File {
    @Id
    @Column(name = "file_id", nullable = false)
    private Integer memberId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "board_item_id", nullable = false)
    private Integer boardItemId;

    @Column(name = "file_orig_name", nullable = false)
    private String fileOrigName;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_up_date")
    private String fileUpDate;
}
