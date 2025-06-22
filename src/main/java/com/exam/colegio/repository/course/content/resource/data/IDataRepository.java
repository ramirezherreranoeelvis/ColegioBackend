package com.exam.colegio.repository.course.content.resource.data;

import com.exam.colegio.model.course.content.resource.data.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDataRepository extends JpaRepository<Data, Integer> {
}
