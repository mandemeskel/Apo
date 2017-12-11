package io.mta.apo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
class Pill (val brand_name: String, val medical_name: String, val img_path: String, val description: String) {

    init {
        // TODO
    }

    /**
     * Saves pill to recent searches list.
     */
    fun save() {
        // TODO
    }

    // companion object to host static functions and properties
    companion object Handler {
        /**
         * Loads saved pills from recent searches list
         * @return a array of pills searched by the user
         */
        fun loadSavedPills(): Array<Pill> {
            // TODO
            var pills: Array<Pill> = arrayOf()
            return pills
        }

        /**
         * Clear the user's saved pills
         */
        fun clearSavedPills() {
            // TODO
        }
    }

    @Entity(tableName = "pills")
    class PillEntity(val brand_name: String, val medical_name: String, val img_path: String, val description: String) {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
        // TODO: should be img_path be a foreign-key, will there be more than one image per pill?

    }
}