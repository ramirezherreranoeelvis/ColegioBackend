package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssistantRepository extends JpaRepository<Assistant, Integer> {

}
