### Kotlin DSL and Script Enging Example

A simple project influenced by [![12 days of Kotlin - Day 02](https://kotlindays.com/2019/12/02/days-of-kotlin-a-dsl-for-everyone/index.html)] that shows
1. How can you build DSL with kotlin syntax sugar
2. How can you make that DSL a JSON-like.
### Setup

```kotlin
dependencies {
    implementation(kotlin("scripting-jsr223-embeddable"))
}
```


### DSL sugar
_In short_: trailing lambda, infix, unaryPlus.

All this operations just to drop brackets! But it is sufficient enough to build readable definitions.

```kotlin
sandwich {
   with type "toasted"
   bread = "baguette"
   
   fillings {
       +"cheese"
       +"ham"
   }
}
```

/!\ Hint: 
if you override String.unaryPlus() inside Sandwich class, you will have access to all its members.


### Transferable DSL (JSON-like)
_In short_: Tried to answer a question: Can you replace JSON with DSL?

Pros: it is feasible to do following and `s` would be proper `Sandwich` object:
```kotlin

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
    }
```


Cons: 
- Extra line of imports (could be auto-added)
- Obviously Not secure: the `eval()` would execute anything, no checks, no restrictions
- if to be used on Android, would require heavyweight compiler-as-a-library.
