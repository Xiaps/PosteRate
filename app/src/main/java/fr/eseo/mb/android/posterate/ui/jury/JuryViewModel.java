package fr.eseo.mb.android.posterate.ui.jury;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JuryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JuryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}