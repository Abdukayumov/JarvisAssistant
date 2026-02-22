package com.jarvis;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        Button speakButton = new Button(this);
        speakButton.setText("Jarvisni uyg'ot");

        layout.addView(speakButton);
        setContentView(layout);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        speakButton.setOnClickListener(v -> {
            tts.speak("Salom, men Jarvisman. Sizga qanday yordam beray?",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null);
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.shutdown();
        }
        super.onDestroy();
    }
}