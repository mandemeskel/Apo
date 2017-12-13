package io.mta.apo

import com.squareup.moshi.*

/**
 * Converts JSON into a pill object
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
     * Converts a json string into a pill object and does some formatting to img_path and description properties.
     *
     * @param json a json string
     * @return a pill object
     */
    fun fromJson(json: String): Pill {
        val pill_json = adapter.fromJson(json)
        var img_path = ""

        if(pill_json!!.img_path.isNotBlank())
            img_path = IMG_URL + pill_json.img_path + IMG_EXTENSION

        var description = pill_json.description.joinToString("\n")

        if(description.isBlank())
            description = ""

        return Pill(pill_json.id, pill_json.brand_name, pill_json.medical_name, img_path, description)
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