/**
 ** Higher-Order Function as parameter to a class.
 ** Robert Linnemann
 ** https://ios.robertlinnemann.com
 **/

fun main(args: Array<String>) {
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
