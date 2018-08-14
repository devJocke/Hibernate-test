package Dao

import Dal.SessionConfiguration
import javax.persistence.*

@Entity
@Table(name = "Blueprints", schema = "dbo", catalog = "mobileRemoteDb")
class Blueprints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0

    var fishingShack: Boolean = false
    var running: Boolean = false


    fun getServiceAll(): MutableList<Blueprints>? {
        val query = SessionConfiguration.getSession().criteriaBuilder.createQuery(Blueprints::class.java)
        val root = query.from(Blueprints::class.java)
        query.select(root)
        val q = SessionConfiguration.getSession().createQuery(query)
        return q.list()

    }

    override fun toString(): String {

        return "Blueprints(fishingShack=$fishingShack)\n (running=$running)"
    }

}