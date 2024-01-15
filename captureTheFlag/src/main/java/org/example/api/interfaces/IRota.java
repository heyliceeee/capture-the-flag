package org.example.api.interfaces;


import java.util.Comparator;

/**
 * Interface da rota
 */
public interface IRota<T>
{
    /**
     * retorna o localizacao/bandeira de partida da rota
     * @return o o localizacao/bandeira de partida da rota
     */
    T getDe();

    /**
     * retorna o localizacao/bandeira de chegada da rota
     * @return o localizacao/bandeira de chegada da rota
     */
    T getPara();

    /**
     * retorna o peso entre o localizacao/bandeira de partida e o localizacao/bandeira de chegada
     * @return o peso
     */
    double getPeso();


    /**
     * define a peso entre 2 pontos
     * @param peso
     */
    void setPeso(int peso);

    /**
     * calcula o peso entre os 2 pontos apartir das coordenadas dos 2 pontos
     * peso = raizQuadrada((x2-x1)^2 + (y2-y1)^2)
     * @param x1 coordenadas do ponto de partida
     * @param y1 coordenadas do ponto de partida
     * @param x2 coordenadas do ponto de chegada
     * @param y2 coordenadas do ponto de chegada
     */
    double calcularPesoDasCoordenadas(double x1, double y1, double x2, double y2);


    /**
     * comparar o peso entre 2 rotas
     */
    Comparator<IRota> PESO = new Comparator<>()
    {
        @Override
        public int compare(IRota o1, IRota o2)
        {
            int compara = 0;

            if(o1.getPeso() > o2.getPeso())
            {
                compara = (int) (o1.getPeso() - o2.getPeso());
            }
            else if(o2.getPeso() > o1.getPeso())
            {
                compara = (int) (o2.getPeso() - o1.getPeso());
            }
            else
            {
                compara = (int) (o1.getPeso() - o2.getPeso());
            }

            return compara;
        }
    };
}
