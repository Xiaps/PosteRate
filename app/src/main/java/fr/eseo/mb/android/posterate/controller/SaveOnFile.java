package fr.eseo.mb.android.posterate.controller;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveOnFile {
    public static int lenghLastText = -1;

    public static boolean writeOnFile(String fileName, String text, Context context) {
        boolean isTheSame = false;
        try {
            // catches IOException below
            final String textString = new String(text);
            /* We have to use the openFileOutput()-method
             * the ActivityContext provides, to
             * protect your file from others and
             * This is done for security-reasons.
             * We chose MODE_WORLD_READABLE, because
             *  we have nothing to hide in our file */
            FileOutputStream fOut = context.getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            // Write the string to the file
            osw.write(textString);
            /* ensure that everything is
             * really written out and close */
            osw.flush();
            osw.close();
//Reading the file back...
            /* We have to use the openFileInput()-method
             * the ActivityContext provides.
             * Again for security reasons with
             * openFileInput(...) */
            FileInputStream fIn = context.getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fIn);
            /* Prepare a char-Array that will
             * hold the chars we read back in. */
            char[] inputBuffer = new char[textString.length()];
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            String readString = new String(inputBuffer);
            // Check if we read back the same chars that we had written out
            isTheSame = textString.equals(readString);
            if (isTheSame) {
                lenghLastText = textString.length();
            }
            Log.i("File Reading stuff", "success = " + isTheSame);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isTheSame;
    }

    public static String readOnFileNotes(String fileName, Context context) throws FileNotFoundException {
        String readString = null;
        try {
            //Reading the file back...
            /* We have to use the openFileInput()-method
             * the ActivityContext provides.
             * Again for security reasons with
             * openFileInput(...) */
            FileInputStream fIn = context.getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fIn);
            /* Prepare a char-Array that will
             * hold the chars we read back in. */
            char[] inputBuffer;
            if (lenghLastText == -1) {
                inputBuffer = new char[5000];
            } else {
                inputBuffer = new char[lenghLastText];
            }
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            readString = new String(inputBuffer);
            if (lenghLastText == -1) {
                readString = readString.substring(0, readString.lastIndexOf("/5.") + 3);
                lenghLastText = readString.length();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readString;
    }

    public static String readOnFileProjetPseudojuryID(String fileName, Context context) throws FileNotFoundException {
        String readString = null;
        try {
            //Reading the file back...
            /* We have to use the openFileInput()-method
             * the ActivityContext provides.
             * Again for security reasons with
             * openFileInput(...) */
            FileInputStream fIn = context.getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fIn);
            /* Prepare a char-Array that will
             * hold the chars we read back in. */
            char[] inputBuffer;
            if (lenghLastText == -1) {
                inputBuffer = new char[5000];
            } else {
                inputBuffer = new char[lenghLastText];
            }
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            readString = new String(inputBuffer);
            if (lenghLastText == -1) {
                readString = readString.substring(0, readString.lastIndexOf("_") + 2);
                lenghLastText = readString.length();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readString;
    }

    public static void addNote(int note, String utilisateur, String titreProjet, Context context) throws FileNotFoundException {
        String textActuel = readOnFileProjetPseudojuryID("PseudoJuryNotes", context);
        String textUpdated = "";
        if (note > 5) {
            note = 5;
        } else if (note < 1) {
            note = 1;
        }
        if (textActuel == null) {
            textActuel = "";
        }
        if (textActuel.contains("Projet " + titreProjet + " " + utilisateur + " a donné la note de")) {
            for (int i = 1; i < 6; i++) {
                textActuel = textActuel.replaceAll("Projet " + titreProjet + " " + utilisateur + " a donné la note de " + i + "/5.",
                        "Projet " + titreProjet + " " + utilisateur + " a donné la note de " + note + "/5.");
            }
            textUpdated = textActuel;
        } else {
            textUpdated = textActuel + "\n " + "Projet " + titreProjet + " " + utilisateur + " a donné la note de " + note + "/5.";
        }
        writeOnFile("PseudoJuryNotes", textUpdated, context);
    }

    public static String getNotes(Context context) throws FileNotFoundException {
        return readOnFileNotes("PseudoJuryNotes", context);
    }

    public static void formatNotes(Context context) {
        writeOnFile("PseudoJuryNotes", "", context);
    }

    public static void formatProjetPseudoJuryID(Context context) {
        writeOnFile("PseudoJuryProjetsID", "", context);
    }

    public static String getProjetPseudoJuryID(Context context) throws FileNotFoundException {
        return readOnFileProjetPseudojuryID("PseudoJuryProjetsID", context);
    }

    public static void addProjetPseudoJuryID(String projectID, Context context) throws FileNotFoundException {
        String textActuel = readOnFileProjetPseudojuryID("PseudoJuryProjetsID", context);
        String textUpdated = "";
        if (textActuel == null) {
            textActuel = "";
        }
        //textActuel = textActuel.substring(textActuel.indexOf("_"));
        String[] listStringID = textActuel.split("_");
        if (listStringID.length >= 5) {
            for (int i = 1; i < 5; i++) {
                textUpdated += listStringID[i] + "_";
            }
            textUpdated += projectID;
        } else {
            if (textActuel.length() == 0) {
                textUpdated += projectID;
            } else {
                for (int i = 0; i < listStringID.length; i++) {
                    textUpdated += listStringID[i] + "_";
                }
                textUpdated += projectID;
            }
        }
        writeOnFile("PseudoJuryProjetsID", textUpdated, context);
    }
}