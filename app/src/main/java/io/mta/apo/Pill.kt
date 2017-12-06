package io.mta.apo

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
}