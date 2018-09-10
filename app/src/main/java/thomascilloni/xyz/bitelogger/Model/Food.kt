package thomascilloni.xyz.bitelogger.Model

import android.graphics.Bitmap

/**
 * Instantiate a new [Food] object.
 * A food's basic information include its name, the author and
 * its nutritional values per 100gr.
 * [Food]s can be represent either single [Ingredient]s or complete Dishes.
 * Foods can additionally have a brand, a list of allergens, instructions
 * on how to be made, a state (raw or cooked/prepared) and a picture.
 * When a new dish is created, its nutritional values must be calculated
 * from the single ingredients
 *
 *
 * @param id ID of the food in the database
 * @param name name of the food (including flavours and customizations)
 * @param author_name the creator of the entry
 * @param brand brand of the food (sub-brands must be included)
 * @param cooked true if the food is cooked / ready, false if it needs processing / is raw
 * @param calories for every 100gr of product
 * @param carbohydrates for every 100gr of product
 * @param proteins for every 100gr of product
 * @param fats for every 100gr of product
 * @param contains a list of allergens
 * @param instructions a list of steps to prepare the food
 * @param picture an image of the product
 *
 */
data class Food (
        val id: Int?,
        val name: String,
        val author_name: String?,
        val brand: String?,
        val cooked: Boolean?,
        val calories: Long,
        val carbohydrates: Long,
        val proteins: Long,
        val fats: Long,
        val contains: String?,
        val instructions: String?,
        val picture: Bitmap?
)