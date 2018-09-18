package thomascilloni.xyz.bitelogger.model

/**
 * Instantiate a new [Dish] object.
 * [Dish]es are the data representative of the relation
 * in between the [Food] and [Meal] relations to solve their
 * many-to-many relationship. Dishes connect a [Food] with
 * a [Meal] and must have a specified quantity.
 *
 * @param name the name of the food
 * @param macros a list of macros per 100gr of the food
 * @param quantity the quantity of the food in grams
 */
data class Dish (
        val name: String,
        val macros: FloatArray,
        val quantity: Int
) {

    constructor(food: Food, qnt: Int) :
            this (  food.name,
                    floatArrayOf(
                            food.carbohydrates,
                            food.proteins,
                            food.fats),
                    qnt
            )
}