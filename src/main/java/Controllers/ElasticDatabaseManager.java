package Controllers;

import java.util.List;
import org.bson.Document;

import Interfaces.DatabaseManager;

public class ElasticDatabaseManager implements DatabaseManager
{
    private Object database;

    public ElasticDatabaseManager( String database )
    {
        this.database = database;
    }

    @Override
    public void save( final String objectToSave, final String collectionName )
    {

    }

    @Override
    public List<String> getAll( final String collectionName )
    {
        return null;
    }

    @Override
    public Document get( final String id, final String collectionName )
    {
        return null;
    }
}
