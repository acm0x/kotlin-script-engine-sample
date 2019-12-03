package kon

class Sandwich {

    private lateinit var type: String
    private lateinit var filling: Filling
    lateinit var bread: String

    val with = this

    infix fun type(sandwichType: String) {
        type = sandwichType
    }

    infix fun fillings(list: Filling.() -> Unit) {
        filling = Filling().apply(list)
    }


    override fun toString(): String {
        return "Sandwich(type='$type', bread='$bread', $filling)"
    }

    class Filling() {

        private val fillings: MutableList<String> = mutableListOf()

        operator fun String.unaryPlus() {
            fillings.add(this)
        }

        override fun toString(): String {
            return "fillings=${fillings.joinToString(",")}"
        }


    }



}



fun sandwich(order: Sandwich.() -> Unit): Sandwich = Sandwich().apply(order)

val result = sandwich {
    with type "toasted"
    bread = "baguette"
    fillings {
        +"cheese"
        +"ham"
    }

}
