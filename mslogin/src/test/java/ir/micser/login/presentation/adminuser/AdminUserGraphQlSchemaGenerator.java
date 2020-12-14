package ir.micser.login.presentation.adminuser;

import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.DefaultOperationBuilder;

/**
 * <b>For testing use only!</b>
 * A schema generator with default configuration useful for testing.
 */
public class AdminUserGraphQlSchemaGenerator extends GraphQLSchemaGenerator {

    private static final String[] basePackages = new String[] {"ir.micser"};

    public AdminUserGraphQlSchemaGenerator() {
        withBasePackages(basePackages);
        withOperationBuilder(new DefaultOperationBuilder(DefaultOperationBuilder.TypeInference.LIMITED));
    }
}

