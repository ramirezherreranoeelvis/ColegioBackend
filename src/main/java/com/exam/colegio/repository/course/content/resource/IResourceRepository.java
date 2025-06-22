package com.exam.colegio.repository.course.content.resource;

import com.exam.colegio.model.course.content.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResourceRepository extends JpaRepository<Resource, Integer> {
}
