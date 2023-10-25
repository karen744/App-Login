package co.edu.udenar.appautenticacion.inicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.edu.udenar.appautenticacion.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class autenticacion : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private val loginButton: Button by lazy {
        findViewById<Button>(R.id.buttonLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticacion)

        mAuth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.EditTextemail)
        passwordEditText = findViewById(R.id.EditTextpassword)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user: FirebaseUser? = mAuth.currentUser
                            if (user != null) {

                                val intent = Intent(this,sesion::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
