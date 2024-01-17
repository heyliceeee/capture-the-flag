package org.example.api.interfaces;

/**
 * Interface da coordenada
 */
public interface ICoordenada
{
    /**
     * Retorna a longitude da coordenada
     * @return a longitude da coordenada
     */
    double getLongitude();

    /**
     * Define a longitude da coordenada
     * @param longitude
     */
    void setLongitude(double longitude);

    /**
     * Retorna a latitude da coordenada
     * @return a latitude da coordenada
     */
    double getLatitude();

    /**
     * Define a latitude da coordenada
     * @param latitude
     */
    void setLatitude(double latitude);
}
