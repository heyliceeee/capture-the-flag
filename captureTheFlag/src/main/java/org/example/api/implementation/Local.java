package org.example.api.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.ILocal;
import org.example.collections.implementation.ArrayUnorderedList;

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
     * registo de interacoes do localizacao/bandeira
     */
    public UnorderedListADT<IInteracao> interacoes = new ArrayUnorderedList<>();


    /**
     * constructor
     *
     * @param id
     * @param tipo
     * @param coordenada
     */
    public Local(int id, String tipo, ICoordenada coordenada)
    {
        this.id = id;
        this.tipo = tipo;
        this.coordenadas = coordenada;
    }


    @Override
    public String adicionarInteracao(IInteracao interacao)
    {
        if(interacao == null)
        {
            throw new IllegalArgumentException("interacao nao pode ser nulo");
        }

        String s = "Falhou";

        if(this.interacoes.isEmpty() || !this.interacoes.contains(interacao)) //se a lista de interacoes estiver vazia ou não conter o interacao a ser adicionado, adiciona-o á lista
        {
            this.interacoes.addToRear(interacao); //adiciona o interacao no fim da lista
            s = "Sucesso";
        }

        return s;
    }


    @Override
    public String getListaInteracoes()
    {
        String s = "Interacoes: {\n";

        if(!this.interacoes.isEmpty())
        {
            Iterator<IInteracao> iterator = interacoes.iterator();

            while (iterator.hasNext())
            {
                s += iterator.next().toString() + "\n";
            }
        }
        else
        {
            s += "nao existe interacoes na lista!\n";
        }

        s += "}";

        return s;
    }


    @Override
    public IInteracao getInteracaoPorID(int id)
    {
        Iterator<IInteracao> iterator = this.interacoes.iterator();
        IInteracao interacao;

        while (iterator.hasNext())
        {
            interacao = iterator.next();

            if(interacao.getID() == id)
            {
                return interacao;
            }
        }

        return null;
    }



    @Override
    public int getIDUltimaInteracao()
    {
        int idUltimaInteracao = 0;

        Iterator<IInteracao> iterator = this.interacoes.iterator();
        IInteracao interacao;

        while (iterator.hasNext())
        {
            interacao = iterator.next();

            if(interacao.getID() > idUltimaInteracao)
            {
                return idUltimaInteracao;
            }
        }


        return idUltimaInteracao;
    }


    @Override
    public String toString()
    {
        return "Local{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", coordenadas=" + coordenadas +
                ", interacoes=" + interacoes +
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


    @Override
    public void setTipoInteracao(int id, String tipo)
    {}

    /**
     * define o bot do jogador que fez a interacao
     *
     * @param id      da (bandeira/localizacao)
     * @param nomeBot bot (jogador1 ou jogador2)
     */
    @Override
    public void setBotInteracao(int id, String nomeBot)
    {

    }

    //endregion
}
