package freelance.demoapp.data.database.database

import androidx.room.TypeConverter
import freelance.demoapp.data.database.dto.Enums
import java.math.BigDecimal

class RoomConverter {
    @TypeConverter
    fun fromSender(sender: Enums.MessageSender): String = sender.value

    @TypeConverter
    fun toSender(value: String): Enums.MessageSender = Enums.MessageSender.valueOf(value)

    @TypeConverter
    fun fromTypeTransaction(type: Enums.TypeTransaction): String = type.value

    @TypeConverter
    fun toTypeTransaction(value: String) : Enums.TypeTransaction = Enums.TypeTransaction.valueOf(value)

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String = value.toPlainString()

    @TypeConverter
    fun toBigDecimal(value: String): BigDecimal = value.toBigDecimal()
}