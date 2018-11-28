# KotlinHigherOrderMessagePassing
Kotlin Higher Order Message Passing with a Lamba

http://ios.robertlinnemann.com/kotlin-higher-order-function-lambda/


This example is basically showing how to pass a function as a parameter to an object that can then be called from within. I use a String but it could be any parameters or none. Comparing to an interface it is a bit cleaner and a bit less code. Interfaces are great when you need multiple functions but using a Lambda works great for just a function and is just as clear.
```kotlin
/**
** Higher-Order Function as parameter to a class.
** Robert Linnemann
**/

fun main(args: Array) {
//make an object that makes an internal object.
val m = Master()
//call a function on the internal object that then calls
//through to the lamda function that was passed into it.
m.apprentice?.sendMessage()
}

class Master {
var apprentice: Apprentice?
init {
//pass in the function print to the Apprentice object.
apprentice = Apprentice(::print)
}

fun print(message: String) {
println(message)
}
}

class Apprentice(send: (String) -> Unit) {
val send = send
fun sendMessage() {
send("Print This for me.")
}
}
```
I’ve also implemented an interface alongside which compares these features a bit better.
```kotlin
/**
** Higher-Order Function as parameter to a class.
** Robert Linnemann
**/

fun main(args: Array) {
//make an object that makes an internal object.
val m = Master()
//call a function on the internal object that then calls
//through to the lamda function that was passed into it.
m.apprentice?.sendMessageThroughLambda()
//A similar was to pass a message to a parent object would be to
//use an interface
m.apprentice?.sendMessageThroughInterface()
}

interface PassMessageListener {
fun message(message: String)
}

class Master: PassMessageListener {
var apprentice: Apprentice?
init {
//pass in the function print to the Apprentice object.
apprentice = Apprentice(::print)
//setup the interface listener
apprentice?.passMessageListener = this
}

fun print(message: String) {
println(message)
}
//interface method
override fun message(message: String) {
print(message)
}
}

//This gets a function passed in as the variable 'send'.
class Apprentice(send: (String) -> Unit) {
val send = send
var passMessageListener: PassMessageListener? = null

fun sendMessageThroughLambda() {
//sends directly to the function on a whole 'notha level
send("Print this for me.")
}
fun sendMessageThroughInterface() {
//traditional interface way to pass a message
passMessageListener?.message("Print this for you")
}
}
```
To run this you can put it into the great site https://try.kotlinlang.org/
or compile it from the command line (install tools). I prefer using sdkman.io
Then run these commands:

kotlinc higherOrder.kt -include-runtime -d higherOrder.jar
java -jar higherOrder.jar


