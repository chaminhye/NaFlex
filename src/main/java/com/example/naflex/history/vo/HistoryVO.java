package com.example.naflex.history.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="user_history")
public class HistoryVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "history_idx")
    private Long historyIdx;

    @Column(name="vod_name")
    private String vodName;

    @Column(name="user_idx")
    private Long userIdx;

    @Column(name="vod_idx")
    private Long vodIdx;

    @Column(name="view_time")
    private String viewTime;

}
