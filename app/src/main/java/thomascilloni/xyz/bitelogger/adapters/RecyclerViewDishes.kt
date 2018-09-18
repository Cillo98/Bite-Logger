package thomascilloni.xyz.bitelogger.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.recyclerview_row_dish.view.*
import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.model.Dish
import thomascilloni.xyz.bitelogger.model.Food

//@Suppress("UNCHECKED_CAST")
class RecyclerViewDishes(var context: Context, var dataset: List<Dish>)
    :RecyclerView.Adapter<RecyclerViewDishes.ViewHolder>() {

    lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewDishes.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_row_dish, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: RecyclerViewDishes.ViewHolder, position: Int) {
        // get the item to display
        val dish = dataset[position]
        val qnt = dish.quantity
        val macros = dish.macros

        // update the fields in the view (row by row) with the actual data
        holder.lblName.text = dish.name
        holder.lblQuantity.text = qnt.toString()+" gr"
        holder.lblCarbohydrates.text = "Carbs: "+(macros[0] * qnt/100).toInt().toString()
        holder.lblProteins.text = "Proteins: "+(macros[1] * qnt/100).toInt().toString()
        holder.lblFats.text = "Fats: "+(macros[2] * qnt/100).toInt().toString()

        holder.chart.setData(macros)
    }




    inner class ViewHolder(var view: View, ctx: Context)
        :RecyclerView.ViewHolder(view), View.OnClickListener {

        // ROW VIEWS
        val lblName = view.lblName!!
        val lblQuantity = view.lblQuantity!!
        val lblCarbohydrates = view.lblCarbohydrates!!
        val lblProteins = view.lblProteins!!
        val lblFats = view.lblFats!!
        val chart = view.nutritionChart!!

        init {
            // update the context of the outer class
            context = ctx
        }

        override fun onClick(v: View) {
            // a dish was clicked...
            val position = adapterPosition

            when (v.id) {
                // cases for ID, that is buttons or graphs clicked
            }

            Toast.makeText(context, "You have clicked ${dataset[position].name}", Toast.LENGTH_SHORT).show()
        }

    }
}

/*
package cillo98.github.com.grocerylist.MainModule.Adapter

// Layouts and Views
import android.app.AlertDialog
import android.app.Dialog
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// Project classes from different directories
import cillo98.github.com.grocerylist.MainModule.Data.Database
import cillo98.github.com.grocerylist.MainModule.Data.Grocery
import cillo98.github.com.grocerylist.MainModule.R
import cillo98.github.com.grocerylist.MainModule.Util.Dates
// Import layout's IDs
import kotlinx.android.synthetic.main.popup_delete_confirmation.view.*
import kotlinx.android.synthetic.main.popup_details.view.*
import kotlinx.android.synthetic.main.recview_row.view.*
// Support imports & Extra
import android.content.Context
import android.widget.Toast


/**
 * Adapter class for the Recycler View of Groceries.
 * This class extends the standard RecyclerView Adapter.
 * It provides methods to check the size of the list of items,
 * remove an item from the list and restore an item to the list.
 * It requires the recview_row XML file.
 *
 * @param context context in which the RecyclerView is placed
 * @param groceryList mutable list of items representing the dataset to display
 */
