package team.currency.crypto.db.mapper

import team.currency.crypto.data.entity.Currency
import tech.iuic.iuicwork.db.entity.CurrencyEntity

/**
 * Created by root on 20.02.18.
 */
class CurrencyMapper{

    fun currencyDbFromResponse(currency:Currency): CurrencyEntity{
        var currencyEntity: CurrencyEntity = CurrencyEntity()
        //city.cityId = cityResponse.id
        currencyEntity.idCurrency = currency.id
        currencyEntity.name = currency.name
       currencyEntity.shortName =  currency.shortName
        currencyEntity.imageUrl = currency.imageUrl
        currencyEntity.cost = currency.cost
        currencyEntity.change_one_hour = currency.change_one_hour
        currencyEntity.change_twenty_four_hour =  currency.change_twenty_four_hour
        currencyEntity.change_one_week =  currency.change_one_week
        currencyEntity.modified = currency.modified
          return currencyEntity
    }




    fun currencyFromDb(currencyEntity:CurrencyEntity): Currency{
        return  Currency(currencyEntity.idCurrency,
                currencyEntity.name ,
                        currencyEntity.shortName ,
                        currencyEntity.imageUrl ,
                        currencyEntity.cost ,
                        currencyEntity.change_one_hour ,
                        currencyEntity.change_twenty_four_hour ,
                        currencyEntity.change_one_week,
                        currencyEntity.modified
        )
     }

}