package io.mta.apo

import android.arch.persistence.room.Entity
import java.util.*

/**
 * This actual object that gets saved into the database, the properties are the column names and values.
 *
 * @param id the pill id passed with the pill data from the server
 * @param brand_name the layman name of the pill e.g. advil, tylenol
 * @param medical_name the jargon name of the pill e.g. ibuprofen
 * @param img_path the path to the img for this pill, can be a remote or local path
 */
@Entity(tableName = Pill.TABLE_NAME, primaryKeys = ["id"])
class PillEntity(var id: Int, var brand_name: String, var medical_name: String, var img_path: String, var description: String) {

    constructor(id: Int): this(id, "", "", "", "") {
    }

    /**
     * The date that the user last searched for the pill, which is set when this entity is created i.e. when the pill is created and saved into the database.
     */
    var date_last_searched: Date = Date()

}