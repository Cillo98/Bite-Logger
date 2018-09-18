package thomascilloni.xyz.bitelogger.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_lists.*

import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.adapters.RecyclerViewMeals
import thomascilloni.xyz.bitelogger.model.Dish
import thomascilloni.xyz.bitelogger.model.Food
import thomascilloni.xyz.bitelogger.model.Meal

/**
 * A simple [Fragment] subclass to display lists (shopping / pantry).
 * Use the [FragmentLists.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentLists : Fragment() {

    private val TAG = "FragmentList"

    private lateinit var ctx: Context
    private lateinit var recViewMealsAdapter: RecyclerViewMeals

    lateinit var meals: List<Meal>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onStart() {
        super.onStart()
        ctx = activity!!.applicationContext
        // TODO: do stuff

        createSomeFoods()


        // set up the recycler list view
        recViewMeals.hasFixedSize()
        recViewMeals.layoutManager = LinearLayoutManager(ctx)

        // and its adapter
        recViewMealsAdapter = RecyclerViewMeals(ctx, meals)
        recViewMeals.adapter = recViewMealsAdapter
        recViewMealsAdapter.notifyDataSetChanged()

        Log.d(TAG, "Created the meal list adapter bla bla")

    }

    private fun createSomeFoods() {
        val food1 = Food (
                "carrots",
                null, null, null,
                30f, 3f, 0f,
                null, null, null
        )
        val food2 = Food (
                "milk",
                null, null, null,
                15f, 10f, 1f,
                null, null, null
        )
        val food3 = Food (
                "avocado",
                null, null, null,
                10f, 5f, 30f,
                null, null, null
        )
        val food4 = Food (
                "egg white",
                null, null, null,
                2f, 30f, 0f,
                null, null, null
        )

        val dishList1 = listOf<Dish>(
                Dish(food1, 10),
                Dish(food2, 100),
                Dish(food3, 30),
                Dish(food1, 2)
        )
        val dishList2 = listOf<Dish>(
                Dish(food3, 185),
                Dish(food2, 31),
                Dish(food4, 340)
        )

        val meal1 = Meal(
                "Breakfast",
                System.currentTimeMillis(),
                dishList1,
                null, null
        )
        val meal2 = Meal(
                "Lunch",
                System.currentTimeMillis(),
                dishList2,
                null, null
        )

        meals = listOf(meal1, meal2)

        Log.d(TAG, "Created list of meals")
    }

    companion object {
        /**
         * Factory method to create a new instance of
         * this lists fragment. Can add parameters.
         *
         * @param param A [String] with data to process
         * @return A new instance of fragment [FragmentLists].
         */
        fun newInstance(param: String?) = FragmentLists()
    }
}
