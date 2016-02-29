package com.phoenix.prashant.brainstormer15;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by PRASHANT on 11-07-2015.
 */
public class QuizFragment extends Fragment  {
    private static final String MARKS = "marks_obtained";
    public static String filename = "My Shared Data";
    public static SharedPreferences marks;
    public static String JSONResp = null;
    Button BtSub;
    ListView lv;
    float x = 0;
    public RowItem item;
    int n[];
    int r[];
    String email;
    TextView tv;
    String ip;
    ImageButton btBr;
    LinearLayout errorlayout;
    RelativeLayout Buttomlayout;
    NiftyDialogBuilder dialogBuilder,dialogBuilder1;
    public static RowItem[] item_arr;
    public final List<RowItem> rowItems = new ArrayList<RowItem>();
    private waitasync wa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);
        ip=getResources().getString(R.string.ip);
        item_arr = new RowItem[10];
        errorlayout = (LinearLayout) view.findViewById(R.id.errorlayout);
        Buttomlayout = (RelativeLayout) view.findViewById(R.id.bottom);
        dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
        dialogBuilder1 = NiftyDialogBuilder.getInstance(getActivity());
        marks = getActivity().getSharedPreferences(filename, 0);
  /*    n = new int[]{1, 2, 3, 4, 5, 6, 1};
        questions(n);*/
        lv = (ListView) view.findViewById(R.id.Qlist);

        btBr= (ImageButton) view.findViewById(R.id.btBr);
        btBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        BtSub = (Button) view.findViewById(R.id.bt_sub);
        BtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=0;
                rand(10);
                for (int i = 0; i < 10; i++) {
                    RowItem rowItem = item_arr[i];
                    System.out.println(Store.ans[i] + " And ur ans " + Store.select[i]);
                    if (i < 10) {
                        if (Store.select[i] == (Integer.parseInt(rowItem.getCorrect()) + 1))
                            x = x + 1.0f;
                        else if (Store.select[i] == 0)
                            x = x + 0.0f;
                        else x = x - 0.5f;
                    }
                    Store.select[i]=0;
                }
                System.out.println("" + x);

                new Submitasync().execute("http://"+ip+"submit.php?");

            }
        });

        init();

        return view;
    }


    public void rand(int n) {
        r = new int[10];
        int f = 0;
        int i = 0;
        while (i < 10) {
            int y;

            y = (int) (Math.random() * n);
            for (int j = 0; j < i; j++) {
                if (r[j] == y)
                    f = 1;
            }
            if (f != 1) {
                r[i] = y;
                i++;

            } else {
                f = 0;
            }

        }
    }



    private class AsyncListViewLoader extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private int resCode;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Log.e("res_CODE", "" + resCode);
            if (resCode != 200) {
                errorlayout.setVisibility(View.VISIBLE);
                Buttomlayout.setVisibility(View.INVISIBLE);

            } else {
                errorlayout.setVisibility(View.GONE);
                Buttomlayout.setVisibility(View.VISIBLE);
                final ListAdapter Custom = new CustomAdapter(getActivity(), rowItems);
                lv.setAdapter(Custom);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        Customview customview = new Customview(getActivity(), position);
                        RowItem rowItem = item_arr[position];
                        dialogBuilder
                                .withTitle("Answer")                                  //.withTitle(null)  no title
                                .withTitleColor("#FFFFFF")                                  //def
                                .withDividerColor("#11000000")                              //def
                                .withMessage(rowItem.getTitle())                     //.withMessage(null)  no Msg
                                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                                .withDialogColor("#FFD147")
                                .withDuration(500)                                          //def
                                .withEffect(Effectstype.Fadein)                                         //def Effectstype.Slidetop
                                .withButton1Text("OK")//def gone
                                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                                .setCustomView(customview.getView(), getActivity())    // .setCustomView(View or ResId,context)
                                .setButton1Click(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogBuilder.hide();
                                        lv.setAdapter(Custom);
                                        if (position > 2)
                                            lv.setSelection(position - 2);
                                    }
                                })
                                .show();
                    }
                });

               wa = new waitasync();
                wa.execute();

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lv.setVisibility(View.VISIBLE);
            Buttomlayout.setVisibility(View.VISIBLE);
            dialog = new ProgressDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            dialog.setMessage("Downloading Questions..");
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            rowItems.clear();
            JSONArray arr = null;
            JSONObject jobj;
            byte[] b = null;

            Boolean x = JSONResp == null;

            try {
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpGet = new HttpPost(params[0]);

                HttpResponse httpResponse = httpClient.execute(httpGet);


                JSONResp = EntityUtils.toString(httpResponse.getEntity());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                arr = new JSONArray(JSONResp);
                rand(arr.length());
                resCode = 201;
                for (int i = 0; i < 10; i++) {
                    int Qn = r[i];
                    String[] Q = new String[0];
                    jobj = arr.getJSONObject(Qn);
                    Q = new String[]{jobj.getString("Q"), jobj.getString("A"), jobj.getString("B"), jobj.getString("C"), jobj.getString("D"), jobj.getString("correct")};
                    item = new RowItem(Q);
                    item_arr[i] = item;
                    rowItems.add(item);
                   /* name=name+jobj.getString("name")+"\n";*/
                    resCode = 200;
                }


            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }
    }
    private class LoginAsyncTask extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private int resCode;
        String S;

        int flag;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss(); 
            Log.e("res_CODE", "" + resCode);
            if (resCode != 200) {
                errorlayout.setVisibility(View.VISIBLE);
                Buttomlayout.setVisibility(View.INVISIBLE);

            } else {

                dialogBuilder1
                        .withTitle("Brainstormer 2015")                                  //.withTitle(null)  no title
                        .withTitleColor("#FFFFFF")                                  //def
                        .withDividerColor("#11000000")                              //def
                        .withMessage(S)                     //.withMessage(null)  no Msg
                        .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                        .withDialogColor("#FFC107")
                        .withDuration(500)                                          //def
                        .withEffect(Effectstype.Fadein)                                         //def Effectstype.Slidetop
                        .withButton1Text("OK")//def gone
                        .withButton2Text("cancel")
                        .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                           // .setCustomView(View or ResId,context)
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder1.hide();
                                init();
                            }
                        })
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (flag == 0) {
                                    dialogBuilder1.hide();
                                    new AsyncListViewLoader().execute("http://"+ip+"questions.json");
                                } else
                                {
                                    dialogBuilder1.hide();
                                    init();}
                            }
                        })

                        .show();




            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            dialog.setMessage("Connecting..");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();


        }

        @Override
        protected Void doInBackground(String... params) {

            Uri builtUri = Uri.parse(params[0]).buildUpon()
                    .appendQueryParameter("email", email)
                    .build();
            String url = new String(builtUri.toString());
            JSONArray arr = null;
            JSONObject jobj;
            byte[] b = null;



            try {

                HttpClient client = new DefaultHttpClient();
                URI web = null;
                try {
                    web = new URI(url);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                HttpGet request = new HttpGet();
                request.setURI(web);
                System.out.println(web);
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
                JSONResp = EntityUtils.toString(response.getEntity());
                // defaultHttpClient

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {


                resCode = 201;
                arr = new JSONArray(JSONResp);

                    jobj = arr.getJSONObject(0);
                    S= jobj.getString("msg");
                    flag= Integer.parseInt(jobj.getString("flag"));

                   /* name=name+jobj.getString("name")+"\n";*/
                    resCode = 200;



            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }
    }

    public void init(){

        btBr.setVisibility(View.VISIBLE);
        final Customview2 customview2 = new Customview2(getActivity());
        dialogBuilder
                .withTitle("Brainstormer 2015")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("Enter Your Email Id to proceed")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFCC33")
                .withDuration(500)                                          //def
                .withEffect(Effectstype.Fadein)                                         //def Effectstype.Slidetop
                .withButton1Text("Submit")//def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .setCustomView(customview2.getView(), getActivity())    // .setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        email=customview2.gtext();
                        System.out.println(email);
                        if (email!="") {
                            dialogBuilder.hide();
                            btBr.setVisibility(View.GONE);
                            new LoginAsyncTask().execute("http://"+ip+"test.php?");
                        }


                    }
                })
                .show();

    }
    public class Submitasync extends AsyncTask<String, Void, Void> {
        String S;

        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            wa.cancel(true);
            dialog = new ProgressDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            dialog.setMessage("Submitting your Answer..");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            lv.setVisibility(View.INVISIBLE);
            Buttomlayout.setVisibility(View.INVISIBLE);
            dialog.show();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            final AlertDialog.Builder msgdialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            msgdialog.setTitle("Brainstormer 2015");
            S=S.concat(""+x);
            msgdialog.setMessage(S);
            msgdialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    init();
                }
            });
            msgdialog.show();

            init();


        }

        @Override
        protected Void doInBackground(String... params) {
            URI web = null;
            Uri builtUri = Uri.parse(params[0]).buildUpon()
                    .appendQueryParameter("email", email)
                    .appendQueryParameter("marks", String.valueOf(x))
                    .build();
            String url = new String(builtUri.toString());
            BufferedReader in = null;
            JSONArray arr = null;
            JSONObject jobj;
            byte[] b = null;

            Boolean x = JSONResp == null;
//            Toast.makeText(getApplicationContext(),"In Background",Toast.LENGTH_SHORT).show();
            System.out.println("In Background");
            try {
                web = new URI(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            try {
                S="No Internet Connection";
                HttpClient client = new DefaultHttpClient();

                HttpGet request = new HttpGet();
                request.setURI(web);
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();


                if(status==200){

                    System.out.println(web);
                    S="Your Marks Is successfully Submitted.\n You scored ";

                }
                else if (status==400)
                    S="Error Connecting... Please Try After Some Time";
                else
                    S="Server Not found. Please Try Later";

                System.out.println(status);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

    }

    public class waitasync extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {

            x=0;
            for (int i = 0; i < 10; i++) {
                RowItem rowItem = item_arr[i];
                System.out.println(Store.ans[i] + " And ur ans " + Store.select[i]);
                if (i < 10) {
                    if (Store.select[i] == (Integer.parseInt(rowItem.getCorrect()) + 1))
                        x = x + 1.0f;
                    else if (Store.select[i] == 0)
                        x = x + 0.0f;
                    else x = x - 0.5f;
                }
                Store.select[i]=0;
            }
            System.out.println("" + x);
            new Submitasync().execute("http://" + ip + "submit.php?");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... params) {
            long timeeer = TimeUnit.MINUTES.toMillis(3);
            System.out.println(timeeer);
            if (isCancelled()==true)
                return null;
            try {
                Thread.sleep(timeeer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}