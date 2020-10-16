package fr.eseo.mb.android.posterate.controller;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import fr.eseo.mb.android.posterate.R;

public class ComWebService {

    private static SSLContext context = null;

    public static SSLContext getContext(Context ressourceContext){
        if(context==null) {

            try {

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            Log.d("certificate", Integer.toString(R.raw.dis_inter_ca));

            InputStream caInput = ressourceContext.getResources().openRawResource(R.raw.dis_inter_ca);


            Log.d("certificateIn", caInput.toString());

            Certificate certif;
            try {
                certif = cf.generateCertificate(caInput);
                System.out.println("certif=" + ((X509Certificate)
                        certif).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certif);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm =
                    TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf =
                    TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);


            } catch (Exception e) {
                System.out.println("Error logging in : "+e.toString());
            }
        }
        return context;
    }


    public static String urlRequest(Context contextRessource,URL url) {
        String resultat = "";

        try {



        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setSSLSocketFactory(getContext(contextRessource).getSocketFactory());
        InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(urlConnection.getInputStream());
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String scannerText = scanner.next();
                return scannerText;
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            System.out.println("Error logging in : "+e.toString());
        }

        return resultat;
    }



}
