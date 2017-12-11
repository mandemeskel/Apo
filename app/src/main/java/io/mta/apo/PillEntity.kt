package io.mta.apo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * This actual object that gets saved into the database, the properties are the column names and values.
 *
 * @param brand_name the layman name of the pill e.g. advil, tylenol
 * @param medical_name the jargon name of the pill e.g. ibuprofen
 * @param img_path the path to the img for this pill, can be a remote or local path
 */
@Entity(tableName = Pill.TABLE_NAME)
class PillEntity(val brand_name: String, val medical_name: String, val img_path: String, val description: String) {

    /**
     * This is an auto-generated primary key for the pill.
     * TODO: replace this with the pillID passed from the server
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}