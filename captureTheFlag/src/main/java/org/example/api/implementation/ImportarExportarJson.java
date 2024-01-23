package org.example.api.implementation;

import org.example.Demo;
import org.example.api.interfaces.*;
import org.example.collections.exceptions.EmptyCollectionException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class ImportarExportarJson
{
    IRota rota = new Rota(null, null, 0);


    /**
     * retorna "sucesso" se importou os dados do ficheiro JSON com sucesso
     * @param raiz
     * @param local
     * @param nomeFicheiro
     * @return "sucesso" se importou os dados do ficheiro JSON com sucesso
     * @throws IOException
     * @throws ParseException
     */
    public String importarDoFicheiroJSON(IRaiz raiz, ILocal local, String nomeFicheiro) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(nomeFicheiro);
        JSONObject objeto = (JSONObject) parser.parse(reader);



        JSONArray jogadoresArray = (JSONArray) objeto.get("jogadores"); //"jogadores":[]

        try
        {
            for(int i=0; i < jogadoresArray.size(); i++)
            {
                JSONObject jogadores = (JSONObject) jogadoresArray.get(i);

                long numeroBots = (long) jogadores.get("numeroBots");
                String nome = (String) jogadores.get("nome");


                JSONObject bandeira = (JSONObject) jogadores.get("bandeira");

                String tipoBandeira = (String) bandeira.get("tipo");
                String nomeBandeira = (String) bandeira.get("nome");
                long idBandeira = (long) bandeira.get("id");


                JSONObject coordenada = (JSONObject) bandeira.get("coordenadas");
                double latitude = (double) coordenada.get("latitude");
                double longitude = (double) coordenada.get("longitude");


                ICoordenada coordenada1 = new Coordenada(latitude, longitude);
                IBandeira bandeira1 = new Bandeira((int)idBandeira, tipoBandeira, nomeBandeira, coordenada1);



                IJogador jogador = new Jogador(nome, (int)numeroBots, bandeira1);

                raiz.adicionarJogador(jogador);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERRO: "+e.getMessage());
        }


        JSONArray botsArray = (JSONArray) objeto.get("bots"); //"bots":[]

        try
        {
            for(int i=0; i < botsArray.size(); i++)
            {
                JSONObject bots = (JSONObject) botsArray.get(i);

                String nome = (String) bots.get("nome");
                String nomeJogador = (String) bots.get("nomeJogador");
                String algoritmoMovimento = (String) bots.get("algoritmoMovimento");


                JSONObject coordenada = (JSONObject) bots.get("coordenadas");
                double latitude = (double) coordenada.get("latitude");
                double longitude = (double) coordenada.get("longitude");


                ICoordenada coordenada1 = new Coordenada(latitude, longitude);


                IBot bot = new Bot(nome, nomeJogador, coordenada1, algoritmoMovimento);

                raiz.adicionarBot(bot);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERRO: "+e.getMessage());
        }



        JSONArray rotasArray = (JSONArray) objeto.get("rotas"); //"rotas":[]

        try
        {
            for(int i=0; i < rotasArray.size(); i++)
            {
                JSONObject rotas = (JSONObject) rotasArray.get(i);

                long de = (long) rotas.get("de");
                long para = (long) rotas.get("para");
                double peso = (double) rotas.get("distancia");

                try
                {
                    ILocal deLocal = raiz.getLocalByID((int) de); //localizacao/bandeira correspondente ao id
                    ILocal paraLocal = raiz.getLocalByID((int) para); //localizacao/bandeira correspondente ao id

                    Demo.raiz.adicionarRota(deLocal, paraLocal, peso);
                }
                catch (IllegalArgumentException | EmptyCollectionException e)
                {
                    System.out.println("ERRO: "+e.getMessage());
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERRO: "+e.getMessage());
        }


        JSONArray locaisArray = (JSONArray) objeto.get("locais"); //"locais":[]

        try
        {
            for(int i=0; i < locaisArray.size(); i++)
            {
                JSONObject local1 = (JSONObject) locaisArray.get(i); //{...}

                int id = (int) local1.get("id");
                String tipo = (String) local1.get("tipo");

                JSONObject coordenadas = (JSONObject) local1.get("coordenadas");
                double longitudeCoordenada = (double) coordenadas.get("longitude");
                double latitudeCoordenada = (double) coordenadas.get("latitude");

                ICoordenada coordenadas1 = new Coordenada(longitudeCoordenada, latitudeCoordenada);


                if(tipo.equals("Localizacao"))
                {
                    String nome = (String) local1.get("nome");

                    ILocalizacao localizacao = new Localizacao(id, tipo, nome, coordenadas1);

                    raiz.adicionarLocal(localizacao); //adicionar a localizacao á raiz
                }
                else if(tipo.equals("Bandeira"))
                {
                    String nome = (String) local1.get("nome");

                    IBandeira bandeira = new Bandeira(id, tipo, nome, coordenadas1);

                    raiz.adicionarLocal(bandeira); //adicionar a bandeira á raiz
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERRO: "+e.getMessage());
        }



        JSONArray mapaArray = (JSONArray) objeto.get("mapa"); //"mapa":[]

        try
        {
            for(int i=0; i < mapaArray.size(); i++)
            {
                JSONObject mapaObjecto = (JSONObject) mapaArray.get(i);

                long qttLocExistentes = (long) mapaObjecto.get("qttLocExistentes"); //quantidade de localizacoes existentes
                String tipoCaminho = (String) mapaObjecto.get("tipoCaminho"); //tipo caminho
                long densidadeArestas = (long) mapaObjecto.get("densidadeArestas"); //densidade arestas

                IMapa mapa1 = new Mapa((int) qttLocExistentes, tipoCaminho, (int) densidadeArestas);

                raiz.adicionarMapa(mapa1);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERRO: "+e.getMessage());
        }



        return "Sucesso";
    }


    /**
     * exporta os dados do jogo para ficheiro JSON
     * @param paraJSONString
     * @param nome
     */
    public static void exportarParaFicheiroJSON(String paraJSONString, String nome) throws IOException
    {
        if (paraJSONString == null || paraJSONString.equals("") || nome == null || nome.equals(""))
        {
            throw new IllegalArgumentException("nao consegue enviar parametros nulos ou vazios!");
        }

        try (FileWriter file = new FileWriter("docs/export" + "/" + nome + ".json"))
        {
            file.write(paraJSONString);
        }
        catch (IOException exception)
        {
            throw new IOException("erro enquanto estava a escrever no ficheiro!");
        }
    }
}
