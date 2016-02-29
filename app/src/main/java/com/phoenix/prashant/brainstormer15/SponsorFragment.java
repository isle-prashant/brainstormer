package com.phoenix.prashant.brainstormer15;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by PRASHANT on 10-07-2015.
 */
public class SponsorFragment extends Fragment {
    GridView grdv;
    boolean doubleTaptoOpenPressedOnce = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sponsors_fragment,container,false);
        ImageView iv = (ImageView) view.findViewById(R.id.imgtitle);
        doubleTaptoOpenPressedOnce = false;
        iv.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.imsindia.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Title Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView madeeasy = (ImageView) view.findViewById(R.id.madeesy);
        madeeasy.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {

                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.madeeasy.in"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Associate Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView smile = (ImageView) view.findViewById(R.id.smile);
        smile.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.smilefoundationindia.org"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Social Partner", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView vani = (ImageView) view.findViewById(R.id.vani);
        vani.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.vaniinstitute.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Associate Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });
        ImageView ccu = (ImageView) view.findViewById(R.id.ccu);
        ccu.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.imsindia.com"));
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Event Partner", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }


            }
        });

        ImageView idp = (ImageView) view.findViewById(R.id.idp);
        idp.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.idp.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Associate Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView uber = (ImageView) view.findViewById(R.id.uber);
        uber.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.uber.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Travel Partner", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView cmpsfrance = (ImageView) view.findViewById(R.id.cmpsfran);
        cmpsfrance.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.campusfrance.org"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Associate Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView fm = (ImageView) view.findViewById(R.id.fm);
        fm.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.imsindia.com"));
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Radio Partner", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }


            }
        });

        ImageView chemplast = (ImageView) view.findViewById(R.id.chemplst);
        chemplast.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {     Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.chemiplast.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Associate Sponsor", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });

        ImageView t2 = (ImageView) view.findViewById(R.id.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            public boolean doubleTaptoOpenPressedOnce;

            @Override
            public void onClick(View v) {
                if (doubleTaptoOpenPressedOnce) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.telegraphindia.com"));
                    startActivity(intent);
                }
                else {
                    this.doubleTaptoOpenPressedOnce = true;
                    Toast.makeText(getActivity(), "Media Partner", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleTaptoOpenPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });





        return view;
    }
}
