package Data

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "Blueprints", schema = "dbo", catalog = "mobileRemoteDb")
data class Blueprints(val id : Int, val fishingShack : Boolean)