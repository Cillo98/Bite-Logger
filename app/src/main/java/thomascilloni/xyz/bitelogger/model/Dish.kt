package thomascilloni.xyz.bitelogger.model

/**
 * Instantiate a new [Dish] object.
 * [Dish]es are the data representative of the relation
 * in between the [Food] and [Meal] relations to solve their
 * many-to-many relationship. Dishes connect a [Food] with
 * a [Meal] and must have a specified quantity.
 *
 * @param food_id the [Food] to connect to
 * @param meal_id the [Meal] to connect to
 * @param quantity the quantity of the food in grams
 */
data class Dish (
        val food_id: Int,
        val meal_id: Int,
        val quantity: Int
)