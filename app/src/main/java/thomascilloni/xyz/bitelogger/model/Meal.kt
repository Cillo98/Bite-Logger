package thomascilloni.xyz.bitelogger.model

import android.graphics.Bitmap

/**
 * Instantiate a new [Meal] object.
 * A meal must have at least a date and the type
 * (breakfast, lunch, dinner, snack). The liking
 * of a meal is expressed with a score from 1 to 5.
 * [Meal]s are connected to a number of [Food]s through
 * the [Dish] class.
 *
 * @param type which meal it is
 * @param date when it was eaten
 * @param dishes list of dishes included in this meal
 * @param liking a number from 1 to 5
 * @param picture an image of the entire meal for future reference
 *
 */
data class Meal (
        val type: String,
        val date: Long,
        val dishes: List<Dish>,
        val liking: Int?,
        val picture: Bitmap?
)