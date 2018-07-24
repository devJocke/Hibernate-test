package data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Play {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String game = "Default";
    private String gameStatus = "Default";

    public Play() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }


    public String getGame() {
        return game;
    }

    private void setGame(String game) {
        this.game = game;
    }


    public String getGameStatus() {
        return gameStatus;
    }

    private void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

}
