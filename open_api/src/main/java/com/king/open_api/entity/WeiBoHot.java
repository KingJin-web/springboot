package com.king.open_api.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月28日 11:02
 * @description:
 */
@Data
public class WeiBoHot {
    private WeiBoData data;

    @Data
    public static class WeiBoData {
        private List<WeiBo> band_list;
    }

    @Data
    public static class WeiBo {
        //
        private String note;
        private String word;
        private Long onboard_time;
        private Long raw_hot;
        private String category;
        private String word_scheme;
        private String mblog;
    }
}