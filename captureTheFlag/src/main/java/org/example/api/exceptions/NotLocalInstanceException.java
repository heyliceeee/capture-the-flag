package org.example.api.exceptions;

/**
 * Lança exceção quando o elemento em questão deveria ser instânciado {@link org.example.api.interfaces.ILocal local} mas não é
 */
public class NotLocalInstanceException extends Exception
{
    /**
     * construtor sem mensagem.
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
     * construtor com mensagem.
     *
     * @param s mensagem.
     */
    public NotLocalInstanceException(String s) {
        super(s);
    }
}
