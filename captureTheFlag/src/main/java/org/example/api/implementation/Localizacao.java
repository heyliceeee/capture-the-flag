package org.example.api.implementation;

import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.IInteracao;
import org.example.api.interfaces.ILocalizacao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class Localizacao extends Local implements ILocalizacao, Comparable<Localizacao>
{
    /**
     * nome da localizacao
     */
    private String nome;


    /**
     * instancia da classe de importar e exportar JSON
     */
    static ImportarExportarJson iEJson = new ImportarExportarJson();



    public Localizacao(int id, String tipo, String nome, ICoordenada coordenadas)
    {
        super(id, tipo, coordenadas);
        this.nome = nome;
    }



    /**
     * retorna o array de json das interacoes
     * @return o array de json das interacoes
     */
    private JSONArray getInteracoesArrayJson()
    {
        JSONArray interacoesArray = new JSONArray();
        Iterator<IInteracao> iteratorInteracao = this.interacoes.iterator();

        while (iteratorInteracao.hasNext())
        {
            interacoesArray.add(iteratorInteracao.next().interacaoParaObjetoJson());
        }

        return interacoesArray;
    }


    @Override
    public void exportInteracoesParaJson() throws IOException
    {
        iEJson.exportarParaFicheiroJSON(getInteracoesArrayJson().toJSONString(), "Interacoes");
    }


    @Override
    public JSONObject localizacaoParaObjetoJSON()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("id", getId());
        raiz.put("tipo", getTipo());
        raiz.put("name", getNome());
        raiz.put("coordenadas", getCoordenadas());
        raiz.put("interacao", getListaInteracoes());

        return raiz;
    }
/*

    private JSONObject getCoordenadasObjetoJSON()
    {
        JSONObject coordenadas = new JSONObject();

        try
        {
            coordenadas.put("longitude", this.coordenadas.getLongitude());

        } catch (Exception e)
        {
            coordenadas.put("longitude", 0);
        }

        try
        {
            coordenadas.put("latitude", this.coordenadas.getLatitude());

        } catch (Exception e)
        {
            coordenadas.put("latitude", 0);
        }

        return coordenadas;
    }*/



    @Override
    public String toString()
    {
        return "Localizacao{" +
                "nome='" + nome + '\'' +
                ", " + super.toString() +
                '}';
    }



    //region GETTERS AND SETTERS

    @Override
    public String getNome()
    {
        return nome;
    }

    @Override
    public void setNome(String nome)
    {
        if (nome == null || nome.equals(""))
        {
            throw new IllegalArgumentException("Nome nao pode ser vazio ou nulo!");
        }

        this.nome = nome;
    }

    /**
     * define o nome do bot do jogador de interação com a localizacao
     *
     * @param id      identificador único da interação
     * @param nomeBot novo nome do bot do jogador de interação
     */
    @Override
    public void setInteracaoBot(int id, String nomeBot)
    {

    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Localizacao o)
    {
        return this.getNome().compareTo(o.getNome());
    }

    //endregion
}
