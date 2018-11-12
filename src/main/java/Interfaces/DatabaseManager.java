package Interfaces;


import java.util.List;
import org.bson.Document;

public interface DatabaseManager
{
    default void save( final String objectToSave, final String collectionName )
    {
    }

    default List<String> getAll( String collectionName )
    {
        return null;
    }

    default Document get( final String id, String collectionName )
    {
        return null;
    }
}
