package co.com.crediya.r2dbc;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.r2dbc.entity.UserEntity;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, String, ReactiveRepository> implements UserRepository {

    public ReactiveRepositoryAdapter(ReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, userEntity -> mapper.map(userEntity, User.class));
    }
}
