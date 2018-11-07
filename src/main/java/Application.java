import static spark.Spark.*;

import java.util.Arrays;

import Controllers.GenericService;
import Controllers.MongoDatabaseManager;
import Interfaces.DatabaseManager;

public class Application
{
    public static void main( final String[] args )
    {
        final DatabaseManager mongoDatabaseManager = new MongoDatabaseManager( "test-db-api" );
        // final DatabaseManager elasticDatabaseManager = new ElasticDatabaseManager( "test-db-api" );

        port( 9090 );

        new GenericService(
                mongoDatabaseManager,
                "/users",
                Arrays.asList( "GET", "POST" ),
                "Users"
        );
    }
}