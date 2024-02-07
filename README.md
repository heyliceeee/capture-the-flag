<img align="right" src="https://visitor-badge.laobi.icu/badge?page_id=heyliceeee.capture-the-flag" />

# Jogo "Capture the Flag"
## 📌 Resumo
### Em cada partida
  - ☑️ gera um novo mapa random (cria/importa ficheiro, e depois exporta ficheiro), onde os 2 jogadores acordam:
    - ✅ quantidade de localizações existentes no mapa (nodes; ex: áreas onde os jogadores podem movimentarem-se)
    - ✅ tipo de caminho que serão gerados (ex: bidirecional ou direcionado)
    - ✅ densidade das arestas (ex: 50% (num grafo direcionado indica que N*(N-1)**0.5 arestas devem estar presentes; num grafo bidirecionado indica que (N*(N-1)*0.5)/2))
    - ✅ random distâncias entre cada ponto (1km - 15km)
      
 - depois do mapa ser definido
   - ✅ os 2 jogadores selecionam a localização das bandeiras no mapa (têm que ser localizações diferentes; tem que guardar para depois ser possivel exportar ficheiro)
   - ✅ definem o número de bots que cada jogador terá a sua disponibilidade
     - ✅ atribuir a cada bot, um algoritmo (ex: caminho + curto, caminho + longo, ...; só pode permitir a repetição de algoritmos apenas se o número de bots ultrapassar o número disponível de algoritmos; criar >= 3 algortimos)

### Quando inicia a partida
 - ✅ o jogador que irá começar é random
 - ✅ os bots deverão estar localizados na mesma posição que a bandeira do seu jogador

### Em cada ronda, alternando pelos jogadores
 - ✅ um dos bots deverá movimentar-se
 - ✅ os bots devem adotar uma abordagem em que cada um, á vez, move-se de acordo com uma lógica sequencial, assegurando que cada bot tenha a sua oportunidade de avançar, sem a repetição imediata de movimentos, proporcionando assim uma distribuição equitativa das ações durante o jogo (ex: num jogo c 3 bots, joga o bot 1, dps o 2, seguindo do 3. Na 4º ronda, deve voltar ao bot 1).

### Executando na localização das bandeira
 - ☑️ um bot não se pode movimentar para uma posição em que esteja outro bot

### Jogo termina
 - ✅ quando um dos bots chega ao campo do inimigo
  
___
## 🧩 Interfaces
### Menu Inicial
![Menu Inicial](https://media.discordapp.net/attachments/697858645559476265/1202632805708726282/image.png?ex=65ce2a14&is=65bbb514&hm=f4ce3cb7ff05bdb06f07a5000e057449a9babeabf9967f92b6f5d8428fe17fa7&=&format=webp&quality=lossless)
![Menu Inicial](https://media.discordapp.net/attachments/697858645559476265/1202633064061341696/image.png?ex=65ce2a52&is=65bbb552&hm=4bc53a38d177d6e4af8c962f36ddb13a97698751c84fa072bb3d7fdf5954cf42&=&format=webp&quality=lossless)
<br></br>

### Criar Mapa (para cada jogador)
![Criar Mapa](https://media.discordapp.net/attachments/697858645559476265/1202633551703449630/image.png?ex=65ce2ac6&is=65bbb5c6&hm=a8c72625c94be5819dfb93648b0da0fd11cf2dda58c5c441b9b53edfcb185f66&=&format=webp&quality=lossless)
<p>⚠️ Ao definir a densidade das arestas, se introduzir um valor maior que 20, o programa não funciona</p>
<br></br>

### Escolher a localização da bandeira (para cada jogador)
![Escolher Localização da Bandeira](https://media.discordapp.net/attachments/697858645559476265/1202634754873237554/image.png?ex=65ce2be5&is=65bbb6e5&hm=88ffd18aa4570223a55a17d87b4a46c11f978158ed907a8e29d241025cb99cf4&=&format=webp&quality=lossless)
<br></br>

### Escolher o número de bots (para cada jogador)
![Escolher número bots](https://media.discordapp.net/attachments/697858645559476265/1202634891196375071/image.png?ex=65ce2c05&is=65bbb705&hm=b31747ab78d2e028790443221c713927587346d513bab0af2dc31ae988db3794&=&format=webp&quality=lossless)
<br></br>

### Escolher os algortimos para os bots (para cada jogador)
![Escolher o algortimo para cada bot](https://media.discordapp.net/attachments/1167228808735228016/1198922296094621706/image.png?ex=65c9e4e6&is=65b76fe6&hm=d64f1eecff8d2bba51291920e67dd5b3423f07362a9f05fa0196a90ae5eb6b21&=&format=webp&quality=lossless)
<br></br>

### Grafo
![Grafo](https://media.discordapp.net/attachments/697858645559476265/1204752096235094037/image.png?ex=65d5dfd2&is=65c36ad2&hm=43c1a203a729203faaa24d9f6cda805e4b80440864c8940cf0fb6429ff2ebc23&=&format=webp&quality=lossless)
<br></br>

### Partida
![Partida](https://media.discordapp.net/attachments/1167228808735228016/1199498565639213169/image.png?ex=65cbfd97&is=65b98897&hm=65ce749e4991d29d24b1e11ceea9c260c02d421ecbcf7b0ae4c7e45da1660684&=&format=webp&quality=lossless)
<br></br>

___
## ➕ Extras
- 🪲 Testes Unitários
- 🖼️ Interface Gráfica && Visualização do Grafo

___
## 🤝 Contribuições

Feito com 💜 e com ☕ por:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/heyliceeee">
        <img src="https://github.com/heyliceeee.png" width="100px;" alt="Foto da Alice Dias no GitHub"/><br>
        <sub>
          <b>Alice Dias</b>
        </sub>
      </a>
    </td>

<td align="center">
      <a href="https://github.com/SandroCunha8200785">
        <img src="https://github.com/SandroCunha8200785.png" width="100px;" alt="Foto do Sandro Cunha no GitHub"/><br>
        <sub>
          <b>Sandro Cunha</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
