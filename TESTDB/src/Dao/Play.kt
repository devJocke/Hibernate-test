package Dao

import javax.persistence.*
import java.util.LinkedHashMap

@Entity
class Play   : Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        private set
    private var hockey = false
    private var football = false

    @Transient
    private val map = LinkedHashMap<String, Boolean>()

     override fun newUnicorn(): Play {
        map["hockey"] = false
        map["football"] = false
        return this
    }

      override fun categories(): LinkedHashMap<String, Boolean> {
        if (!hockey) {
            map["hockey"] = hockey
        }
        if (!football) {
            map["football"] = football
        }
        return map
    }

    override fun save(key: String): String {
        if (key == "hockey") {
            hockey = true
            map.remove(key)
        }
        if (key == "football") {
            football = true
            map.remove(key)
        }
        return "enjoyed playing $key"
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

}
