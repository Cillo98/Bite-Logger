package thomascilloni.xyz.bitelogger.Model

/**
 * Instantiate a new [Ingredient] object.
 * [Ingredient]s are the data representative of the relation
 * in between the [Food] and [Recipe] relations to solve their
 * many-to-many relationship. Ingredients connect a [Food] with
 * a [Recipe] and must have a specified quantity.
 *
 * @param food_id the [Food] to connect to
 * @param recipe_id the [Recipe] to connect to
 * @param quantity the quantity of the ingredient in grams
 */
data class Ingredient (
    val food_id: Int,
    val recipe_id: Int,
    val quantity: Int
)