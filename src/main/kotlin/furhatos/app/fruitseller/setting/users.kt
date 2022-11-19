package furhatos.app.fruitseller.setting

import furhatos.records.User


class FruitData (
        var fruits : FruitList = FruitList()
)


val User.order : FruitData
    get() = data.getOrPut(FruitData::class.qualifiedName, FruitData())
