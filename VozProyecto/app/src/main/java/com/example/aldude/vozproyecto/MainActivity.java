package com.example.aldude.vozproyecto;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


//Al crear el activity, por defecto nos viene "public class MainActivity extends AppCompatActivity"
//Por ende al agregarle la línea "implements TextToSpeech.OnInitListener"
// Nos arrojará error pero al pasar el puntero sobre la línea subrayada e ir al "foquito"
//Le damos a "Implement Methods", nos abrirá una nueva ventana en la cual nos saldrá dos métodos que debemos agregar, le damos "Ok" y se solucionará

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {


    //Declaramos variables

    //La clase TextToSpeech será la que usaremos para convertir el texto en sonido
    private TextToSpeech textToSpeech;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaramos los botones de los idiomas enlazándolos con sus respectivos id
        final Button btnSpanish = (Button)findViewById(R.id.btnSpanish);
        final Button btnEnglish = (Button)findViewById(R.id.btnEnglish);

        //Le damos funcionalidad al textToSpeech
        textToSpeech = new TextToSpeech(this, this);

        //Enlazamos el editext donde escribiremos lo que se hablará
        editText = (EditText)findViewById(R.id.editTextWords);


        //Le damos funcionalidad al boton "btnSpanish", esta forma es algo complejo podríamos decir ya que el código no sale de inmediato
        //Empezamos escribiendo o completando btnSpanish seguido del "." y seleccionamos setOnClickListener()
        // dentro del paréntesis escribimos new seguido de View.OnClic... y seleccionamos el OnclickLstener y automaticamente generará
        //El onclick

        btnSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //En esta línea declaramos el lenguaje sea Español de España
                textToSpeech.setLanguage(new Locale("spa", "ESP"));
                //Al escribir speak nos saldrá error al inicio, es porque aun no declaramos ni le damos funcionalidad
                speak(editText.getText().toString());
            }
        });

        //Aquí hacemos lo mismo que en el botón 1 solo que escribiendo btnEnglish
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Como en el primer botón aquí se establece el idioma es Inglés de Estados Unidos
                textToSpeech.setLanguage(new Locale("en","US"));
                speak(editText.getText().toString());
            }
        });

    }

    //Estas líneas son generadas cuando implementamos los dos métodos que nos pide al agregar implements TextToSpeech.OnInitListener en la primera línea de código
    @Override
    public void onInit(int status) {
        if (status ==  TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED){
            Toast.makeText(this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT);
        }
    }


    //Aquí declarmaos qué es la variable speak, que nos daba error en líneas anteriores
    private void speak (String str){
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }

    @Override
    protected void onDestroy(){

        if ( textToSpeech != null )
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
