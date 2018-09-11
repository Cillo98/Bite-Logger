package thomascilloni.xyz.bitelogger.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import thomascilloni.xyz.bitelogger.R
import kotlinx.android.synthetic.main.fragment_register.*
import thomascilloni.xyz.bitelogger.util.Constants

class FragmentRegister: Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onStart() {
        super.onStart()

        ctx = activity!!.applicationContext
        mAuth = FirebaseAuth.getInstance()

        initButton()
    }

    private fun initButton() {
        btnRegister.setOnClickListener {

            val email = txtEmail.text.toString()
            val pssw = txtPssw.text.toString()
            val pssw2 = txtConfirmPssw.text.toString()

            if (pssw != pssw2)
                lblErrorMessage.text = getString(R.string.error_pssw_mismatch)

            else {
                // passwords match, check the rest
                if (email.isNotEmpty() && pssw.isNotEmpty()) {
                    // non-empty credentials, try registering

                    // creation and authentication together
                    mAuth.createUserWithEmailAndPassword(email, pssw)
                            .addOnCompleteListener { task ->
                                // no problem
                                if (task.isSuccessful) {
                                    val user = mAuth.currentUser

                                    // user does exist
                                    if (user != null) {
                                        Toast.makeText(ctx, "Registration successful", Toast.LENGTH_LONG).show()
                                        // save the credentials in the shared preferences
                                        saveCredentials(email, pssw)
                                        activity?.finish()

                                    } else
                                        Toast.makeText(ctx, "ERROR: Login failed", Toast.LENGTH_LONG).show()
                                } else
                                    Toast.makeText(ctx, "ERROR: Registration unsuccessful", Toast.LENGTH_LONG).show()
                            }
                } else {
                    // error: some fields are not completed
                    lblErrorMessage.text = getString(R.string.error_empty_fields)
                }
            }
        }
    }

    /**
     * Save the user's credentials in memory.
     * [email] and [password] will be saved in the SharedPreferences
     * under the fields [Constants.PREFS_USERNAME] and [Constants.PREFS_PASSWORD].
     *
     * @param email the authenticated email
     * @param password the password associated with the email
     */
    private fun saveCredentials(email: String, password: String) {
        val prefsEditor = ctx.getSharedPreferences(Constants.PREFS_NAME, 0).edit()

        prefsEditor.putString(Constants.PREFS_USERNAME, email)
        prefsEditor.putString(Constants.PREFS_PASSWORD, password)
        prefsEditor.putBoolean(Constants.PREFS_LOGGED_IN, true)

        prefsEditor.apply()
    }

}