package thomascilloni.xyz.bitelogger.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import thomascilloni.xyz.bitelogger.R
import thomascilloni.xyz.bitelogger.Util.Constants

class ActivityLogin : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        initViews()
    }

    private fun initViews() {
        btnLogin.setOnClickListener {
            // reset the error message
            lblErrorMessage.text = ""

            // get the data
            val email = txtEmail.text.toString()
            val pssw = txtPssw.text.toString()

            // TODO: Firebase login here
            if (email.isNotEmpty() && pssw.isNotEmpty()) {
                // attemp login
                mAuth.signInWithEmailAndPassword(email, pssw)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = mAuth.currentUser
                                if (user != null) {
                                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                                    finish()
                                } else
                                    Toast.makeText(this, "ERROR: User null", Toast.LENGTH_LONG).show()
                            } else
                                Toast.makeText(this, "ERROR: Task unsuccessful", Toast.LENGTH_LONG).show()
                        }
            } else {
                lblErrorMessage.text = getString(R.string.error_empty_fields)
            }
        }


        btnRegister.setOnClickListener {

            // hardcode an email and a password
            val email = "myEmail@gmail.com"
            val pssw = "12345678"

            mAuth.createUserWithEmailAndPassword(email, pssw)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            if (user != null) {
                                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                                finish()
                            } else
                                Toast.makeText(this, "ERROR: User null", Toast.LENGTH_LONG).show()
                        } else
                            Toast.makeText(this, "ERROR: Task unsuccessful", Toast.LENGTH_LONG).show()
                    }
        }
    }
}