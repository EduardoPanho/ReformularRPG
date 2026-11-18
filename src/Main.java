
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void mostrarInicio() {
        System.out.println("*******************");
        System.out.println("     JOGO RPG      ");
        System.out.println("*******************");
    }

    public static int escolhaPersonagem(Scanner sc) {

        int opcaoClasse;

        do {
            System.out.println("Escolha sua classe:");
            System.out.println("1- Guerreiro");
            System.out.println("2- Arqueiro");
            System.out.println("3- Shaman");
            System.out.println("4- Mago");

            opcaoClasse = sc.nextInt();

        } while (opcaoClasse <= 0 || opcaoClasse >= 5);

        return opcaoClasse;
    }

    public static int sortearDadoD20(Random random) {
        return random.nextInt(20) + 1;
    }

    public static int explorar(Random random) {

        int sorteio = random.nextInt(100) + 1;

        if (sorteio <= 40) {
            return 1;
        } else if (sorteio <= 60) {
            return 2;
        } else if (sorteio <= 95) {
            return 3;
        } else {
            return 4;
        }
    }

    public static void mostrarMenuJogo() {
        System.out.println("\nInicio da jornada");
        System.out.println("1- Explorar");
        System.out.println("2- Ver Status");
        System.out.println("3- Recuperar");
        System.out.println("0- Sair");
    }

    public static void mostrarStatus(String nome, int ataque,
                                     int defesa, int vida, int energia,
                                     int nivel, int inimigosMortos,
                                     int pocoes, boolean temArmadura,
                                     boolean temArma) {

        System.out.println("\nOlá " + nome);
        System.out.println("Nível: " + nivel);
        System.out.println("Inimigos derrotados: " + inimigosMortos);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia);
        System.out.println("Poções: " + pocoes);
        System.out.println("Armadura: " + (temArmadura ? "Sim" : "Não"));
        System.out.println("Arma: " + (temArma ? "Sim" : "Não"));
    }

    public static int recuperar(int vida, int vidaMaxima)
            throws InterruptedException {

        vida += 10;

        if (vida >= vidaMaxima) {
            vida = vidaMaxima;
            System.out.println("Vida maxima atingida!");
        } else {
            System.out.println("Vida aumentada!");
        }

        System.out.println("Vamos recuperar sua energia..");

        Thread.sleep(2000);

        System.out.println("Ainda estamos recuperando");

        Thread.sleep(3000);

        System.out.println("Energia recuperada!!");

        return vida;
    }

    public static int usarPocao(int vida, int vidaMaxima) {

        vida += 30;

        if (vida >= vidaMaxima) {
            vida = vidaMaxima;
        }

        System.out.println("Você utilizou uma poção!");
        System.out.println("Sua vida agora é: " + vida);

        return vida;
    }

    public static int encontrarItemRaro(Random random) {

        System.out.println("Sortudinho, ganhou um item raro");

        return random.nextInt(2) + 1;
    }

    public static int combater(int vida, int ataque, int defesa,
                               boolean temArma, boolean temArmadura,
                               boolean inimigoEspecial, Random random) {

        int mostroEspecial = random.nextInt(20) + 1;
        int vidaMostro = random.nextInt(80) + 40 + mostroEspecial;
        int ataqueMostro = random.nextInt(30) + 5;

        if (inimigoEspecial) {
            vidaMostro += 50;
            ataqueMostro += 10;

            System.out.println("\n********************************");
            System.out.println("    VOCÊ ENCONTROU UM CHEFÃO!");
            System.out.println("********************************");
        } else if (vidaMostro >= 100) {
            System.out.println("Você encontrou o Marcelo Goblin!");
        }

        if (temArmadura) {
            defesa += 20;
        }

        if (temArma) {
            ataque += 20;
        }

        System.out.println("Seu inimigo tem:");
        System.out.println("Vida: " + vidaMostro);
        System.out.println("Ataque: " + ataqueMostro);

        while (vidaMostro > 0 && vida > 0) {

            int sorteioDado = sortearDadoD20(random);

            if (sorteioDado <= 7) {
                System.out.println("Você errou!");
            } else if (sorteioDado <= 14) {
                vidaMostro -= ataque / 2;
                System.out.println("Metade do dano");
            } else if (sorteioDado <= 19) {
                vidaMostro -= ataque;
                System.out.println("Ataque total");
            } else {
                vidaMostro -= ataque * 2;
                System.out.println("ATAQUE CRITICO");
            }

            System.out.println("Vida Personagem: " + vida);
            System.out.println("Vida Mostro: " + vidaMostro);

            if (vidaMostro > 0) {

                sorteioDado = sortearDadoD20(random);

                if (sorteioDado <= 7) {
                    System.out.println("O Goblin errou!");
                } else if (sorteioDado <= 14) {
                    vida -= ataqueMostro / 2;
                    System.out.println("Ataque pela metade");
                } else if (sorteioDado <= 19) {
                    vida -= ataqueMostro;
                    System.out.println("Você recebeu o ataque total");
                } else {
                    vida -= ataqueMostro * 2;
                    System.out.println("Você recebeu um critico");
                }

                System.out.println("Vida Personagem: " + vida);
                System.out.println("Vida Mostro: " + vidaMostro);
            }
        }

        if (vidaMostro > 0) {
            System.out.println("O Goblin te pegou!");
            return 0;
        } else {
            System.out.println("Você MATOU ele!");
            return vida;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        mostrarInicio();

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Informe o nome do seu personagem");
        String nome = sc.next();

        int vida = 100;
        int ataque = 0;
        int defesa = 0;
        int energia = 0;
        int inimigosMortos = 0;
        int nivel = 1;
        int pocoes = 2;

        boolean temArma = false;
        boolean temArmadura = false;

        System.out.println("Seja bem-vindo " + nome + "!");

        int opcao = escolhaPersonagem(sc);

        if (opcao == 1) {
            vida = 120;
            ataque = 80;
            defesa = 50;
        } else if (opcao == 2) {
            vida = 100;
            ataque = 70;
            defesa = 40;
        } else if (opcao == 3) {
            vida = 80;
            ataque = 50;
            defesa = 60;
        } else {
            vida = 90;
            ataque = 40;
            defesa = 20;
        }

        int opcaoJogo = -1;
        int vidaMaxima = vida;

        while (opcaoJogo != 0) {

            mostrarMenuJogo();

            opcaoJogo = sc.nextInt();

            if (opcaoJogo == 1) {

                int opcaoExplorar = explorar(random);

                if (opcaoExplorar == 1) {

                    boolean inimigoEspecial = random.nextInt(100) < 15;

                    if (inimigoEspecial) {
                        System.out.println("Um inimigo poderoso apareceu!");
                    } else {
                        System.out.println("Você encontrou um Goblin");
                    }

                    System.out.println("Você quer fazer o quê?");
                    System.out.println("1 - Atacar");
                    System.out.println("2 - Defender");
                    System.out.println("3 - Fugir");

                    int opcaoLuta = sc.nextInt();

                    if (opcaoLuta == 1) {

                        vida = combater(vida, ataque, defesa,
                                temArma, temArmadura,
                                inimigoEspecial, random);

                        if (vida > 0) {

                            inimigosMortos++;

                            System.out.println("Inimigos derrotados: " + inimigosMortos);

                            if (inimigosMortos % 3 == 0) {

                                nivel++;
                                ataque += 10;
                                vidaMaxima += 10;
                                vida += 10;

                                System.out.println("\n********************************");
                                System.out.println("    VOCÊ SUBIU DE NÍVEL!");
                                System.out.println("    Nível atual: " + nivel);
                                System.out.println("    Ataque +10");
                                System.out.println("    Vida máxima +10");
                                System.out.println("********************************");
                            }
                        }

                        if (vida <= 0) {
                            System.out.println("\nVOCÊ MURREU!");
                            System.out.println("Nível alcançado: " + nivel);
                            System.out.println("Inimigos betinhas moggados: " + inimigosMortos);
                            break;
                        }

                    } else if (opcaoLuta == 2) {

                    } else if (opcaoLuta == 3) {
                        System.out.println("Corrrreeeuuuu!!");
                    } else {
                        System.out.println("Opção invalida");
                    }

                } else if (opcaoExplorar == 2) {

                    System.out.println("Você encontrou um bau de energia");

                    if (energia + 1 < 10) {
                        energia += 1;
                    } else {
                        System.out.println("Energia cheia!");
                    }

                } else if (opcaoExplorar == 3) {

                    System.out.println("Você deve seguir sua vidinha");

                } else if (opcaoExplorar == 4) {

                    int itemSorteado = encontrarItemRaro(random);

                    if (itemSorteado == 1) {

                        if (temArmadura) {
                            System.out.println("Você já tem, rala");
                        } else {
                            temArmadura = true;
                            System.out.println("Você encontrou uma armadura!");
                        }

                    } else {

                        if (temArma) {
                            System.out.println("Você ja ta armado!");
                        } else {
                            temArma = true;
                            System.out.println("Você encontrou uma arma!");
                        }
                    }
                }

            } else if (opcaoJogo == 2) {

                mostrarStatus(nome, ataque, defesa, vida, energia,
                        nivel, inimigosMortos, pocoes,
                        temArmadura, temArma);

            } else if (opcaoJogo == 3) {

                System.out.println("\n1- Recuperar vida e energia");
                System.out.println("2- Usar poçãozinha");
                System.out.println("Escolha uma opção:");

                int opcaoRecuperar = sc.nextInt();

                if (opcaoRecuperar == 1) {

                    vida = recuperar(vida, vidaMaxima);

                    energia += 1;

                } else if (opcaoRecuperar == 2) {

                    if (pocoes > 0) {

                        if (vida < vidaMaxima) {
                            vida = usarPocao(vida, vidaMaxima);
                            pocoes--;
                            System.out.println("Poções restantes: " + pocoes);
                        } else {
                            System.out.println("Sua vida já está no limite");
                        }

                    } else {
                        System.out.println("Você não tem mais poções");
                    }

                } else {
                    System.out.println("Opção invalida");
                }

            } else {
                break;
            }
        }

        sc.close();
    }
}
