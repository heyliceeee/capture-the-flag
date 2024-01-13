package org.example.collections.exceptions;

public class EmptyCollectionException extends RuntimeException
{
    public EmptyCollectionException(String collectionType)
    {
        super(collectionType + " is empty.");
    }
}
