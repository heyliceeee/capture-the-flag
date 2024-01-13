package org.example.api.exceptions;

public class ElementNotFoundException extends Exception
{
    public ElementNotFoundException(String m) {
        super("ERROR: " + m);
    }

}
