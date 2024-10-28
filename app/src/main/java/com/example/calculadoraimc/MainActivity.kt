package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btwCalculator: ImageButton
    private lateinit var txtPeso: EditText
    private lateinit var txtAltura: EditText
    private lateinit var txtResultant: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btwCalculator = findViewById(R.id.imgBtCalcular)
        txtResultant = findViewById(R.id.txtResultado)
        txtPeso= findViewById(R.id.textPeso)
        txtAltura= findViewById(R.id.textAltura)


        btwCalculator.setOnClickListener{


            if(txtPeso.text.trim().isNotEmpty() && txtAltura.text.trim().isNotEmpty()){
            val resultant=calcular()

                when (resultant) {
                    in 1.0..18.5 -> {
                        txtResultant.setBackgroundColor(Color.BLUE) // Color Azul
                    }
                    in 18.6..24.9 -> {
                        txtResultant.setBackgroundColor(Color.GREEN) // Color Verde
                    }
                    in 25.0..29.9 -> {
                        txtResultant.setBackgroundColor(getColor(R.color.orange)) // Color Naranja

                    }
                    in 30.0..34.9 -> {txtResultant.setBackgroundColor(Color.YELLOW)}
                    else -> {
                        txtResultant.setBackgroundColor(Color.RED) // Color por defecto si no entra en los rangos
                    }
                }
                val resul= String.format("%.1f",resultant)
                txtResultant.text= "tu IMC es $resul"

            }


            else{
                showAlert()

            }
        }






    }

   private fun calcular(): Double
    {



        val peso:String=txtPeso.text.toString()
        val pesoDouble: Double= peso.toDouble()

        val altura:String=txtAltura.text.toString()
        val alturaDouble: Double= altura.toDouble()


        val resultado: Double= (pesoDouble/(alturaDouble*alturaDouble))*10000


        return resultado

    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Campos obligatorios")
        builder.setMessage("Debe rellenar el peso en kg y la altura en cm")

        // Agregar botones
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface, which: Int ->
            // Acción al presionar Aceptar
        }


        // Crear y mostrar el diálogo
        val alertDialog = builder.create()
        alertDialog.show()
    }


}