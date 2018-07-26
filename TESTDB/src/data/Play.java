package data;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Play {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean bored = false;
    @Transient
    private boolean football = false;
    @Transient
    private Map<String, Boolean> map = new HashMap<>();

    Play() {
    }

    boolean isBored() {
        return bored;
    }

    private void setBored(boolean bored) {
        this.bored = bored;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    boolean isFootball() {
        return football;
    }

    private void setFootball(boolean football) {
        this.football = football;
    }

    Map<String, Boolean> getNeeds() {
        if (!isBored()) {
            map.put("Börje is bored", isBored());
        }
        if (!isFootball()) {
            map.put("Börje wants to play football", isFootball());
        }
        return map;
    }
}
