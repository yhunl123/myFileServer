package com.table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BoardItem")
@Entity
@Data
public class BoardItem {

    @Id
    @Column(name = "board_item_id", nullable = false)
    private Integer boardItemId;

    @Column(name = "board_item_title", nullable = false)
    private String boardItemTitle;

    @Column(name = "board_item_contents")
    private String boardItemContents;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "board_item_ct_date")
    private String boardItemCtDate;

}
