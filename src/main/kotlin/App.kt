import javax.script.ScriptEngineManager




fun main() {
    with(ScriptEngineManager().getEngineByExtension("kts")) {
        val s = eval(
            """import kon.*
sandwich {
    with type "toasted"
    bread = "baguette"
    
    fillings {
        +"cheese"
        +"ham"
    }
    """)
        println (s)
    }
}
