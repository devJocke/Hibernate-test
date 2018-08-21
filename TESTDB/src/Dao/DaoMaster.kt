package Dao

import java.util.LinkedHashMap

abstract class DaoMaster {

    private val properties = javaClass.declaredFields
    private lateinit var tempMap: LinkedHashMap<String, Boolean>

    open fun checkAndReturnUpdates(map: LinkedHashMap<String, Boolean>): LinkedHashMap<String, Boolean> {
        tempMap = map
        properties.forEachIndexed { i, field ->
            field.isAccessible = true
            if (properties[i].type.toString() == "boolean" && !properties[i].getBoolean(this)) {
                tempMap[properties[i].name] = properties[i].getBoolean(this)
            }
            field.isAccessible = false
        }
        return tempMap
    }

    open fun saveChanges(key: String): String {
        properties.forEach { field ->
            field.isAccessible = true
            if (key == field.name) {
                field.setBoolean(this, java.lang.Boolean.TRUE)
            }
            field.isAccessible = false
        }
        return key
    }

    open fun setNewUnicorn(map: LinkedHashMap<String, Boolean>): LinkedHashMap<String, Boolean> {
        tempMap = map
        properties.forEachIndexed { i, field ->
            field.isAccessible = true
            if (properties[i].type.toString() == "boolean") {
                tempMap[properties[i].name] = properties[i].getBoolean(this)
            }
            field.isAccessible = false
        }
        return tempMap
    }
}