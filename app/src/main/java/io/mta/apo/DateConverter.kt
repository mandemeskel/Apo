package io.mta.apo

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * A converter class that will allow us to use date column in an entity. This class basicaly converts date object in the entity into a primitive that can be saved the Room library.
 */
class DateConverter {

    /**
     * Converts milliseconds into a date object which is returned.
     * @param date_ms the milliseconds to convert into a date
     * @return date object representation of the milliseconds passed into the function
     */
    @TypeConverter
    fun fromTimestamp(date_ms: Long): Date {
        return Date(date_ms)
    }

    /**
     * Converts a date object into long, the number of milliseconds since January 1, 1970, 00:00:00 GMT.
     * @param date the date object to convert into a long
     * @return the number milliseconds the date is converted into
     */
    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}