package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.flow.Parent
import furhatos.app.fruitseller.setting.FruitList
import furhatos.app.fruitseller.setting.order
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.nlu.common.Time

val ConfirmOrder: State = state {
    onEntry {
        furhat.ask("You have ordered ${users.current.order.fruits}. Would you like to confirm your order?")
    }

    onResponse<Yes> {
        val time  = users.current.data.get("time") as Time?
        if ( time != null){
            furhat.say("Your order will be delivered at ${time.toText()}   . Have a great day " + users.current.data.get("name") + "!")
        }
        else{
            furhat.say("There you go. Have a great day " + users.current.data.get("name") + "!")
        }
        goto(Idle)
    }

    onResponse<No> {
        furhat.say("Okay " + users.current.data.get("name") + ", I've discarded your order.")
        users.current.order.fruits = FruitList();
        goto(TakingOrder)
    }
}
