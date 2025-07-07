package com.authms.infrastructure.output.persistence.repository;

import com.authms.infrastructure.output.persistence.entity.AccessEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface IR2dbcAccessRepository extends ReactiveCrudRepository<AccessEntity, String> {

}
