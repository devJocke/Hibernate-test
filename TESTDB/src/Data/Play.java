package Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Play implements Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean bored = false;
    private boolean football = false;

    @Transient
    private
    Map<String, Boolean> map = new HashMap<>();

    Play() {
        map.put("bored", false);
        map.put("football", false);
    }

    @Override
    public Map<String, Boolean> getCategories() {
        if (!isBored()) {
            map.put("bored", isBored());
        }
        if (!isFootball()) {
            map.put("football", isFootball());
        }
        return map;
    }

    @Override
    public void save(String s) {
        if (s.equals("bored")) {
            setBored(true);
            map.remove("bored");
        }
        if (s.equals("football")) {
            setFootball(true);
            map.remove("football");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
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

}
