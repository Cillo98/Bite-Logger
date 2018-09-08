package thomascilloni.xyz.bitelogger.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.Util.Constants


class ActivityMain : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navLogs -> {
                message.setText(R.string.title_logs)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navRecipes -> {
                message.setText(R.string.title_recipes)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navLists -> {
                message.setText(R.string.title_lists)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navDiets -> {
                message.setText(R.string.title_diets)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLogin()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    /**
     * Check if the user has logged in.
     * The check is done in the sharedPreferences. If the user hasn't logged in yet,
     * the login activity will be launched. If the user has logged in, the login will be checked
     * against Firebase.
     */
    private fun checkLogin() {

        // 0 gives private mode
        val prefs = applicationContext.getSharedPreferences(Constants.PREFS_NAME, 0)

        // check the shared preferences. Default value for login is false (in case it does not exist)
        if (!prefs.getBoolean(Constants.PREFS_LOGGED_IN, false))
            startActivity(Intent(this@ActivityMain, ActivityLogin::class.java))

    }
}
