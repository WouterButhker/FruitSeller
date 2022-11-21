package furhatos.app.fruitseller.setting

import furhatos.nlu.ComplexEnumEntity
import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.ListEntity
import furhatos.nlu.common.Number
import furhatos.nlu.common.PersonName
import furhatos.nlu.common.Time
import furhatos.util.Language

// Our Fruit entity.
class Fruit : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("banana", "orange", "apple", "cherimoya", "apricot", "Pear", "cherry", "kiwi", "Coconut", "Blueberry", "strawberry", "avocado")
    }
}

// Our BuyFruit intent
class BuyFruit(var fruits : FruitList? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@fruits", "I want @fruits", "I would like @fruits", "I want to buy @fruits")
    }
}

class SayName(val name : PersonName? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@name", "My is is @name", "I'm called @name")
    }
}

class FruitList : ListEntity<QuantifiedFruit>()


class QuantifiedFruit(
        val count : Number? = Number(1),
        val fruit : Fruit? = null) : ComplexEnumEntity() {

    override fun getEnum(lang: Language): List<String> {
        return listOf("@count @fruit", "@fruit")
    }

    override fun toText(): String {
        return generate("$count $fruit")
    }
}

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What fruits do you have?",
                "What are the alternatives?",
                "What do you have?", "what are the possibilities?", "I don't know")
    }
}


class ReceiveTime (var time : Time? = null)  : Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@time", "I want it delivered at @time", "by @time")
    }
}

