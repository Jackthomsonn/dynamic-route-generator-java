package Controllers;

import static spark.Spark.*;

import java.util.List;
import org.bson.Document;
import com.google.gson.Gson;

import Enums.StatusMessage;
import Enums.StatusResponse;
import Interfaces.DatabaseManager;
import spark.Request;
import spark.Response;

public class GenericService
{
    private DatabaseManager database;
    private String collectionName;

    public GenericService( final DatabaseManager database, final String path, final List<String> methods,
                           final String collectionName )
    {
        this.database = database;
        this.collectionName = collectionName;

        this.generateRoutes( path, methods );
    }

    private String save( final Request request, final Response response )
    {
        try
        {
            response.type( "application/json" );

            this.database.save( request.body(), this.collectionName );

            return new Gson()
                    .toJson( new StandardResponse( StatusResponse.SUCCESS, StatusMessage.ADDED_SUCCESSFULLY ) );
        }
        catch ( Exception e )
        {
            return new Gson().toJson( e );
        }
    }

    private String getAll( final Response response )
    {
        try
        {
            response.type( "application/json" );

            List<String> documents = this.database.getAll( this.collectionName );

            return new Gson().toJson( documents );
        }
        catch ( Exception e )
        {
            return new Gson().toJson( e );
        }
    }

    private String getById( final Request request, final Response response )
    {
        try
        {
            final String id = request.params( ":id" );

            response.type( "application/json" );

            Document doc = this.database.get( id, this.collectionName );

            return new Gson().toJson( doc );
        }
        catch ( Exception e )
        {
            return new Gson().toJson( e );
        }
    }

    public void generateRoutes( final String path, final List<String> methods )
    {
        if ( methods.indexOf( "GET" ) != -1 )
        {
            get( path + "/:id", this::getById );
            get( path, ( Request req, Response res ) -> this.getAll( res ) );
        }

        if ( methods.indexOf( "POST" ) != -1 )
        {
            post( path, this::save );
        }
    }
}
