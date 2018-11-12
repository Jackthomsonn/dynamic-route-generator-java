package Controllers;

import static com.mongodb.client.model.Filters.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Interfaces.DatabaseManager;


public class MongoDatabaseManager implements DatabaseManager
{
    private MongoDatabase database;

    public MongoDatabaseManager( String databaseName )
    {
        this.database = new MongoClient().getDatabase( databaseName );
    }

    @Override
    public void save( final String objectToSave, final String collectionName )
    {
        final Type stringStringMap = new TypeToken<Map<String, String>>()
        {
        }.getType();
        final Map<String, String> map = new Gson().fromJson( objectToSave, stringStringMap );
        final Document doc = new Document();

        map.keySet().forEach( key -> doc.append( key, map.get( key ) ) );

        this.database.getCollection( collectionName ).insertOne( doc );
    }

    @Override
    public List<String> getAll( final String collectionName )
    {
        final List<String> list = new ArrayList<>();
        final MongoCollection<Document> collection = database.getCollection( collectionName );

        for ( final Document document : collection.find() )
        {
            list.add( document.toJson() );
        }

        return list;
    }

    @Override
    public Document get( final String id, final String collectionName )
    {
        final MongoCollection<Document> collection = this.database.getCollection( collectionName );

        return collection.find( eq( "id", id ) ).first();
    }
}
