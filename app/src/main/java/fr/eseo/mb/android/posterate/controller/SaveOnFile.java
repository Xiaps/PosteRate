package fr.eseo.mb.android.posterate.controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import fr.eseo.mb.android.posterate.data.model.Project;
import fr.eseo.mb.android.posterate.data.model.User;

public class SaveOnFile {
    public static int lenghLastTextNotes = -1;
    public static int lenghLastTextProjects = -1;
    public static int lenghLastTextProjectsID = -1;

    public static boolean writeOnFileNotes(String fileName, String text, Context context) {
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
                lenghLastTextNotes = textString.length();
            }
            Log.i("File Reading stuff", "success = " + isTheSame);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isTheSame;
    }

    public static boolean writeOnFileProjectID(String fileName, String text, Context context) {
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
                lenghLastTextProjectsID = textString.length();
            }
            Log.i("File Reading stuff", "success = " + isTheSame);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isTheSame;
    }

    public static boolean writeOnFileProjects(String fileName, String text, Context context) {
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
                lenghLastTextProjects = textString.length();
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
            if (lenghLastTextNotes == -1) {
                inputBuffer = new char[5000];
            } else {
                inputBuffer = new char[lenghLastTextNotes];
            }
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            readString = new String(inputBuffer);
            if (lenghLastTextNotes == -1) {
                readString = readString.substring(0, readString.lastIndexOf("/5.") + 3);
                lenghLastTextNotes = readString.length();
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
            if (lenghLastTextProjectsID == -1) {
                inputBuffer = new char[5000];
            } else {
                inputBuffer = new char[lenghLastTextProjectsID];
            }
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            readString = new String(inputBuffer);
            if (lenghLastTextProjectsID == -1) {
                readString = readString.substring(0, readString.lastIndexOf("_") + 2);
                lenghLastTextProjectsID = readString.length();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readString;
    }

    public static String readOnFileProjets(String fileName, Context context) throws FileNotFoundException {
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
            if (lenghLastTextProjects == -1) {
                inputBuffer = new char[5000000];
            } else {
                inputBuffer = new char[lenghLastTextProjects];
            }
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            readString = new String(inputBuffer);
            if (lenghLastTextProjects == -1) {
                readString = readString.substring(0, readString.lastIndexOf("}") + 1);
                lenghLastTextProjects = readString.length();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return readString;
    }

    public static void addNote(int note, String utilisateur, String titreProjet, Context context) throws FileNotFoundException {
        String textActuel = readOnFileNotes("PseudoJuryNotes", context);
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
        writeOnFileNotes("PseudoJuryNotes", textUpdated, context);
    }

    public static String getNotes(Context context) throws FileNotFoundException {
        return readOnFileNotes("PseudoJuryNotes", context);
    }

    public static void formatNotes(Context context) {
        writeOnFileNotes("PseudoJuryNotes", "", context);
    }

    public static void formatProjetPseudoJuryID(Context context) {
        writeOnFileNotes("PseudoJuryProjetsID", "", context);
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
        writeOnFileProjectID("PseudoJuryProjetsID", textUpdated, context);
    }

    public static ArrayList<Project> getProjets(Context context) throws FileNotFoundException {
        String result = readOnFileProjets("ProjetsFull", context);
        ArrayList<Project> projectList = new ArrayList<Project>();

        try {
            JSONArray allProjects = new JSONObject(result).getJSONArray("projects");
            for (int i = 0; i < allProjects.length(); i++) {
                String projectID = allProjects.getJSONObject(i).getString("projectId");
                String title = allProjects.getJSONObject(i).getString("title");
                String descrip = allProjects.getJSONObject(i).getString("descrip");
                JSONObject supervisorO = allProjects.getJSONObject(i).getJSONObject("supervisor");
                User supervisor = new User(supervisorO.getString("forename"), supervisorO.getString("surname"));
                Boolean poster = allProjects.getJSONObject(i).getBoolean("poster");
                int confid = allProjects.getJSONObject(i).getInt("confid");
                JSONArray studentsAO = allProjects.getJSONObject(i).getJSONArray("students");
                ArrayList<User> students = new ArrayList<User>();
                for (int y = 0; y < studentsAO.length(); y++) {
                    students.add(new User(studentsAO.getJSONObject(y).getString("forename"), studentsAO.getJSONObject(y).getString("surname"), studentsAO.getJSONObject(y).getString("userId")));
                }
                projectList.add(new Project(projectID, title, descrip, supervisor, poster, confid, students));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return projectList;
    }


    public static void setProjets(String projets, Context context) {
        writeOnFileProjects("ProjetsFull", projets, context);
    }


    public static ArrayList<Project> getProjetsSelection(Context context) throws FileNotFoundException {
        String stringIDs = getProjetPseudoJuryID(context);
        String[] stringIDList = stringIDs.split("_");
        ArrayList<Project> allProjets = getProjets(context);
        ArrayList<Project> selectedProjets = new ArrayList<Project>();

        for (int i = 0; i < allProjets.size(); i++) {
            for (String ID : stringIDList) {
                if (ID.equals(allProjets.get(i).getProjectId())) {
                    selectedProjets.add(allProjets.get(i));
                }
            }
        }

        return selectedProjets;
    }


}
