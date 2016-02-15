package slidenerd.vivz.d2demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import slidenerd.vivz.d2demo.extras.Keys;

/**
 * A placeholder fragment containing a simple view.
 *
 * Step 3: We add the @Inject annotations inside Fragment A and Fragment B where we want Dagger to
 * supply us with a SharedPreferences object. Build the program at this point so that Dagger can
 * generate an implementation for your component interface. We use this implementation in the next
 * step.
 *
 */
public class FragmentB extends Fragment implements View.OnClickListener {

    // Step 3: Inject our SharedPreferences object
    @Inject
    SharedPreferences mPreferences;

    //private SharedPreferences mPreferences;
    private TextView mTextStored;
    private Button mBtnRefresh;

    public FragmentB() {
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
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mTextStored = (TextView) view.findViewById(R.id.text_stored);
        mBtnRefresh = (Button) view.findViewById(R.id.btn_refresh);
        mBtnRefresh.setOnClickListener(this);
        loadStoredDataIntoTextView();
    }

    @Override
    public void onClick(View v) {
        loadStoredDataIntoTextView();
    }

    private void loadStoredDataIntoTextView() {
        String storedText = mPreferences.getString(Keys.PREF_INPUT, "Nothing found yet");
        mTextStored.setText(storedText);
    }
}
