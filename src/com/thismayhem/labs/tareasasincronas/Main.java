package com.thismayhem.labs.tareasasincronas;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.botonIniciarCuenta).setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.botonIniciarCuenta){
			new miTareaAsincrona().execute(0);
		}
	}
   
	class miTareaAsincrona extends AsyncTask<Integer,Integer,Integer>{
		@Override
		protected synchronized Integer doInBackground(Integer... params) {
			for(int i=5;i>=0;i--){
				try {
					wait(1000);
					this.publishProgress(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer...progreso){
			TextView miProgreso = (TextView) findViewById(R.id.cuentaAtras);
			miProgreso.setText(progreso[0]+"");
		}
	}

}
