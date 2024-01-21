package org.example.api.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.ILocal;
import org.example.collections.implementation.ArrayUnorderedList;
import org.example.collections.interfaces.UnorderedListADT;

import java.util.Iterator;


/**
 * Representacao da classe de um localizaco/bandeira
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
) //indica o tipo de classe que sera definido com base no valor da propriedade type

@JsonSubTypes({
        @JsonSubTypes.Type(value = Localizacao.class, name = "Localizacao"),
        @JsonSubTypes.Type(value = Bandeira.class, name = "Bandeira")
}) //indica quais são as subclasses possiveis (localizacao e bandeira) e como elas são identificadas na propriedade type do JSON
public class Local implements ILocal
{
    /**
     * identificador unico do localizacao/bandeira
     */
    private int id;

    /**
     * tipo de local localizacao/bandeira
     */
    @JsonProperty(required = true)
    private String tipo;


    /**
     * coordenadas do localizacao/bandeira
     */
    private ICoordenada coordenadas;


    /**
     * constructor
     *
     * @param id
     * @param tipo
     * @param coordenadas
     */
    public Local(int id, String tipo, ICoordenada coordenadas)
    {
        this.id = id;
        this.tipo = tipo;
        this.coordenadas = coordenadas;
    }



    @Override
    public String toString()
    {
        return "Local{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", coordenadas=" + coordenadas +
                '}';
    }



    //region GETTERS E SETTERS
    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }


    @Override
    public String getTipo()
    {
        return tipo;
    }

    @Override
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }


    @Override
    public Coordenada getCoordenadas()
    {
        return (Coordenada) coordenadas;
    }

    @Override
    public void setCoordenadas(Coordenada coordenadas)
    {
        this.coordenadas = coordenadas;
    }
    //endregion
}
