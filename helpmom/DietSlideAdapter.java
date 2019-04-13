package com.example.t_tiger.helpmom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by T-Tiger on 3/21/2018.
 */

public class DietSlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    String[] slide_headings2;
    String[] slide_descs2;


    public DietSlideAdapter(Context context,String [] slide_headings2,String [] slide_descs2 ){
        this.context = context;
        this.slide_headings2 = slide_headings2;
        this.slide_descs2 = slide_descs2;
    }


    //Arrays
    public int[] slide_images2 = {
            R.drawable.breakfast,
            R.drawable.cabbage,
            R.drawable.protein,
            R.drawable.fish,
            R.drawable.tea

    };





    @Override
    public int getCount() {
        return slide_headings2.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide2, container, false);

        ImageView slideImageView2 = (ImageView) view.findViewById(R.id.slideImage2);
        TextView slideHeading2 = (TextView) view.findViewById(R.id.slide_heading2);
        TextView slideDescription2 = (TextView) view.findViewById(R.id.slide_desc2);

        slideImageView2.setImageResource(slide_images2[position]);
        slideHeading2.setText(slide_headings2[position]);
        slideDescription2.setText(slide_descs2[position]);
        slideDescription2.setMovementMethod(new ScrollingMovementMethod());


        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
