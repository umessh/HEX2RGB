package com.juventex.hex2rgb;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText txtHEX;
TextView txtRGB;
TextView tvInsp;
RelativeLayout mLayout;
private static final String HEX_PATTERN = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
private static final int maxColors=255;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		txtHEX = (EditText)findViewById(R.id.txtHex);
		txtRGB = (TextView)findViewById(R.id.txtRgb);
		tvInsp = (TextView)findViewById(R.id.tvInsp);
		txtHEX.setTextColor(Color.WHITE);
		txtRGB.setTextColor(Color.WHITE);
		tvInsp.setTextColor(Color.WHITE);
		//output = (TextView)findViewById(R.id.txt);
		txtHEX.addTextChangedListener(watch);
		
		mLayout = (RelativeLayout)  findViewById(R.id.myRelLayout);
		mLayout.setBackgroundColor(0xFF408DD2);
		
	}
   TextWatcher watch = new TextWatcher(){

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int a, int b, int c) {
		// TODO Auto-generated method stub
		
		//output.setText(s);
		int length = s.length();
		int red=64,green=141,blue=210;
		String temp = s.toString();
		txtRGB.setText("");
		if(length >0 && temp.matches(HEX_PATTERN))
		{
			switch(length)
			{
				case 3: red = Integer.parseInt(temp.substring(0, 1)+temp.substring(0, 1),16);
						green =  Integer.parseInt(temp.substring(1, 2)+temp.substring(1, 2),16);
						blue =  Integer.parseInt(temp.substring(2)+temp.substring(2),16);
						break;
				case 6: red = Integer.parseInt(temp.substring(0, 2),16);
						green =  Integer.parseInt(temp.substring(2, 4),16);
						blue =  Integer.parseInt(temp.substring(4),16);
						break;
			
			}
			/*Log.i("Umesh", red+":"+green+":"+blue);
			Log.i("Umesh", Color.rgb(red, green, blue)+"");*/
			txtRGB.setText(" "+red+","+green+","+blue);
			if(red == 128 && green == 128 && blue == 128){
				txtHEX.setTextColor(Color.BLACK);
				txtRGB.setTextColor(Color.BLACK);
				tvInsp.setTextColor(Color.BLACK);
			}else
			{
				txtHEX.setTextColor(Color.rgb(maxColors-red, maxColors-green, maxColors-blue));
				txtRGB.setTextColor(Color.rgb(maxColors-red, maxColors-green, maxColors-blue));
				tvInsp.setTextColor(Color.rgb(maxColors-red, maxColors-green, maxColors-blue));
			}
			//Toast.makeText(getApplicationContext(), Color.rgb(red, green, blue), Toast.LENGTH_SHORT).show();
		}else
		{
			if(length!=0)
				txtRGB.setText(" INVALID");
			txtHEX.setTextColor(Color.WHITE);
			txtRGB.setTextColor(Color.WHITE);
			tvInsp.setTextColor(Color.WHITE);
		}
		mLayout.setBackgroundColor(Color.rgb(red, green, blue));
	}};
	
	public void copyClip(View v)
	{
		if(txtRGB.getText().length()>0 && !txtRGB.getText().equals(" INVALID"))
		{
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("hex2rgb", txtRGB.getText());
			clipboard.setPrimaryClip(clip);
			Toast.makeText(getApplicationContext(), R.string.copyTextSuccess, Toast.LENGTH_SHORT).show();

		}
	}
}
