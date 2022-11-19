package furhatos.app.fruitseller.flow.main

import furhatos.app.fruitseller.setting.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language
import furhatos.app.fruitseller.setting.ReceiveTime

val getTime = state{
    onEntry {
        furhat.ask("At what time do you want it delivered?")
    }

    onResponse<ReceiveTime>{
        val time = it.intent.time
        if (time != null){
            furhat.say("Perfect! You will receive your order at ${time.toText()}")
        }
        goto(ConfirmOrder)
    }

    onResponse<No> {
        furhat.say("Okay, that's fine, you can get your order directly from our shop")
        goto(ConfirmOrder)
    }

}
