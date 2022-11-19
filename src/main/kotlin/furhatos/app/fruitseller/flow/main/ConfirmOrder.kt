package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.flow.Parent
import furhatos.app.fruitseller.setting.FruitList
import furhatos.app.fruitseller.setting.order
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val ConfirmOrder: State = state {
    onEntry {
        furhat.ask("Would you like to confirm your order?")
    }

    onResponse<Yes> {
        furhat.say("Here you go. Have a great day " + users.current.data.get("name") + "!")
        goto(Idle)
    }

    onResponse<No> {
        furhat.say("Okay " + users.current.data.get("name") + ", I've discarded your order.")
        users.current.order.fruits = FruitList();
        goto(TakingOrder)
    }
}
