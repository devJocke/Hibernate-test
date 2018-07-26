package data;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.Map;

public abstract class Needs {

    public static Map<String, Boolean> getAllUnicornNeeds(Session session) {

        TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);
        Unicorn unicorn = unicornTypedQuery.getSingleResult();
        return unicorn.getCare().getAllNeeds();
    }
}
