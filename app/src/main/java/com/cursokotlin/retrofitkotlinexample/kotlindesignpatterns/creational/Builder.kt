package com.cursokotlin.retrofitkotlinexample.kotlindesignpatterns.creational

interface BuilderInterface{
    fun setName(name: String):BuilderInterface
    fun setAge(age: Int):BuilderInterface
    fun setDescription(description: String):BuilderInterface
    fun setPower(power: String):BuilderInterface
    fun setIsAlive(isAlive: Boolean):BuilderInterface
    fun build(): Hero
}


class SuperHeroBuilder: BuilderInterface {
    private var name: String? = null
    private var age:Int? = null
    private var description:String? = null
    private var power: String? = null
    private var isAlive: Boolean? = null

    override fun setName(name: String):BuilderInterface {
        this.name = name
        return this
    }

    override fun setAge(age: Int):BuilderInterface {
        this.age = age
        return this}

    override fun setDescription(description: String):BuilderInterface {
        this.description = description
        return this}

    override fun setPower(power: String):BuilderInterface {
        this.power = power
        return this}

    override fun setIsAlive(isAlive: Boolean):BuilderInterface {
        this.isAlive = isAlive
        return this}

    override fun build(): Hero {
        return Hero (name,age,description,power,isAlive)
    }

}
data class Hero(
    val name: String?,
    val age:Int?,
    val description:String?,
    val power: String?,
    val isAlive: Boolean?
){}

class SuperHeroBuilderDirector(){
    fun createDevHeroWeak(superHeroBuilder: SuperHeroBuilder): Hero{
        /*superHeroBuilder.setName("")
        superHeroBuilder.setAge(20)
        superHeroBuilder.setDescription("")
        superHeroBuilder.setPower("")
        superHeroBuilder.setIsAlive(true)
        superHeroBuilder.build()*/
        return superHeroBuilder.setName("").setAge(20).build()
    }
    fun createDevHeroStrong(superHeroBuilder: SuperHeroBuilder): Hero{
        return superHeroBuilder.setName("").setAge(20).setPower("100%").build()
    }
}




fun main(){
    //No deberian asignarse valores asi
    val hero = Hero("Francisco",null,null,null,true)

    val superHeroBuilderDirector = SuperHeroBuilderDirector()
    val superHeroBuilder = SuperHeroBuilder()

    superHeroBuilderDirector.createDevHeroStrong(superHeroBuilder)
    superHeroBuilderDirector.createDevHeroWeak(superHeroBuilder)
}

