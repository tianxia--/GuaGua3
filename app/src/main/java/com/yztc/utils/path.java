package com.yztc.utils;

/**
 * Created by Administrator on 2016/9/12.
 */
public class path {
    //接口数据
    //广告接口
    public static final String PATH_AD="http://csapi.dm300.com:21889/android/recom/edi" +
            "torrecomlist?pagesize=4&platform_id=0";
    //热门推荐接口
    public static final String PATH_HOT="http://csapi.dm300.com:21889/android" +
            "/recom/hotlist?pagesize=30&page=";
    //小编推荐接口
    public static final String PATH_RECOMMEND="http://csapi.dm300.com:21889/android/recom/" +
            "editorlist?pagesize=30&page=";
    //精彩港漫
    public static final String PATH_COMIC="http://csapi.dm300.com:21889/android/" +
            "recom/hothklist?pagesize=30&page=";
    //最近更新
    public static final String PATH_NEW="http://csapi.dm300.com:21889/android/" +
            "search/recentupdate?pagesize=30&page=";
    //分类总接口
    public static final String PATH_CLASSIFY="http://csapi.dm300.com:21889/android/search/category";
    //分类具体接口
    public static final String CLASSIFY_FIRST="http://csapi.dm300.com:21889/android/search/categorylist?cateId=";
    public static final String CLASSIFY_LAST="&pagesize=30&tophot=1&page=";
    //漫画详情接口
    public static final String PATH_CONMIC="http://csapi.dm300.com:21889/android/comic/info?comicsrcid=0&comicid=";
    //评论接口
    public static final String PATH_COMMENT="http://csapi.dm300.com:21889/android/comment/getCommentList?parent_id=0&pagesize=30&page=1&root_id=0&comicid=";
    //章节列表接口
    public static final String PATH_SECTION_FIRST="http://csapi.dm300.com:21889/android/comic/charpterlist?comicsrcid=";
    public static final String PATH_SECTION_LAST="&comicid=";
    //搜索接口
    public static final String PATH_SEARCH="http://csapi.dm300.com:21889/android/search/query?pagesize=30&page=1&keyword=";
    //看漫画接口
    public static final String CONMIC="http://csapi.dm300.com:21889/android/comic/charpterinfo?charpterid=";
}
