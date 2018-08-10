package Data

import java.util.LinkedHashMap
import javax.persistence.*

@Entity
class Discipline internal constructor() : Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
    var angry: Boolean = false

    @Transient
    private val map = LinkedHashMap<String, Boolean>()

    override fun newUnicorn(): Discipline {
        map["angry"] = false
        return this
    }


      override fun categories(): LinkedHashMap<String, Boolean> {
        if (!angry) {
            map["angry"] = angry
        }
        return map
    }

    override fun save(key: String): String {
        if (key == "angry") {
            angry = true
            map.remove(key)
        }
        return "is no longer $key"
    }

    override fun toString(): String {
        return javaClass.simpleName
    }
}