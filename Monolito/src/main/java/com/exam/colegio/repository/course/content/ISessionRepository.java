package com.exam.colegio.repository.course.content;

import com.exam.colegio.model.course.content.SessionContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gatomontes
 */
@Repository
public interface ISessionRepository extends JpaRepository<SessionContent, Integer> {

}
