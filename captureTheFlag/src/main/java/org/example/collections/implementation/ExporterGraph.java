package org.example.collections.implementation;

import org.example.api.implementation.Local;
import org.example.api.implementation.RouteNetwork;
import org.example.api.interfaces.ILocal;
import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.interfaces.GraphADT;
import org.example.collections.interfaces.IExporter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Classe que implementa a interface IExporter
 */
public class ExporterGraph implements IExporter
{
    /**
     * localização do ficheiro aonde vai ser guardado
     */
    private String fileName;


    /**
     * constructor
     * @param fileName localização do ficheiro aonde vai ser guardado
     */
    public ExporterGraph(String fileName)
    {
        if (fileName == null || fileName.equals(""))
        {
            throw new IllegalArgumentException("fileName name invalid!");
        }

        this.fileName = fileName;
    }


    @Override
    public void setFileName(String fileName)
    {
        if (fileName == null || fileName.equals(""))
        {
            throw new IllegalArgumentException("fileName name invalid!");
        }

        this.fileName = fileName;
    }

    @Override
    public String getFileName()
    {
        return fileName;
    }


    /**
     * Exporta um grafo em .dot e em .png
     *
     * @param graph    grafo a ser exportado
     * @param fileName localização do ficheiro
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    @Override
    public <T> void exportGraph(GraphADT<T> graph, String fileName) throws EmptyCollectionException, InterruptedException
    {
        this.makeDotAndPNG(this.exportGraph(graph), fileName);
    }

    /**
     * Exporta um grafo e uma route em .dot e em .png
     *
     * @param graph         grafo a ser exportado
     * @param routeIterator route a ser notado no grafo
     * @param fileName      localização do ficheiro
     * @throws InterruptedException
     * @throws EmptyCollectionException
     */
    @Override
    public <T> void exportRouteGraph(GraphADT<T> graph, Iterator<T> routeIterator, String fileName) throws InterruptedException, EmptyCollectionException
    {
        this.makeDotAndPNG(this.exportRouteGraph(graph, routeIterator), fileName);
    }


    @Override
    public <T> void exportRoute(Iterator<T> routeIterator, String fileName) throws EmptyCollectionException, InterruptedException
    {
        this.makeDotAndPNG(this.exportRoute(routeIterator), fileName);
    }

    /**
     * Cria um .dot e um .png
     * @param contentString string do conteúdo .dot
     * @param fileName localização do ficheiro aonde vai ser guardado
     * @throws InterruptedException
     */
    private void makeDotAndPNG(String contentString, String fileName) throws InterruptedException
    {
        //fazer e exportar o .dot
        try
        {
            FileWriter fw = new FileWriter(this.fileName + "/" + fileName + ".dot");
            fw.write(contentString);
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }

        //cria um .png baseado no .dot
        String dotFileName = this.fileName + "/" + fileName + ".dot";

        try
        {
            String[] c = {"dot", "-Tpng", dotFileName, "-O"}; //comando a ser executado
            Process p = Runtime.getRuntime().exec(c);
            int err = p.waitFor();
        }
        catch (IOException | InterruptedException e1)
        {
            System.out.println("ERROR: "+e1.getMessage());
        }

        ExporterGraph.showImage(dotFileName + ".png");
    }

