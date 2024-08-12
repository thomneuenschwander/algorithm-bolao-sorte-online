# algorithm-bolao-sorte-online

Aplicacao para testar os algoritimos dos filtros do app 'Bolao da Sorte Online'.

## CLI - Bolao da Sorte

Use o comando abaixo para executar a aplicação a partir do arquivo JAR

```bash
java -jar cli-bolao-sorte.jar [options]
```

### Opções CLI

- `-n <lotteryName>`: Define o nome da loteria. ---> `"quina"` ou `"megasena"`
- `-g <groups>`: Define os grupos de números, separados por ponto e vírgula.
- `-r`: Imprime as combinções que foram filtradas. Os jogos gerados do bolão.
- `-rc <randomChoice>`: Define uma quantidade de jogos aleatórios entre as combinções filtradas.

### Filters

- `-e <edgeNumber>`: Define a quantidade de numeros borda em uma combinções.
- `-p <removePrimes>`: Remove números primos (`true` ou `false`).

#### Mini-Quadrants Filters

- `-emq <exclusivesMiniQuadrants>`: Deixa apenas os combinções com numeros de diferentes mini-quadrantes. (`true` ou `false`).
- `-smq <sharedMiniQuadrants>`: Deixa apenas os combinções com exatos 2 numeros em um mini-quadrantes. (`true` ou `false`).

#### Four-Quadrants Filters

- `-cqn <coverageQuadrantNumber>`: Define o número de quadrantes de cobertura. Ou seja, remove combinções com numeros que nao estao distribuidos na quantidade de quadrantes passada.  `[1..4]`
- `-sfq <specificFourQuadrants>`: Define o número dos quatro quadrantes específicos para a cobertura.
- `-qom <quadrantOccurrenceMap>`: Define o número dos quatro quadrantes específicos para a cobertura e quantos numeros devem aparecer em cada quadrante.

#### Column Filter

- `-col <columns>`: Define o número de sequencias de colunas de tamanho `y`.
- `-y <y>`: Quantidade de numeros na(s) sequencia(s) de coluna(s).

#### Row Filter

- `-row <rows>`: Define o número de sequencias de linhas de tamanho.
- `-x <x>`: Quantidade de numeros na(s) sequencia(s) de linhas(s).

## Exemplos de uso


### 1. Gerar apenas combinações, sem passar filtros

```bash
java -jar cli-bolao-sorte.jar -n "megasena" -g "1,2,3;4,5,6;7,8,9;10,11,12;13,14,15;16,17,18"
```

### 2. Filtrar combinações com numeros primos e escolher aleatoriamente 20 jogos

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60,13,72,18;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,73,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,44,80,69,71,42,74,57" --primes true -rc 20
```

### 3. Filtrar combinações que nao possuem 2 numeros na borda da tabela, alem de deixar apenas combinações com numeros pertencentes a um unico miniquadrante

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60,13,72,18;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" --exclusives-mini-quadrants true --edge 2" --primes true -rc 20
```

### 4. Filtrar combinações que nao possuem 2 numeros na borda da tabela, alem de deixar apenas combinações com numeros pertencentes a um unico miniquadrante

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60,13,72,18;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" --exclusives-mini-quadrants true --edge 2" --primes true -rc 20
```

### 5. Deixar apenas combinações com 2 sequencias de colunas com 2 números e 1 sequencia de linhas com 3. Depois, escolher 1 jogo aleatoriamente

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" -col 2 -y 2 -row 1 -x 3 -rc 1
```

### 6. Deixar apenas combinações com 3 numeros de borda e números não primos distribuidos em 3 quadrantes diferentes

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" -e 3 -p true --coverage-quadrant-number 3
```

### 7. Deixar apenas combinações números não primos distribuidos especificamente nos quadrante 1 e 3

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" -p true --specific-four-quadrants "1,3"
```

### 8. Deixar apenas combinações com 1 numero no qudrante 1 e 2 numeros no quadrante 3 e 2 numeros no quadrante 4. Depois, é escolhido um jogo resultante

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" --quadrant-occurrence-map "1,1;3,2;4,2" --random-choice 1
```

### 9. Deixar apenas combinações com números não primos, 3 números borda, 1 aparição de 2 numeros de um mesmo mini-quadrante, com numeros distribuidos nos 4 quadrantes, formando um sequencia com 2 numeros na mesma linha. Depois, é escolhido um jogo resultante 

```bash
java -jar cli-bolao-sorte.jar -n "quina" -g "12,34,67,8,19,28,45,23,39,2,78,51,4,32,63,7;15,77,3,68,49,31,24,55,10,43,5,29,60;21,14,6,59,35,20,9,79,26,47,1,38,75,48,56,64;25,53,11,37,70,41,62,40,52,30,58,76,16,50,66;33,17,61,46,36,22,27,74,57" -p true -e 3 -smq true -cqn 4 -col 1 -y 2 -rc 1
```

## Download

```bash
cd algorithm-bolao-sorte-online

java --version

javac -d bin src/Main.java 

jar cfm cli-bolao-sorte.jar MANIFEST.MF -C bin .
```
