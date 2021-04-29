package com.euler.service;

import com.euler.repository.ArticleRepository;
import com.euler.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
@SpringBootTest
class ArticleServiceImplTest {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    public void test() {
//        articleService.getArticleStatisticalInfo();
//        articleService.getArticles(1);

//        String content= "## 序言\n" +
//                "段落\n" +
//                "### Vue技术栈\n" +
//                "1. mavonEditor\n" +
//                "2. vuex\n" ;
//        List<String> tags=new ArrayList<>(Arrays.asList("Vue","CSS"));
//        articleService.uploadNewArticle("李航飞","文章3",content,tags);

        //        articleService.getArticles(1);

//        String content= "## 文章2\n" +
//                "段落\n" +
//                "### Vue技术栈\n" +
//                "1. mavonEditor\n" +
//                "2. vuex\n" ;
//        List<String> tags=new ArrayList<>(Arrays.asList("Article"));
//        articleService.updateArticle(2,"文章2",content,tags);

        //删除文章
        articleService.deleteArticle(7);
    }

}