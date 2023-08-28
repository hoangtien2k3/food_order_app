package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sử dụng view by binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        singin_form();
        referenceSignUpAccount()
    }

    private fun referenceSignUpAccount() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val username = binding.txtUsernameAndEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            val confirmPassword = binding.txtConfirmPassword.text.toString()
            val nextSignIn = binding.txtSignInApp.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.input_full_information), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!confirmPassword.equals(password)) {
                Toast.makeText(this, resources.getString(R.string.password_failed), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, resources.getString(R.string.password_failed), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, resources.getString(R.string.input_full_username_and_password), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun singin_form(){
        binding.txtSignInApp.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

}