package thomascilloni.xyz.bitelogger.model

/**
 * Instantiate a new [Ingredient] object.
 * [Ingredient]s solve the many-to-many relationship for
 * [Food]s as [Ingredient]s in another [Food].
 * Ingredients must have a specified quantity.
 *
 * @param food_id the [Food] to add to the recipe
 * @param recipe_id the [Food] that this [Food] is part of
 * @param quantity the quantity of the ingredient in grams
 */
data class Ingredient (
    val food_id: Int,
    val recipe_id: Int,
    val quantity: Int
)