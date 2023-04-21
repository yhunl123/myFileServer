package com.table;

import lombok.Data;

import javax.persistence.*;

@Table(name = "FileThumbImage")
@Entity
@Data
public class FileThumbImage {
    @Id
    @Column(name = "pk_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkSeq;

    @Column(name = "fd_thumb_path", nullable = false)
    private String fdThumbPath;

    @Column(name = "fd_thumb_name", nullable = false)
    private String fdThumbName;

    @Column(name = "fd_thumb_size")
    private Long fdThumbSize;

    @Column(name = "fk_file_seq", nullable = false)
    private Integer fkFileSeq;
}
