package com.euler.repository;

import com.euler.domain.Article;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 文章表访问类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/20
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    /**
     * 保存文章
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends Article> S saveAndFlush(S entity);

    /**
     * 根据文章id查找文章
     *
     * @param integer
     * @return
     */
    @Override
    Optional<Article> findById(Integer integer);

    /**
     * 根据用户id查找所有的文章
     *
     * @param userId 用户id
     * @return
     */
     List<Article> findAllByUserId(Integer userId);

//    @Query("select ui.user_id,ui.user_name,count",nativeQuery = true)
//    Map<String,Object> findCount()
}