class RecViewAdapter(var context: Context, var groceryList: MutableList<Grocery>):
        RecyclerView.Adapter<RecViewAdapter.ViewHolder>() {

    // used many times for confirmation menus
    lateinit var alertDialogBuilder: AlertDialog.Builder
    lateinit var inflater: LayoutInflater
    lateinit var dialog: Dialog


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recview_row, parent, false)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RecViewAdapter.ViewHolder, position: Int) {
        // get the grocery to display
        val grocery = groceryList[position]

        // update the fields in the view (row by row) with the actual data
        holder.txtName.text = grocery.name
        holder.txtQuantity.text = grocery.quantity.toString()
        holder.txtExpiration.text = Dates.millisToText(grocery.dateExpires)
    }

    /**
     * Get the number of elements currently in the adapter's dataset
     *
     * @return Int: the dataset's size
     */
    override fun getItemCount(): Int {
        return groceryList.size
    }

    /**
     * Remove an item from the dataset given its index.
     * The item is removed from this adapter's dataset and the change is then notified
     * to update the view.
     *
     * @param index the index of the item to remove
     */
    fun removeItem(index: Int) {
        groceryList.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, groceryList.size)
    }

    /**
     * Reinsert an item into the adapter at a specified index.
     * A grocery item can be reinserted into this adapter's dataset at the position
     * it was removed from, provided that its previous position was saved outside this
     * class and here passed as argument.
     *
     * @param grocery the item to reinsert
     * @param index the index where to insert the item
     */
    fun restoreItem(grocery: Grocery, index: Int) {
        groceryList.add(index, grocery)
        notifyItemInserted(index)
    }


    /**
     * Inner ViewHolder class inside the Adapter.
     * This class manages the entire view of each single row. It manages
     * the behaviours of the buttons, all views and actions. The outer class
     * takes care of populating the fields with the values from the dataset's items,
     * this is where each view/row is built.
     */
    inner class ViewHolder(var view: View, ctx: Context):
            RecyclerView.ViewHolder(view), View.OnClickListener {


        // views and buttons for each row
        val txtName = view.name!!
        val txtQuantity = view.quantity!!
        val txtExpiration= view.expiration!!
        private val btnEdit = view.editButton!!
        private val btnDelete = view.deleteButton!!
        //val id: Int? = null                                               IS THIS NECESSARY?

        val viewForeground = view.viewForeground!!  // white normally visible row
        val viewBackground = view.viewBackground!!  // red/green background of the row

        init {
            // update the context of the outer class
            context = ctx

            // give the two buttons listeners // TO BE EDITED
            btnEdit.setOnClickListener(this)
            btnDelete.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // this represents which row was clicked
            val position = adapterPosition

            // switch case
            when (v.id) {
                R.id.editButton -> editElement(groceryList[position])
                R.id.deleteButton -> deleteElement(groceryList[position])
                else -> Toast.makeText(context, "Button click not recognized", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * Open a dialog to edit an item.
         * A new dialog will open with editable fields for name, quantity and expiration date.
         * Changes can be then either saved or discarded.
         *
         * @param grocery the item to edit
         */
        private fun editElement(grocery: Grocery) {
            // prepare the dialog to be displayed
            alertDialogBuilder = AlertDialog.Builder(context)
            inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.popup_details, null)

            // find all fields in the view...
            val txtPopupName        = view.txtPopupName
            val txtPopupQuantity    = view.txtPopupQuantity
            val txtPopupDateAdded   = view.txtPopupDateAdded
            val txtPopupExpiration  = view.txtPopupExpiration
            val btnPopupUpdate      = view.btnPopupUpdate
            val btnPopupDiscard     = view.btnPopupDiscard

            // ... and set them to their values based on the item being edited
            txtPopupName.setText(grocery.name)
            txtPopupQuantity.setText(grocery.quantity.toString())
            txtPopupDateAdded.setText(Dates.millisToText(grocery.dateAdded))
            txtPopupExpiration.setText(Dates.millisToText(grocery.dateExpires))

            // display the dialog
            alertDialogBuilder.setView(view)
            dialog = alertDialogBuilder.create()
            dialog.show()

            // UPDATE button action
            btnPopupUpdate.setOnClickListener {
                val db = Database(context)

                // check that all fields are filled
                if (txtPopupName.text.isNotEmpty() && txtPopupQuantity.text.isNotEmpty() &&
                    txtPopupDateAdded.text.isNotEmpty() && txtPopupExpiration.text.isNotEmpty()) {

                    // TODO: Use calendar popups instead of simple text fields

                    grocery.name = txtPopupName.text.toString()
                    grocery.quantity = Integer.parseInt(txtPopupQuantity.text.toString())
                    // the context is passed so that the method can print a toast error message
                    grocery.dateAdded = Dates.textToMillis(txtPopupDateAdded.text.toString(), context)
                    grocery.dateExpires = Dates.textToMillis(txtPopupExpiration.text.toString(), context)

                    // update the grocery. Conflicts are on name, not on ID. No duplicates allowed
                    db.updateGrocery(grocery)
                    // update the view
                    notifyItemChanged(adapterPosition, grocery)
                }
                else
                    Snackbar.make(view, "Invalid values", Snackbar.LENGTH_LONG).show()

                // close the dialog and give feedback to the user
                dialog.dismiss()
                Snackbar.make(view, "${grocery.name} updated", Snackbar.LENGTH_LONG).show()
            }

            // if the user chooses to discard the updates, just close the dialog
            btnPopupDiscard.setOnClickListener {
                dialog.dismiss()
                Snackbar.make(view, "Changes discarded", Snackbar.LENGTH_LONG).show()
            }

        }

        /**
         * Open a dialog to delete an item.
         * A new dialog will open with two buttons to ask for deletion confirmation
         *
         * @param grocery the item to delete
         */
        private fun deleteElement(grocery: Grocery) {
            // prepare the dialog
            alertDialogBuilder = AlertDialog.Builder(context)
            inflater        = LayoutInflater.from(context)
            // prepare the UI views
            val view        = inflater.inflate(R.layout.popup_delete_confirmation, null)
            val btnYes      = view.btnYes
            val btnNo       = view.btnNo

            // show the dialog
            alertDialogBuilder.setView(view)
            dialog = alertDialogBuilder.create()
            dialog.show()

            // just close the dialog if the user does not want to delete the item
            btnNo.setOnClickListener {
                dialog.dismiss()
            }

            // if the user is sure, delete the item and give him feedback. Also notify the adapter
            btnYes.setOnClickListener {
                val db = Database(context)
                db.deleteGrocery(grocery.name)

                groceryList.remove(grocery)
                notifyItemRemoved(adapterPosition)

                dialog.dismiss()
                Snackbar.make(view, "Item ${grocery.name} removed", Snackbar.LENGTH_LONG).show()
            }
        }

    }
}
 */