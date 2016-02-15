package slidenerd.vivz.d2demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import slidenerd.vivz.d2demo.extras.Keys;

/**
 * A placeholder fragment containing a simple view.
 *
 * Step 3: We add the @Inject annotations inside Fragment A and Fragment B where we want Dagger to
 * supply us with a SharedPreferences object. Build the program at this point so that Dagger can
 * generate an implementation for your component interface. We use this implementation in the next
 * step.
 */
public class FragmentA extends Fragment implements View.OnClickListener {

    // Step 3: Inject our SharedPreferences object
    @Inject
    SharedPreferences mPreferences;

    //private SharedPreferences mPreferences;
    private EditText mInputText;
    private Button mBtnStore;

    public FragmentA() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        /*
         Step 5 Notice how we have passed a reference of Fragment A by getting our Activity, get our
         Application from our Activity, typecast it to our custom Application, get the component
         from that and simply call inject() method which was actually a part of our Component
         interface declared in Step 2. This is how the whole process works

         give our injected object a reference
         */
        ((MyApplication) getActivity().getApplication()).getStorageComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mInputText = (EditText) view.findViewById(R.id.input_text);
        mBtnStore = (Button) view.findViewById(R.id.btn_store);
        mBtnStore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        storeTextToPreferences();
    }

    private void storeTextToPreferences() {
        String text = mInputText.getText().toString().trim();
        if (text != null && !text.isEmpty()) {
            mPreferences.edit().putString(Keys.PREF_INPUT, text).apply();
        }
    }
}
