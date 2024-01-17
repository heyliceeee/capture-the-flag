package org.example.api.exceptions;

public class ElementAlreadyExistsException extends Exception
{

    /**
     * Constructor sem mensagem
     */
    public ElementAlreadyExistsException()
    {
        super();
    }

    /**
     * Constructor sem mensagem
     * @param s mensagem
     */
    public ElementAlreadyExistsException(String s)
    {
        super(s);
    }
}
