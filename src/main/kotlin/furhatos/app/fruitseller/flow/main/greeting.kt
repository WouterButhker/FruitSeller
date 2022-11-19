package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.flow.Parent
import furhatos.app.fruitseller.setting.SayName
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.TellName
import furhatos.nlu.common.Yes

val Greeting : State = state(Parent) {
    onEntry {
        random(
                {furhat.say("Hey!")},
                {furhat.say("Hello there.")}
        )
        furhat.ask("What is your name?")
    }

    onResponse<SayName> {
        users.current.data.put("name", it.intent.name);
        furhat.say("Nice to meet you " + it.intent.name + "!")
        goto(TakingOrder)
    }

}
