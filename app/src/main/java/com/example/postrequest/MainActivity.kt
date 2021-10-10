package com.example.postrequest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var responseText: TextView
    private lateinit var buttonadd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseText = findViewById(R.id.text1)
        buttonadd = findViewById(R.id.button1)

        val apiinter = APIClient().getClient()?.create(APIInterface::class.java)

        if (apiinter != null) {
            apiinter.getUser()?.enqueue(object : Callback<List<Users.UserDeta>> {
                override fun onResponse(
                    call: Call<List<Users.UserDeta>>,
                    response: Response<List<Users.UserDeta>>
                ) {
                    var data: String? = "";
                    for (User in response.body()!!) {
                        data = data + "${User.name} \n ${User.location} \n \n"
                    }
                    responseText.text = data
                }
                override fun onFailure(call: Call<List<Users.UserDeta>>, th: Throwable) {
                    Toast.makeText(applicationContext, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                }
            })
        }
        buttonadd.setOnClickListener {
            intent = Intent(applicationContext, Main2::class.java)
            startActivity(intent)
        }
    }
}