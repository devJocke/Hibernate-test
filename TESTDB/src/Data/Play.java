package Data;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Play implements Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean hockey = false;
    private boolean football = false;

    @Transient
    private
    LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

    Play() {
    }

    Play NewUnicorn() {
        map.put("hockey", false);
        map.put("football", false);
        return this;
    }

    @Override
    public LinkedHashMap<String, Boolean> getCategories() {
        if (!isHockey()) {
            map.put("hockey", isHockey());
        }
        if (!isFootball()) {
            map.put("football", isFootball());
        }
        return map;
    }

    @Override
    public String save(String key) {
        if (key.equals("hockey")) {
            setHockey(true);
            map.remove("hockey");
        }
        if (key.equals("football")) {
            setFootball(true);
            map.remove("football");
        }
        return "enjoyed playing " + key;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    boolean isHockey() {
        return hockey;
    }

    private void setHockey(boolean hockey) {
        this.hockey = hockey;
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
