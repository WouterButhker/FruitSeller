package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.setting.FruitList
import furhatos.app.fruitseller.setting.order
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No

fun OrderReceived(fruitList: FruitList) : State = state(Options) {
    onEntry {
        furhat.say("${fruitList.text}, what a lovely choice!")
        fruitList.list.forEach {
            users.current.order.fruits.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Did you want something else?")
    }

    onResponse<No> {
        furhat.say("Your order right now is ${users.current.order.fruits}.")
        goto(delivery)
    }
}
