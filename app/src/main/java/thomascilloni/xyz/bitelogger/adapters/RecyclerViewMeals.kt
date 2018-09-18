package thomascilloni.xyz.bitelogger.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_row_meal.view.*
import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.model.Meal

class RecyclerViewMeals(var context: Context, var dataset: List<Meal>)
    : RecyclerView.Adapter<RecyclerViewMeals.ViewHolder>() {

    private lateinit var recyclerViewDishesAdapter: RecyclerViewDishes


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_row_meal, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get the item to display
        val meal = dataset[position]

        // prepare the recycler view
        holder.recyclerViewDishes.hasFixedSize()
        holder.recyclerViewDishes.layoutManager = LinearLayoutManager(context)

        // prepare the recycler view adapter
        recyclerViewDishesAdapter = RecyclerViewDishes(context, meal.dishes)
        holder.recyclerViewDishes.adapter = recyclerViewDishesAdapter
        recyclerViewDishesAdapter.notifyDataSetChanged()

        // update the fields in the view (row by row) with the actual data
        holder.lblName.text = meal.type
        //holder.recyclerViewDishes = RecyclerViewDishes(context)
    }


    inner class ViewHolder(var view: View, ctx: Context)
        : RecyclerView.ViewHolder(view) {

        // ROW VIEWS
        val lblName = view.lblMealName!!
        val recyclerViewDishes = view.recViewDishes!!

        init {
            // update outer context
            context = ctx
        }
    }
}

/*
HOW TO MAKE A RECYCLER VIEW

must create an inner ViewHolder class that contains all views of the rows;
the inner class in the init method must update the context of the outer class;
the ViewHolder inner class connects the Java code with the XML elements

onCreateViewHolder must inflate the layout and return the inner ViewHolder

onBindViewHolder must update the fields in the ViewHolder

IN THE ACTIVITY:
- set fixed size
 */