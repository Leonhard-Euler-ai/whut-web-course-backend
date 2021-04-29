package com.euler.controller;

import com.euler.domain.BaseResponse;
import com.euler.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 用户文章统计
     *
     * @return
     */
    @GetMapping("/api/getArticleStatisticalInfo")
    @ResponseBody
    public BaseResponse getArticleStatisticalInfo() {
        return articleService.getArticleStatisticalInfo();
    }

    /**
     * 获取用户id对应的所有文章
     *
     * @param id
     * @return
     */
    @PostMapping("/api/getArticles")
    @ResponseBody
    public BaseResponse getArticles(@RequestParam Integer id) {
        return articleService.getArticles(id);
    }

    /**
     * 上传新文章
     *
     * @param username
     * @param title
     * @param content
     * @param tags
     * @return
     */
    @PostMapping("/api/uploadNewArticle")
    @ResponseBody
    public BaseResponse uploadNewArticle(@RequestParam String username, @RequestParam String title,
                                         @RequestParam String content, @RequestParam List<String> tags) {
        return articleService.uploadNewArticle(username, title, content, tags);
    }

    /**
     * 更改文章
     *
     * @param id      文章id
     * @param title
     * @param content
     * @param tags
     * @return
     */
    @PostMapping("/api/updateArticle")
    @ResponseBody
    public BaseResponse updateArticle(@RequestParam Integer id, @RequestParam String title,
                                         @RequestParam String content, @RequestParam List<String> tags) {
        return articleService.updateArticle(id, title, content, tags);
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @PostMapping("/api/deleteArticle")
    @ResponseBody
    public BaseResponse deleteArticle(@RequestParam Integer id) {
        return articleService.deleteArticle(id);
    }
}
