package com.example.swifttechinternship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.example.swifttechinternship.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var Password: String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
        usernameFocusListener()
        passwordFocusListener()
        phoneFocusListener()

        binding.btnValidate.setOnClickListener { submitForm() }
    }

    private fun resetForm() {
      var   message = "Username: " + binding.usernameEditText.text
            message += "\nPassword: " + binding.passwordEditText.text
            message += "\nPhone: " + binding.phoneEditText.text

        AlertDialog.Builder(this)
            .setTitle("Form Submitted")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                binding.usernameEditText.text == null
                binding.passwordEditText.text == null
                binding.phoneEditText.text == null

//                binding.username.helperText = getString(R.string.required)
//                binding.password.helperText = getString(R.string.required)
//                binding.phone.helperText = getString(R.string.required)
            }
            .show()

    }

    private fun invalidForm() {
        var message = ""
       if ( binding.username.helperText != null)
           message += "\n\nUsername: " + binding.username.helperText
        if ( binding.password.helperText != null)
            message += "\n\nPassword: " + binding.password.helperText
        if ( binding.phone.helperText != null)
            message += "\n\nPhone: " + binding.phone.helperText

        AlertDialog.Builder(this)
            .setTitle("invalidForm")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                //do nothing
            }
            .show()

    }

    private fun submitForm() {

        binding.username.helperText = validUsername()
        binding.password.helperText = validPassword()
        binding.phone.helperText = validPhone()

        val validUsername = binding.username.helperText== null
        val validPassword = binding.password.helperText== null
        val validPhone = binding.phone.helperText== null

        if (validUsername && validPassword && validPhone)
            resetForm()
            else
                invalidForm()


    }

    private fun usernameFocusListener() {
        binding.usernameEditText.setOnFocusChangeListener { _, focused ->
        if(!focused){
           binding.username.helperText = validUsername()
            
        }
        }
    }

    private fun validUsername(): String? {
        val usernameText = binding.usernameEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(usernameText).matches()){
            return "Invalid Username"
        }
    return null
    }

    private fun passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.password.helperText = validPassword()

            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.passwordEditText.text.toString()
        if (passwordText.length < 8){
            return "Minimum 8 character password"
        }
        return null
    }

    private fun phoneFocusListener() {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.phone.helperText = validPhone()

            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding.phoneEditText.text.toString()
        if (!phoneText.matches(".*[0-9]_*".toRegex())){
            return "Must be all digits"
        }
        if (phoneText.length !=10){
            return "Must be 10 digits"
        }
        return null
    }

}