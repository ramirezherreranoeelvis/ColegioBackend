package com.exam.colegio.repository.course.content.resource.activity;

import com.exam.colegio.model.course.content.resource.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Integer> {

}
