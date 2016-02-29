package com.phoenix.prashant.brainstormer15;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.special.ResideMenu.ResideMenu;

import java.util.HashMap;

/**
 * Created by PRASHANT on 25-06-2015.
 */
public class GalleryFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    private View parentView;
    private ResideMenu resideMenu;
    String comment [];
    int img[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.gallery_fragment, container, false);
        mDemoSlider = (SliderLayout)parentView.findViewById(R.id.slider);
        setUpViews();

        comment = new String[]{"Mr. Barry Inaugarating Brainstormer 2014","Memories Of Last Year","Participants of Brainstormer 2014","Our beloved Seniors","A small token of love from Mr. Barry","Registration At Ahindra Munch","Prize Distribution Ceremony","Audience Of Brainstormer 2014","Inauguration Of Brainstormer 2014","Qualifier Teams of Brainstormer"};
        img = new int[]{R.drawable.ic_g1,R.drawable.ic_g2,R.drawable.ic_g3,R.drawable.ic_g4,R.drawable.ic_g6,R.drawable.ic_g8
                ,R.drawable.ic_g9,R.drawable.ic_g10,R.drawable.ic_g11,R.drawable.ic_g12};


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        for (int i=0;i<img.length;i++){
            String commment = comment[i];
            int imgid= img[i];
            file_maps.put(commment,imgid);
        }

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
//                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            if (textSliderView!=null)
                mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(6000);
        mDemoSlider.addOnPageChangeListener(this);
        return parentView;




    }

    private void setUpViews() {
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
        resideMenu.addIgnoredView(ignored_view);
        // add gesture operation's ignored view
    }


    @Override
    public void onDestroy() {
        mDemoSlider.stopAutoCycle();
        super.onDestroy();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}


