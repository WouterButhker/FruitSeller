package furhatos.app.fruitseller.flow.main



import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language
import furhatos.app.fruitseller.setting.ReceiveTime

val delivery = state{
    onEntry {
        random(
                { furhat.ask("Would you like your order delivered?") },
                { furhat.ask("Do you want us to deliver your order?") }
        )
    }

    onResponse<Yes> {
        goto(getTime)

    }


    onResponse<No> {
        furhat.say("Okay, that's fine, you can get your order directly from our shop")
        goto(ConfirmOrder)
    }
}
