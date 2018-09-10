package thomascilloni.xyz.bitelogger.Model

/**
 * Instantiate a new [Food] object.
 * A food's basic information include its name and
 * its nutritional values per 100gr.
 *
 * @param id ID of the food in the database
 * @param name name of the food (including flavours and customizations)
 * @param brand brand of the food (sub-brands must be included)
 * @param cooked true if the food is cooked, false if raw
 * @param calories for every 100gr of product
 * @param carbohydrates for every 100gr of product
 * @param proteins for every 100gr of product
 * @param fats for every 100gr of product
 * @param contains a list of allergens
 *
 */
data class Food (
        val id: Int?,
        val name: String,
        val brand: String?,
        val cooked: Boolean?,
        val calories: Int,
        val carbohydrates: Int,
        val proteins: Int,
        val fats: Int,
        val contains: String?
)