package com.euler.repository;

import com.euler.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void test(){
        //添加新文章
        /*Article article=new Article();
        article.setUserId(1);
        article.setTitle("文章一");
        article.setContent("# 标题\n" +
                "## 序言\n" +
                "段落\n" +
                "### Vue技术栈\n" +
                "1. mavonEditor\n" +
                "2. vuex\n" +
                "### 后端\n" +
                "* springboot\n" +
                "* redis");
        article.setTags("Vue,Springboot");
        System.out.println(articleRepository.saveAndFlush(article));*/

        // 更改文章

        Article article=articleRepository.findById(1).orElse(null);
        if(null==article){
            System.out.println("id为1的文章不存在");
            return;
        }
        article.setTitle("修改后的标题");
        article.setContent("# MarkDown文章\n" +
                "## 序言\n" +
                "段落\n" +
                "### Vue技术栈\n" +
                "1. mavonEditor\n" +
                "2. vuex\n" +
                "### 后端\n" +
                "* springboot\n" +
                "* redis\n" +
                "尾部");
        System.out.println(articleRepository.saveAndFlush(article));
    }
}