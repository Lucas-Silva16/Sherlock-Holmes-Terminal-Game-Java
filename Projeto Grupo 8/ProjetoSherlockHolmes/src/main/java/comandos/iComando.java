package comandos;
import nucleo.Jogo;

public interface iComando {
    String executar(Jogo jogo, String[] args);
}