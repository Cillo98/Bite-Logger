package thomascilloni.xyz.bitelogger.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import thomascilloni.xyz.bitelogger.fragments.FragmentLogin
import thomascilloni.xyz.bitelogger.fragments.FragmentRegister
import thomascilloni.xyz.bitelogger.R

class ActivityLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (fragment_container != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null)
                return

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, FragmentLogin()).commit()
        }

        btnSignUp.setOnClickListener { toFragment(FragmentRegister()) }
        btnSignIn.setOnClickListener { toFragment(FragmentLogin()) }

    }

    private fun toFragment(fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, fragment)
        trans.addToBackStack(null) // required for future development
        trans.commit()
    }
}