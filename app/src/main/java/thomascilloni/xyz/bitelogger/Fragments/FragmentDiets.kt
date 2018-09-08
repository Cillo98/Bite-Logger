package thomascilloni.xyz.bitelogger.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import thomascilloni.xyz.bitelogger.R

/**
 * A simple [Fragment] subclass to display diets.
 * Use the [FragmentDiets.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentDiets : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diets, container, false)
    }

    companion object {
        /**
         * Factory method to create a new instance of
         * this diets fragment. Can add parameters.
         *
         * @param param A [String] with data to process
         * @return A new instance of fragment [FragmentDiets].
         */
        fun newInstance(param: String?) = FragmentDiets()
    }
}
