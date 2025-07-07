package com.authms.application.port.output;

import com.authms.domain.User;
import reactor.core.publisher.Mono;
public interface UserRepository {

      Mono<User> save(User user);

}
