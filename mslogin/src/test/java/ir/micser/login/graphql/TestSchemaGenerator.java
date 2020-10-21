package ir.micser.login.graphql;

import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.DefaultOperationBuilder;

/**
 * <b>For testing use only!</b>
 * A schema generator with default configuration useful for testing.
 */
public class TestSchemaGenerator extends GraphQLSchemaGenerator {

    private static final String[] basePackages = new String[] {"com.motaharinia"};

    public TestSchemaGenerator() {
        withBasePackages(basePackages);
        withOperationBuilder(new DefaultOperationBuilder(DefaultOperationBuilder.TypeInference.LIMITED));
    }
}

