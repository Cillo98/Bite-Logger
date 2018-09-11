package thomascilloni.xyz.bitelogger.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import thomascilloni.xyz.bitelogger.R

/**
 * A simple [Fragment] subclass to display recipes.
 * Use the [FragmentRecipes.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentRecipes : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    companion object {
        /**
         * Factory method to create a new instance of
         * this recipes fragment. Can add parameters.
         *
         * @param param A [String] with data to process
         * @return A new instance of fragment [FragmentRecipes].
         */
        fun newInstance(param: String?) = FragmentRecipes()
    }
}
