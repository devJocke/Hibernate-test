import Dao.Farm

class FarmOverview() {

    private var farm: Farm? = null

    constructor(farm: Farm) : this() {
        this.farm = farm

        println("-------- FARM MENU ----------")
        println("You can build following")
        //TODO LOOP THROUGH ALL VALUES, CHECK IF MISSING AND PRESENT SOMETHING
        //farm.blueprints[0].getServiceAll()?.forEach {a -> print(a.) }
        println("----------------------" )
    }

}