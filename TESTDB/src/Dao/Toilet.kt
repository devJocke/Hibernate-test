package Dao


import javax.persistence.*
import java.util.LinkedHashMap

@Entity
class Toilet : Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        private set

    var flush = false
        private set

    @Transient
    private val map = LinkedHashMap<String, Boolean>()

    override fun newUnicorn(): Toilet {
        map["flush"] = false
        return this
    }

    override fun categories(): LinkedHashMap<String, Boolean> {
        if (!flush) {
            map["flush"] = flush
        }
        return map
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

    override fun save(key: String): String {
        if (key == "flush") {
            flush = true
            map.remove(key)
        }
        return "is glad that you helped him $key"
    }

}
