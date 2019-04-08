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
	//声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    /**
    *  初始化定位并设置定位回调监听
    */
    private void getCurrentLocationLatLng(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
       //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    
      
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    /**
     * 定位回调监听器
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
		// 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
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
				// 等待证书响应
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
