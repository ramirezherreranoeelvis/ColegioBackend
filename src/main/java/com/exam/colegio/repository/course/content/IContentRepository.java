package com.exam.colegio.repository.course.content;

import com.exam.colegio.model.course.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gatomontes
 */
@Repository
public interface IContentRepository extends JpaRepository<Content, Integer> {

        
}
