package org.example.api.implementation;

import org.example.api.interfaces.ICoordenada;

/**
 * Representacao da classe de uma coordenada
 */
public class Coordenada implements ICoordenada
{
    /**
     * longitude da coordenada
     */
    private double longitude;

    /**
     * latitude da coordenada
     */
    private double latitude;


    public Coordenada(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }



    /**
     * retorna a longitude da coordenada
     *
     * @return a longitude da coordenada
     */
    @Override
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * define a longitude da coordenada
     *
     * @param longitude
     */
    @Override
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * retorna a latitude da coordenada
     *
     * @return a latitude da coordenada
     */
    @Override
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * define a latitude da coordenada
     *
     * @param latitude
     */
    @Override
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
