package Dao

import java.util.LinkedHashMap
import javax.persistence.*

@Entity
class Discipline : Care.CareInformation {

    @Transient
    private val properties = javaClass.declaredFields

    override fun checkForUpdates() {
        for (i in properties.indices) {
            if (properties[i].type.toString() == "boolean" && !properties[i].getBoolean(this)) {
                map[properties[i].name] = properties[i].getBoolean(this)
            }
        }
    }

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