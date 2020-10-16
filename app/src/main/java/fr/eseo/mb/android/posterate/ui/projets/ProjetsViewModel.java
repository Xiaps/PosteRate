package fr.eseo.mb.android.posterate.ui.projets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjetsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProjetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is project fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}