    /**
     * Mostra uma imagem do grafo
     * @param s caminho da imagem
     */
    public static void showImage(String s)
    {
        try
        {
            BufferedImage bi = ImageIO.read(new File(s));
            ImageIcon ii = new ImageIcon(bi);

            JFrame frame = new JFrame("Visualization");
            frame.setLayout(new FlowLayout());
            frame.setSize(bi.getWidth() + 100, bi.getHeight() + 100);

            JLabel label = new JLabel();
            label.setIcon(ii);

            frame.add(label);
            frame.setVisible(true);
        }
        catch (IOException e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    /**
     * Fazer a string do .dot
     * @param graph grafo a ser exportado
     * @return a string do .dot
     * @param <T>
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    private <T> String exportGraph(GraphADT<T> graph) throws EmptyCollectionException, InterruptedException
    {
        if(graph.isEmpty())
        {
            throw new EmptyCollectionException("Graph is empty");
        }

        Graph<T> currentGraph = (Graph<T>) graph;
        String content = "strict digraph{\n\tgraph [ordering=\"out\"]";

        if(!graph.isConnected()) //se o grafo não estiver conectado, irá escrever primeiro em todos os vértices
        {
            for(int i=0; i < currentGraph.numVertices - 1; i++)
            {
                if(graph instanceof RouteNetwork)
                {
                    Local local = (Local) currentGraph.vertices[i];
                    content += "\"" + local.getId()  + "\"" + ",";
                }
                else
                {
                    content += "\"" + currentGraph.vertices[i] + "\"" + ",";
                }
            }

            if (graph instanceof RouteNetwork)
            {
                Local local = (Local) currentGraph.vertices[currentGraph.numVertices - 1];
                content += "\"" + local.getId()  + "\"" + ",";
            }
            else
            {
                content += "\"" + currentGraph.vertices[currentGraph.numVertices - 1] + "\"\n";
            }
        }

        //para todos os vértices faz as arestas
        for(int i=0; i < currentGraph.numVertices; i++)
        {
            for(int j=i; j < currentGraph.numVertices; j++)
            {
                if(currentGraph.adjMatrix[i][j] != 0)
                {
                    if(graph instanceof RouteNetwork)
                    {
                        Local local1 = (Local) currentGraph.vertices[i];
                        Local local2 = (Local) currentGraph.vertices[j];

                        content += "\"" + local1.getId() + "\"->" + "\"" + local2.getId() + "\"" + "[arrowhead=none][label=" + currentGraph.adjMatrix[i][j] + "]\n";
                    }
                    else if(currentGraph instanceof Network)
                    {
                        content += "\"" + currentGraph.vertices[i].toString() + "\"->" + "\"" + currentGraph.vertices[j].toString() + "\"" + "[arrowhead=none][label=" + currentGraph.adjMatrix[i][j] + "]\n";
                    }
                    else
                    {
                        content += "\"" + currentGraph.vertices[i].toString() + "\"->" + "\"" + currentGraph.vertices[j].toString() + "\"" + "[arrowhead=none]\n";
                    }
                }
            }
        }

        content += "\n}";

        return content;
    }

    /**
     * Fazer a string das routes do .dot
     * @param graph grafo a ser exportado
     * @param routeIterator route a ser notado no grafo
     * @return a string do .dot
     * @param <T>
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    private <T> String exportRouteGraph(GraphADT<T> graph, Iterator<T> routeIterator) throws EmptyCollectionException, InterruptedException
    {
        String content = this.exportGraph(graph);
        content = content.substring(0, content.length() - 2); //remove os últimos 2 caracteres, o "\n}" final do ficheiro

        //depois de ter o grafo feito, iremos fazer a rota das arestas
        T first = null;
        T second = null;
        RouteNetwork<ILocal> tmpGraph;

        while (routeIterator.hasNext())
        {
            if(first == null) //se for a 1ª vez que entra no ciclo
            {
                first = routeIterator.next();

                if(graph instanceof RouteNetwork)
                {
                    Local local1 = (Local) first;
                    content += "\"" + local1.getId() + "\"[fillcolor=red, style=\"rounded,filled\"]\n";
                }
                else
                {
                    content += "\"" + first.toString() + "\"[fillcolor=red, style=\"rounded,filled\"]\n";
                }
            }
            else //se não for a 1ª vez, irá assumir o valor antigo do second
            {
                first = second;
            }

            if(routeIterator.hasNext())
            {
                second = routeIterator.next();
                Local local1;
                Local local2;

                if(graph instanceof RouteNetwork)
                {
                    tmpGraph = (RouteNetwork<ILocal>) graph;

                    if (tmpGraph.getIndex((ILocal) first) <= tmpGraph.getIndex((ILocal) second))
                    {
                        local1 = (Local) first;
                        local2 = (Local) second;
                    }
                    else
                    {
                        local1 = (Local) second;
                        local2 = (Local) first;
                    }
                    content += "\"" + local1.getId() + "\"->" + "\"" + local2.getId() + "\"" + "[arrowhead=none][color=red][penwidth = 3]\n";
                }
                else
                {
                    content += "\"" + first.toString() + "\"->" + "\"" + second.toString() + "\"" + "[arrowhead=none][color=red][penwidth = 3]\n";
                }
            }
        }

        content += "\n}";

        return content;
    }

    /**
     * Fazer a string do .dot para uma route
     * @param routeIterator route para escrever
     * @return string do .dot para a route
     * @param <T>
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    private <T> String exportRoute(Iterator<T> routeIterator) throws EmptyCollectionException, InterruptedException
    {
        String content = "digraph{\n\tgraph [ordering=\"in\"]";
        int count=0;
        T first = null;
        T second = null;

        while (routeIterator.hasNext())
        {
            if (first == null) //se é a 1ºvez que entra no ciclo
            {
                first = routeIterator.next();
            }
            else //se não for a 1ªvez, assume o valor antigo do second
            {
                first = second;
            }

            if (routeIterator.hasNext())
            {
                count++;
                second = routeIterator.next();

                if (first instanceof ILocal)
                {
                    content += "\"" + ((ILocal) first).getId() + "\"->" + "\"" + ((ILocal) second).getId() + "\"" + "[label=" + count + "]\n";
                }
                else
                {
                    content += "\"" + first.toString() + "\"->" + "\"" + second.toString() + "\"" + "[label=" + count + "]\n";
                }
            }
        }

        content += "\n}";

        return content;
    }
}
