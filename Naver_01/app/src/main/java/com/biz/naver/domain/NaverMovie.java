package com.biz.naver.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NaverMovie {

    @SerializedName("lastBuildDate")
    @Expose
    private String lastBuildDate;

    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("start")
    @Expose
    private Integer start;


    @SerializedName("display")
    @Expose
    private Integer display;


    @SerializedName("items")
    @Expose
    List<NaverMovieVO> items = null;


}
