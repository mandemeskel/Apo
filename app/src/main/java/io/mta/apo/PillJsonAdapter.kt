package io.mta.apo

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json

/**
 * Converts JSON into a pill object
 *
 * @property IMG_URL the images of the pills are hosted at this server
 * @property IMG_EXTENSION all the images are jpgs
 */
class PillJsonAdapter {
    val IMG_URL = "https://pillbox.nlm.nih.gov/assets/large/"
    val IMG_EXTENSION = ".jpg"

    /**
     * Converts a PillJson into a proper pill object by doing some formatting to img_path and description properties.
     *
     * @param json a PillJson object
     * @return a pill object
     */
    @FromJson
    fun fromJson(json: PillJson): Pill {
        val img_path = IMG_URL + json.img_path + IMG_EXTENSION
        val description = json.description.joinToString("\n")

        return Pill(json.id, json.brand_name, json.medical_name, img_path, description)
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