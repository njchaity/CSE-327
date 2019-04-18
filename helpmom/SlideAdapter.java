package helpmom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    String[] slide_headings;
    String[] slide_descs;

    public SlideAdapter(Context context,String [] slide_headings,String [] slide_descs){
        this.context = context;
        this.slide_headings = slide_headings;
        this.slide_descs =slide_descs;

    }
    //Arrays
    public int[] slide_images = {
            R.drawable.stage,
            R.drawable.stage1,
            R.drawable.stage2,
            R.drawable.stage3,
            R.drawable.stage4,
            R.drawable.weight,
            R.drawable.doc,
            R.drawable.test
    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object c) {
        return view == (RelativeLayout) c;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        slideDescription.setMovementMethod(new ScrollingMovementMethod());

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
