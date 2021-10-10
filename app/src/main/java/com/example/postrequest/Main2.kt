package com.example.postrequest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Main2 : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var location: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        name = findViewById(R.id.Name)
        location = findViewById(R.id.Location)
        buttonSave = findViewById(R.id.buttonsave)
        buttonView = findViewById(R.id.buttonview)

        buttonSave.setOnClickListener {

            var f = Users.UserDeta(name.text.toString(), location.text.toString())
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

            if (apiInterface != null) {
                apiInterface.addUser(f).enqueue(object : Callback<Users.UserDeta> {
                    override fun onResponse(
                        call: Call<Users.UserDeta>,
                        response: Response<Users.UserDeta>
                    ) {
                        name.setText("")
                        location.setText("")
                        Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT)
                            .show();
                    }
                    override fun onFailure(call: Call<Users.UserDeta>, t: Throwable) {
                        name.setText("")
                        location.setText("")
                        Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();

                    }
                })
            }


        }

        buttonView.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }


}