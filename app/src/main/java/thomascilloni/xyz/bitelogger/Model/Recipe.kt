package thomascilloni.xyz.bitelogger.Model

import android.graphics.Bitmap

/**
 * Instantiate a new [Recipe] object.
 * A recipe must have at least a name, an author
 * and a preparation time.
 *
 * @param id the ID of the recipe
 * @param name of the recipe / dish
 * @param author_id pointer to the author
 * @param time the execution time in minutes
 * @param instructions a series of instructions, separated by triple asterisks
 * @param picture an image of the final product
 *
 */
data class Recipe (
        val id: Int?,
        val name: String,
        val author_id: Int,
        val time: Int,
        val instructions: String?,
        val picture: Bitmap?
)