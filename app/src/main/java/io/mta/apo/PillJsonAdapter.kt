package io.mta.apo

import com.squareup.moshi.*


/**
 * Converts JSON into pill objects
 *
 * @property IMG_URL the images of the pills are hosted at this server
 * @property IMG_EXTENSION all the images are jpgs
 */
object PillJsonAdapter {
    val IMG_URL = "https://pillbox.nlm.nih.gov/assets/large/"
    val IMG_EXTENSION = ".jpg"
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(PillJsonAdapter.PillJson::class.java)

    /**
     * Converts a json string into a pill object.
     *
     * @param json a json string
     * @return a pill object
     */
    fun fromJson(json: String): Pill {
        val pill_json = adapter.fromJson(json)
        return convertToPill(pill_json!!)
    }

    /**
     * Converts a json string array to an array of pills.
     *
     * @param json a json array string
     * @return an array of pill objects
     */
    fun fromJsonArray(json: String): Array<Pill> {
        val array_adapter = moshi.adapter(Array<PillJson>::class.java)
        val jpills = array_adapter.fromJson(json) as Array<PillJson>
        val pills = mutableListOf<Pill>()

        jpills.map{ jpill -> pills.add(convertToPill(jpill))}

        return pills.toTypedArray()
    }

    /**
     * Converts a PillJson object to a Pill object, which mainly involves moving over values and formating img_path and description properties
     *
     * @param jpill a PillJson object
     * @return a pill object
     */
    private fun convertToPill(jpill: PillJson): Pill {
        var img_path = ""

        if(jpill.img_path.isNotBlank())
            img_path = IMG_URL + jpill.img_path + IMG_EXTENSION

        var description = jpill.description.joinToString("\n")

        if(description.isBlank())
            description = ""

        return Pill(jpill.id, jpill.brand_name, jpill.medical_name, img_path, description)

    }

    /**
     * Moshi will convert the json directly into this data model.
     */
    data class PillJson(
            @Json(name = "pillID") val id: Int,
            @Json(name = "medicine_name") val brand_name: String,
            @Json(name = "rxString") val medical_name: String,
            @Json(name = "splimage") val img_path: String,
            @Json(name = "active_ingredient") val description: Array<String>
    )

}