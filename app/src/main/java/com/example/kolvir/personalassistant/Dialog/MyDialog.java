package com.example.kolvir.personalassistant.Dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.kolvir.personalassistant.R;

public class MyDialog extends DialogFragment {
    public interface MyCallback{
        void returnCallback(String name, String description);
    }

    private static final String TAG = "MyDialog";
    MyCallback myCallback;
    EditText editTextTitle;
    EditText editTextDescription;

    public void registerCallback(MyCallback callback){
        this.myCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.day_cfragment,container,false);

        editTextTitle = v.findViewById(R.id.dayTitle);
        editTextDescription = v.findViewById(R.id.dayDescription);

        v.findViewById(R.id.action_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        v.findViewById(R.id.action_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCallback.returnCallback(editTextTitle.getText().toString(),editTextDescription.getText().toString());
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.e(TAG, ": dismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.e(TAG,": cancel");
    }
}
