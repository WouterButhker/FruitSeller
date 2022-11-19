package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.flow.Parent
import furhatos.app.fruitseller.setting.BuyFruit
import furhatos.app.fruitseller.setting.Fruit
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.Intent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language

val TakingOrder : State = state(Parent) {
    onEntry {
        random(
                {furhat.ask("Would you like some fruits?")},
                {furhat.ask("Do you want some fruits?")}
        )
    }

    onResponse<Yes> {
        random(
                { furhat.ask("What kind of fruit do you want?") },
                { furhat.ask("What type of fruit?") }
        )
        goto(Idle)
    }

    onResponse<RequestOptions> {
        furhat.say("We have ${Fruit().optionsToText()}")
        furhat.ask("Do you want some?")
    }

    onResponse<BuyFruit> {
        furhat.say("${it.intent.fruits}, what a lovely choice!")
    }

    onResponse<No> {
        furhat.say("Okay, that's a shame. Have a splendid day!")
        goto(Idle)
    }
}

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What fruits do you have?",
                "What are the alternatives?",
                "What do you have?")
    }

}


