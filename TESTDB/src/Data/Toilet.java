package Data;


import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Toilet implements Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean flush = false;

    @Transient
    private LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

    Toilet() {
    }

    Toilet NewUnicorn() {
        map.put("flush", false);
        return this;
    }

    @Override
    public LinkedHashMap<String, Boolean> getCategories() {
        if (!isFlushed()) {
            map.put("flush", isFlushed());
        }
        return map;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public String save(String key) {
        if (key.equals("flush")) {
            setFlushed(true);
            map.remove(key);
        }
        return "is glad that you helped him " + key;
    }


    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }


    public boolean isFlushed() {
        return flush;
    }

    private void setFlushed(boolean flushed) {
        this.flush = flushed;
    }

}
