package com.cursokotlin.retrofitkotlinexample.kotlindesignpatterns.creational

class Config2 private constructor(){
    var name:String = ""
    companion object {
        /*Con by lazy lo que hacemos es que hasta que algo no llame a esa variable
        * lo quehay ahi dentro nunca se va a ejecutar, se ejecuta solo cuando se llama
        * */
        val instance: Config2 by lazy{ Config2() }
    }
}

/* Un objeto como tal no permite constructores por que se crea el solo de forma automatica
* por lo tanto no se puede poner el private Constructor de igual forma tampoco el companion object*/
object Config3 {
    var name:String = ""
}

fun main(){
    val example1 = Config2.instance
    example1.name = "Francisco"
    val example2 = Config2.instance

    Config3.name = "Hernandez"
    println(Config3.name)
}