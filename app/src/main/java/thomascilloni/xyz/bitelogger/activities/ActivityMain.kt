package thomascilloni.xyz.bitelogger.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.fragments.FragmentLists
import thomascilloni.xyz.bitelogger.util.Constants


class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLogin()

        // TODO: implement switching fragments
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    /**
     * Check if the user has logged in.
     * The check is done in the sharedPreferences. If the user hasn't logged in yet,
     * the login activity will be launched. If the user has logged in, the login will be checked
     * against Firebase.
     */
    private fun checkLogin() {
        val prefs = applicationContext.getSharedPreferences(Constants.PREFS_NAME, 0) // 0 gives private mode

        // check the shared preferences. Default value for login is false (in case it does not exist)
        if (!prefs.getBoolean(Constants.PREFS_LOGGED_IN, false))
            startActivity(Intent(this@ActivityMain, ActivityLogin::class.java))
        else
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navLogs -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navRecipes -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navLists -> {
                supportFragmentManager.beginTransaction()
                        .add(R.id.listFrame, FragmentLists()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navDiets -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
