package com.imanesaurus.xperiasetting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class AboutWindowPop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_window);

        DisplayMetrics disp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(disp);

        int width = disp.widthPixels;
        int height = disp.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        ImageView fblogo = (ImageView)findViewById(R.id.fb_logo);
        fblogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com/saurusboy"));
                startActivity(intent);
            }
        });

        ImageView gplus = (ImageView)findViewById(R.id.gp_logo);
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://plus.google.com/u/1/communities/111393255059076699589"));
                startActivity(intent);
            }
        });

        ImageView github = (ImageView)findViewById(R.id.git_logo);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/imanesaurus"));
                startActivity(intent);
            }
        });

        ImageView twit = (ImageView)findViewById(R.id.twit_logo);
        twit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.twitter.com/pirmansaah"));
                startActivity(intent);
            }
        });

        ImageView xda = (ImageView)findViewById(R.id.xda_logo);
        xda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forum.xda-developers.com/member.php?u=4814483"));
                startActivity(intent);
            }
        });

        ImageView paypal = (ImageView)findViewById(R.id.pay_logo);
        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.paypal.com/us/cgi-bin/webscr?cmd=_flow&SESSION=EWgctWg-nlLYqgfnt7b7LMHiywF8u4Rn4ky3ufegRrBnFQxkC652TKFyCFi&dispatch=5885d80a13c0db1f8e263663d3faee8d99e4111b56ef0eae45e68b8988f5b2dd"));
                startActivity(intent);
            }
        });

    }
}
