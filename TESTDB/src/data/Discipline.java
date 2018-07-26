package data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Discipline {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean angry = false;
    @Transient
    private Map<String,Boolean> map= new HashMap<>();

    Discipline() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private boolean isAngry() {
        return angry;
    }

    private void setAngry(boolean angry) {
        this.angry = angry;
    }

    Map<String, Boolean> getNeeds() {
        if (!isAngry()) {
            map.put("Börje is angry", isAngry());
        }
        return map;
    }
}
