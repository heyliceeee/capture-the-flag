package org.example.api.implementation;

import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.IInteracao;
import org.example.api.interfaces.ILocalizacao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class Bandeira extends Local implements IBandeira
{
    /**
     * nome da bandeira
     */
    private String nome;


    /**
     * instancia da classe de importar e exportar JSON
     */
    static ImportarExportarJson iEJson = new ImportarExportarJson();



    public Bandeira(int id, String tipo, String nome, ICoordenada coordenadas)
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
    public JSONObject bandeiraParaObjetoJSON()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("id", getId());
        raiz.put("tipo", getTipo());
        raiz.put("nome", this.nome);
        raiz.put("coordenadas", getCoordenadas());
        raiz.put("interacao", getInteracoesArrayJson());

        return raiz;
    }


    /*private JSONObject getCoordenadasObjetoJSON()
    {
        JSONObject coordenadas = new JSONObject();

        coordenadas.put("longitude", this.coordenadas.getLongitude());
        coordenadas.put("latitude", this.coordenadas.getLatitude());

        return coordenadas;
    }*/



    @Override
    public String toString()
    {
        return "Bandeira{" +
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
     * define o nome do bot do jogador de interação com a bandeira
     *
     * @param id      identificador único da interação
     * @param nomeBot novo nome do bot do jogador de interação
     */
    @Override
    public void setInteracaoBot(int id, String nomeBot)
    {

    }

    /**
     * @param id     identificador único da interação
     * @param pontos os pontos de interação
     */
    @Override
    public void setPontosInteracao(int id, int pontos)
    {}

    //endregion
}
