package com.king.open_api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月25日 13:34
 * @description:
 */
@Data
@ApiModel(value = "新闻模板")
public class NewsModel {
    //我只需要原新闻链接（url），新闻来源（出自哪篇新闻网站），新闻内容
    @ApiModelProperty(value = "新闻链接")
    private String url;
    @ApiModelProperty(value = "新闻来源")
    private String source;
    @ApiModelProperty(value = "新闻内容")
    private String content;
    @ApiModelProperty(value = "新闻标题")
    private String title;
    @ApiModelProperty(value = "新闻发布时间")
    private String time;
    @ApiModelProperty(value = "新闻图片")
    private String img;

}