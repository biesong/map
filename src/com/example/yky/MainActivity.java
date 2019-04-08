package com.example.yky;



import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.util.PropertiesUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {
	//����AMapLocationClient�����
    AMapLocationClient mLocationClient = null;
    //����AMapLocationClientOption����
    public AMapLocationClientOption mLocationOption = null;

    /**
    *  ��ʼ����λ�����ö�λ�ص�����
    */
    private void getCurrentLocationLatLng(){
        //��ʼ����λ
        mLocationClient = new AMapLocationClient(getApplicationContext());
       //���ö�λ�ص�����
        mLocationClient.setLocationListener(mLocationListener);
        //��ʼ��AMapLocationClientOption����
        mLocationOption = new AMapLocationClientOption();

        // ͬʱʹ�����綨λ��GPS��λ,���ȷ�����߾��ȵĶ�λ���,�Լ���Ӧ�ĵ�ַ������Ϣ
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    
      
        mLocationClient.setLocationOption(mLocationOption);
        //������λ
        mLocationClient.startLocation();
    }
    /**
     * ��λ�ص�������
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
        	if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                   
                    TextView tv=(TextView)findViewById(R.id.address);
                    tv.setText(amapLocation.getAddress());
             
                } else {
                
                }
            }

        	 
        }
                 
             
   
    };
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getCurrentLocationLatLng();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setContentView(R.layout.user);
		WebView webView = (WebView) findViewById(R.id.webView);
		webView.loadUrl(PropertiesUtil.getValueByKey("url"));
		WebSettings settings = webView.getSettings();
		// ������ʵ�ҳ����Ҫ��Javascript��������webview��������֧��Javascript
		settings.setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				// �ȴ�֤����Ӧ
				handler.proceed();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			
			break;
		case R.id.action_index:
			Intent user = new Intent(MainActivity.this, MainActivity.class);
			startActivity(user);
		default:
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
}
