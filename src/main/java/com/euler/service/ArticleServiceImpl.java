package com.euler.service;

import com.euler.domain.Article;
import com.euler.domain.BaseResponse;
import com.euler.domain.UserInfo;
import com.euler.repository.ArticleRepository;
import com.euler.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    final UserInfoRepository userInfoRepository;
    final ArticleRepository articleRepository;

    public ArticleServiceImpl(UserInfoRepository userInfoRepository, ArticleRepository articleRepository) {
        this.userInfoRepository = userInfoRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public BaseResponse getArticleStatisticalInfo() {
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        List<Map<String, Object>> staticsInfoList = userInfoList.stream().map(userInfo -> {
            Map<String, Object> staticsMap = new HashMap<>();
            staticsMap.put("id", userInfo.getUserId());
            staticsMap.put("username", userInfo.getUserName());
            staticsMap.put("articleCount", articleRepository.findAllByUserId(userInfo.getUserId()).size());
            return staticsMap;
        }).collect(Collectors.toList());
        return new BaseResponse(HttpServletResponse.SC_OK, "获取统计信息成功", staticsInfoList);
    }

    @Override
    public BaseResponse getArticles(Integer id) {
        List<Map<String,Object>> respArticleList=
                articleRepository.findAllByUserId(id).stream().map(article->{
                    Map<String, Object> articleMap = new HashMap<>(4);
                    articleMap.put("id",article.getId());
                    articleMap.put("title",article.getTitle());
                    articleMap.put("sentiments",article.getContent());
                    String tags=article.getTags();
                    String[] tagList=tags.split(",");
                    articleMap.put("tags",tagList);
                    return articleMap;
                }).collect(Collectors.toList());
        return new BaseResponse(HttpServletResponse.SC_OK, "获取该用户文章成功",respArticleList);
    }

    @Override
    public BaseResponse uploadNewArticle(String username, String title, String content, List<String> tags) {
        Article article=new Article();
        UserInfo userInfo=userInfoRepository.findUserByUserName(username).get(0);
        if(null==userInfo){
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "用户"+username+"不存在");
        }
        article.setUserId(userInfo.getUserId());
        article.setTitle(title);
        article.setContent(content);
        article.setTags(String.join(",",tags));
        Integer id=articleRepository.saveAndFlush(article).getId();
        return new BaseResponse(HttpServletResponse.SC_OK, "新增文章成功",id);
    }

    @Override
    public BaseResponse deleteArticle(Integer id) {
        Article article=articleRepository.findById(id).orElse(null);
        if(null==article){
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "id为"+id+"的文章不存在");
        }
        articleRepository.delete(article);
        return new BaseResponse(HttpServletResponse.SC_OK, "文章删除成功",id);
    }

    @Override
    public BaseResponse updateArticle(Integer id, String title, String content, List<String> tags) {
        Article article=articleRepository.findById(id).orElse(null);
        if(null==article){
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "id为"+id+"的文章不存在");
        }
        article.setTitle(title);
        article.setContent(content);
        article.setTags(String.join(",",tags));
        articleRepository.saveAndFlush(article);
        return new BaseResponse(HttpServletResponse.SC_OK, "文章修改成功");
    }
}
