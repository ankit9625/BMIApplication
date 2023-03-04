package com.example.bmiapplication
// Here Import Some important Packages
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bmiapplication.databinding.ActivityMainBinding

// This is My Main Class which will be Execute and this inherited properties of Superclass AppCompatActivity
@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Here I Declare ActivityMainBinding Type Reference Variable which Contain All XML File of Components of Objects or Views
    private lateinit var binding:ActivityMainBinding
    // Here I Declare isClear Variable which type is Boolean which contains only true and false value
    private var isClear=true
    // This is Create function which Create Our Activity at run time

    private lateinit var builder:AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Here I initialize My ViewBinding reference with ActivityMainBinding Object
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Here I Declare Button Listener to perform task from button and Here this is Current class object
        binding.btncalclear.setOnClickListener(this)

        builder=AlertDialog.Builder(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.my_option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
// I Use Option Menu Which make my Application Good and excellent
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_app->{
              Toast.makeText(this," About App ",Toast.LENGTH_LONG).show()
            }

            R.id.exit->{
                builder.setTitle("Alert!")
                    .setMessage("Do You Want to Exit ?")
                    .setCancelable(true)
                    .setPositiveButton("Yes"){ _, _ ->
                        finish()
                    }
                    .setNegativeButton("No"){ dialogInterface, _ ->
                        dialogInterface.cancel()

                    }
                    .setNeutralButton("help"){ _, _ ->
                        Toast.makeText(this,"Help",Toast.LENGTH_LONG).show()
                    }

            }
        }
        return super.onOptionsItemSelected(item)
    }
    // Here I Use Code for disable back button Action
    @Deprecated("Deprecated in Java", ReplaceWith("Unit"))
    override fun onBackPressed() = // super.onBackPressed()
        Unit


    // this is Override function which belong into AppCompatActivity Class and This is Click event function
    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
      // Here I Take two conditions
      // if isClear will be true Then condition will be Execute if()
      // and if() is Calculate BMI
      // if isClear will be false Then condition will be Execute else()
      // and else() is Clear All Fields
        if(isClear) {
            // Here Firstly i Will Check All EditText Fields is Empty or Not
            if (binding.edtinputweight.text.toString().isNotEmpty() && binding.edtinputheight.text.toString().isNotEmpty()) {
            // Here I Get input from EditText First
            val weightkg = binding.edtinputweight.text.toString().toDouble()
                if (weightkg.toString().toDouble()==00.00) {
                    Toast.makeText(this, " Please Enter Valid Weight ", Toast.LENGTH_LONG).show()
                    return
                }
                else
                // Here I Use Some Spacial Properties of EditText 1 Like Enabled and Clickable will be false after first time enter Value After Enter Value you Can't Change it
              binding.edtinputweight.isEnabled=false
                binding.edtinputweight.isClickable=false


            // here I Get input from EditText Second
            val heightcm = binding.edtinputheight.text.toString().toDouble()
                if (heightcm.toString().toDouble()==000.00) {
                    Toast.makeText(this, "Please Enter Valid Height ", Toast.LENGTH_LONG).show()
                    return
                }
                else
                // Here I Use Some Spacial Properties of EditText 2 Like Enabled and Clickable will be false after first time enter Value After Enter Value you Can't Change it
                binding.edtinputheight.isEnabled=false
                binding.edtinputheight.isClickable=false
            // Here i Perform a Function to Calculate BMI Given Inputs  and Store it in res variable which is Local type
            val res = calculateBMI(weightkg, heightcm)
            res.toString()
            // Here I chance My input 23.90 This Type Formate
            val formate = "%.2f".format(res)
                // Here I Set My Output on A BMI TextView Fields to Show Output on Activity
                binding.showbmivalue.text = " BMI Value is - $formate"
            when (res) {
                // this is Switch case to check Multiples values
                in 10.00..16.00 -> {
                    binding.showmessage.text = " You are Severely Underweight "
                    Toast.makeText(
                        this,
                        " Suggestion For You - You Need to Gain Good Weight For Your Health to Fit ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 16.00..18.40 -> {
                    binding.showmessage.text = " You are Underweight "
                    Toast.makeText(
                        this,
                        " Suggestion For You - You Need to Gain Weight For Your Health ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 18.50..24.90 -> {
                    binding.showmessage.text = " You are Normal "
                    Toast.makeText(
                        this,
                        " Good News - You are Fit and Healthy. and Keep Your Health Well ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 25.00..29.90 -> {
                    binding.showmessage.text = " You are Overweight "
                    Toast.makeText(
                        this,
                        " Advise For You - You Need to Loose Your Weight to get Your Good Health ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 30.00..34.90 -> {
                    binding.showmessage.text = " You are Moderately Obese "
                    Toast.makeText(
                        this,
                        " Suggestion For You - You Need to Lose Your Weight a Large Scale to get Good Health ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 35.00..39.90 -> {
                    binding.showmessage.text = " You are Severely Obese "
                    Toast.makeText(
                        this,
                        " Suggestion For You - You Have to Loose Your Weight a Large Scale  ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                in 40.00..50.00 -> {
                    binding.showmessage.text = " You are Morbidly Obese "
                    Toast.makeText(
                        this,
                        " Suggestion For You - You Have to Loose Your Weight to get Health and Fitness ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {

                }
            }
                // Here I Set isClear variable value after calculate BMI Value
                isClear=false
                // Here I Set btn Text to show button new text after perform Action
                binding.btncalclear.text = " CLEAR "
        }
        else {
            // This will be show when Our All Fields will be Empty
            if (TextUtils.isEmpty(binding.edtinputweight.text.toString()) &&TextUtils.isEmpty(binding.edtinputheight.text.toString())) {
                Toast.makeText(this, " Please Fill All Fields Very carefully ", Toast.LENGTH_LONG)
                    .show()
            }else if(TextUtils.isEmpty(binding.edtinputweight.text.toString())){
                binding.edtinputweight.requestFocus()
                    Toast.makeText(this, " Please Enter Weight Very Carefully ", Toast.LENGTH_LONG)
                        .show()
                }else if(TextUtils.isEmpty(binding.edtinputheight.text.toString())){
                    binding.edtinputheight.requestFocus()
                    Toast.makeText(this, " Please Enter Height Very Carefully ", Toast.LENGTH_LONG)
                        .show()
                }
        }

        }

        // This Block will be Call when our button perform Clear Action
        else{
            binding.edtinputweight.text.clear()
            binding.edtinputheight.text.clear()
            binding.showbmivalue.text = " "
            binding.showmessage.text = " "
            isClear=true
            binding.btncalclear.text = " CALCULATE "
            // Here I Use Some Spacial Properties of EditText 1 Like Enabled and Clickable will be true after Action Clear You can Use again
            binding.edtinputweight.isEnabled=true
            binding.edtinputweight.isClickable=true
            // Here I Use Some Spacial Properties of EditText 2 Like Enabled and Clickable will be true after Action Clear You can Use again
            binding.edtinputheight.isEnabled=true
            binding.edtinputheight.isClickable=true
        }
    }

}
// this is Calculation function
fun calculateBMI(w:Double,h:Double):Double{
    val cmtocsqr=h/100
    return w/(cmtocsqr*cmtocsqr)
}