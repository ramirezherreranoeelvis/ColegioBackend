package com.exam.colegio.repository.course.content;

import com.exam.colegio.model.course.content.AuxiliaryContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gatomontes
 */
@Repository
public interface IAuxiliaryContentRepository extends JpaRepository<AuxiliaryContent, Integer> {

}
