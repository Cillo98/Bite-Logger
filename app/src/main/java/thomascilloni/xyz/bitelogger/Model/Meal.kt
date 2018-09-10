package thomascilloni.xyz.bitelogger.Model

import android.graphics.Bitmap

/**
 * Instantiate a new [Meal] object.
 * A meal must have at least a date and the type
 * (breakfast, lunch, dinner, snack). The liking
 * of a meal is expressed with a score from 1 to 5.
 *
 * @param id the ID of the meal
 * @param type which meal it is
 * @param date when it was eaten
 * @param liking a number from 1 to 5
 * @param picture an image of the entire meal for future reference
 *
 */
data class Meal (
        val id: Int?,
        val type: String,
        val date: Long,
        val liking: Int?,
        val picture: Bitmap?
)