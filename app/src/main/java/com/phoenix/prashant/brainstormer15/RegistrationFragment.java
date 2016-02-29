package com.phoenix.prashant.brainstormer15;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Entity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by PRASHANT on 05-07-2015.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener {
    TextView mName, mEmail, mphone, mTeam, mInstitute;
    RadioButton radioSchool, radioCollege;
    String name, email, phone, team, institute, type;
    Button btReg;
    String ip;
    String JSONResp;
     String Base_Url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, container, false);
        mName = (TextView) view.findViewById(R.id.mname);
        mEmail = (TextView) view.findViewById(R.id.mEmail);
        mphone = (TextView) view.findViewById(R.id.mPhone);
        mTeam = (TextView) view.findViewById(R.id.mTeam);
        mInstitute = (TextView) view.findViewById(R.id.mInstitute);
        radioSchool = (RadioButton) view.findViewById(R.id.radioSchool);
        radioCollege = (RadioButton) view.findViewById(R.id.radioCollege);
        btReg = (Button) view.findViewById(R.id.btreg);
        ip=getResources().getString(R.string.ip);
        Base_Url = "http://" + ip + "check.php?";
        btReg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        boolean valid;
        name = String.valueOf(mName.getText());
        email = String.valueOf(mEmail.getText());
        phone = String.valueOf(mphone.getText());
        team = String.valueOf(mTeam.getText());
        institute = String.valueOf(mInstitute.getText());
        if (radioSchool.isChecked())
            type = String.valueOf(radioSchool.getText());
        else
            type = String.valueOf(radioCollege.getText());
        valid = validate();

        if (valid==true) {
            mName.setText(null);
            mEmail.setText(null);
            mphone.setText(null);
            mTeam.setText(null);
            mInstitute.setText(null);
            radioCollege.setChecked(false);
            radioSchool.setChecked(false);
            Uri builtUri = Uri.parse(Base_Url).buildUpon()
                    .appendQueryParameter("name", name)
                    .appendQueryParameter("email", email)
                    .appendQueryParameter("team", team)
                    .appendQueryParameter("phone", phone)
                    .appendQueryParameter("type", type)
                    .appendQueryParameter("institute", institute)
                    .build();

            String url = new String(builtUri.toString());

            new getdata().execute(url);
        }

    }

    public class getdata extends AsyncTask<String, Void, Void> {
        String S;
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            dialog.setMessage("Registering..");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            final AlertDialog.Builder msgdialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            msgdialog.setTitle("Registration For Brainstormer");
            msgdialog.setMessage(S);
            msgdialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            msgdialog.show();

        }

        @Override
        protected Void doInBackground(String... params) {
            BufferedReader in = null;
            JSONArray arr = null;
            JSONObject jobj;
            byte[] b = null;

            Boolean x = JSONResp == null;
//            Toast.makeText(getApplicationContext(),"In Background",Toast.LENGTH_SHORT).show();
            System.out.println("In Background");

            try {
                S="No Internet Connection";
                HttpClient client = new DefaultHttpClient();
                URI web = new URI(params[0]);
                HttpGet request = new HttpGet();
                request.setURI(web);
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
                JSONResp = EntityUtils.toString(response.getEntity());

                if(status==200){
                    try {
                        arr = new JSONArray(JSONResp);
                        jobj = arr.getJSONObject(0);
                        S = jobj.getString("res");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
            } catch (URISyntaxException e) {
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

    public boolean validate(){


            String x = name;
            if(x.length()<2)
            {
                Toast.makeText(getActivity(),"Invalid Name",Toast.LENGTH_SHORT).show();
                return false;
            }

            String y =email;
            int atpos=y.indexOf("@");
            int dotpos=y.lastIndexOf(".");
            if((atpos<1) || (dotpos<atpos+2)||(dotpos+2>=y.length()))
            {

                Toast.makeText(getActivity()," Not a valid e-mail address",Toast.LENGTH_SHORT).show();
                return false;
            }

            String t= team;
            if(t.length()<2){
                Toast.makeText(getActivity()," Select Team name",Toast.LENGTH_SHORT).show();

                return false;}

            String z = phone;
            if((z.length())!=10)
            {

                Toast.makeText(getActivity(),"Not a valid Phone number !",Toast.LENGTH_SHORT).show();
                return false;
            }

            String f=type;
            if(f.length()<3){
                Toast.makeText(getActivity()," Select Institute type!",Toast.LENGTH_SHORT).show();
                return false;}

            String e = institute;
            if (e.length()<2) {
                Toast.makeText(getActivity(),"Not a valid Institute Name",Toast.LENGTH_SHORT).show();
                return false;}

        else
                return true;

    }
}
