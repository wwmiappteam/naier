package com.tfsp.toastTest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastTest extends Activity {
	
	private Context context;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnToast = (Button)findViewById(R.id.btn_toast);
        Button btnImageToast = (Button)findViewById(R.id.btn_image_toast);
        Button btnLayToast = (Button)findViewById(R.id.btn_layout_toast);
        
        btnToast.setOnClickListener(new ButtonToastListener());
        btnImageToast.setOnClickListener(new ButtonImageToastListener());
        btnLayToast.setOnClickListener(new ButtonEditTextToastListener());
    }
    
    private class ButtonToastListener 
    				implements Button.OnClickListener{

    	public void onClick(View v) {
    		Toast.makeText(v.getContext(), "文本Toast测试", Toast.LENGTH_LONG).show();
    	}
    }
    
    private class ButtonImageToastListener 
    				implements Button.OnClickListener{

    	public void onClick(View v) {
    		context = v.getContext();
    		Toast toast = new Toast(context); 
    		ImageView ivView = new ImageView(context); 
    		ivView.setImageResource(R.drawable.rabbit); 
    		toast.setView(ivView); 
    		toast.show(); 
    	}
    }
    
    private class ButtonEditTextToastListener 
    				implements Button.OnClickListener{

    	public void onClick(View v) {
    		context = v.getContext();
    		Toast toast = Toast.makeText(context, "这是子Toast", Toast.LENGTH_LONG); 
    		View toastView = toast.getView();   // the key is here
    		
    		LinearLayout lay = new LinearLayout(context); 
    		lay.setOrientation(LinearLayout.HORIZONTAL); 
    		ImageView ivView = new ImageView(context); 
    		ivView.setImageResource(R.drawable.rabbit); 
    		
    		lay.addView(ivView); 
    		lay.addView(toastView); 
    		toast.setView(lay); 
    		toast.show();  
    	}
    }
}