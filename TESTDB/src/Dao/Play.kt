package Dao

import javax.persistence.*
import java.util.LinkedHashMap


@Entity
class Play : Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0


    private var hockey = false
    private var football = false

    @Transient
    private val map = LinkedHashMap<String, Boolean>()


    override fun newUnicorn(): Play {
        map["hockey"] = false
        map["football"] = false
        return this
    }

    @Transient
    val properties = javaClass.declaredFields

    override fun categories(): LinkedHashMap<String, Boolean> {
        return map
    }

    /**
     * Adds false values to the map
     */
    override fun checkForUpdates() {
        for (i in properties.indices) {
            val property = properties[i]
            if (property.type.toString() == "boolean" && !property.getBoolean(this)) {
                map[property.name] = property.getBoolean(this)
            }
        }
    }

    override fun save(key: String): String {
        map.removeIfKeyExists(key)
        if (key == "hockey") {
            hockey = true
        }
        if (key == "football") {
            football = true
        }
        map.remove(key)
        return "enjoyed playing $key"
    }

    override fun toString(): String {
        return javaClass.simpleName
    }
}

private fun <K, V> LinkedHashMap<K, V>.removeIfKeyExists(key: K) {


    remove(key)
}
