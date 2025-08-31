package co.com.crediya.r2dbc;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.r2dbc.entity.UserEntity;
import co.com.crediya.r2dbc.exception.UserAlreadyExistsException;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<User, UserEntity, UUID, UserReactiveRepository> implements UserRepository {

    private final TransactionalOperator operator;

    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper, TransactionalOperator operator) {
        super(repository, mapper, userEntity -> mapper.map(userEntity, User.class));
        this.operator = operator;
    }

    @Override
    public Mono<User> saveUser(User user) {
        return this.findUserByEmail(user.getEmail())
                .flatMap(userExisting -> Mono.error(new UserAlreadyExistsException()))
                .cast(User.class)
                .switchIfEmpty(Mono.defer(() -> super.save(user).as(operator::transactional)));

    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        return this.repository.findUserEntityByEmail(email).map(this::toEntity);
    }
}
