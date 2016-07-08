package com.gjd.puddingcomic.utils;

/**
 * Created by Administrator on 2016/6/27.
 */
public class URLUtils {

    //热门上边viewpager四张图
    public static final String HOT_VIEWPAGER = "http://api.kuaikanmanhua.com/v1/banners";
    //点击进入http://www.kuaikanmanhua.com/comics/
    public static final String INTO_HOT_VIEWPAGER = "http://www.kuaikanmanhua.com/comics/";
    //热门下边的分类（热气飙升）
//    public static final String HOT_CLASSITY = "http://api.kuaikanmanhua.com/v1/topic_lists/mixed/new";
//    下边的分类：（人气飙升）
    public static final String CLASSITY_UNDER = "http://api.kuaikanmanhua.com/v1/topic_lists/mixed/new";
    //人气飙升点击进入
    public static final String INTO_HOT = "http://api.kuaikanmanhua.com/v1/topic_lists/4";
    //点击推荐进入
    public static final String INTO_RECOMMEND = "http://api.kuaikanmanhua.com/v1/topic_lists/3";
    //章节详情
    public static final String INTO_EVERY = "http://api.kuaikanmanhua.com/v1/topics/";
    //后边拼接的
    public static final String INTO_EVERY_AFTER_URL = "?sort=0";
    //分类每一项
    public static final String CLASSIFY_EVERY = "http://api.kuaikanmanhua.com/v1/topics?offset=0&limit=20&tag=";
    //正是看似是阅读每一章
    public static final String BEGIN_READ = "http://api.kuaikanmanhua.com/v1/comics/";
//  http://api.kuaikanmanhua.com/v1/daily/comic_lists/1466352000?since=0
    //随机的url
    public static final String RANDOM_LIST = "http://api.kuaikanmanhua.com/v1/daily/comic_lists/";
//   拼接随机的后半段网址
    public static final String RANDOM_LIST_AFTER = "?since=0";
}

