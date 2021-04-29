package com.euler.service;

import com.euler.domain.BaseResponse;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
public interface ArticleService {
    /**
     * 获取用户文章统计数据
     *
     * @return http响应
     */
    BaseResponse getArticleStatisticalInfo();

    /**
     * 获取用户id对应的所有文章
     *
     * @param id 用户id
     * @return http响应
     */
    BaseResponse getArticles(Integer id);

    /**
     * 根据用户名保存新文章
     *
     * @param username 用户名
     * @param title    文章标题
     * @param content  文章内容
     * @param tags     文章标签
     * @return http响应
     */
    BaseResponse uploadNewArticle(String username, String title, String content, List<String> tags);


    /**
     * 根据文章id删除文章
     *
     * @param id 文章id
     * @return http响应
     */
    BaseResponse deleteArticle(Integer id);

    /**
     * 根据文章id更改文章
     *
     * @param id      文章id
     * @param title   文章标题
     * @param content 文章内容
     * @param tags    文章标签
     * @return http响应
     */
    BaseResponse updateArticle(Integer id, String title, String content, List<String> tags);

}
