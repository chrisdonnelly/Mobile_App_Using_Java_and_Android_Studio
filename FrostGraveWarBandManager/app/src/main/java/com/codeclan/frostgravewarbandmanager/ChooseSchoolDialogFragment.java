package com.codeclan.frostgravewarbandmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ChooseSchoolDialogFragment extends DialogFragment {

    WizardSchools wizardSchools;
    ArrayList<School> schools;
    NoticeDialogListener mListener;
    int mStoreChoice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceSate) {

        wizardSchools = new WizardSchools();
        schools = wizardSchools.getList();
        String[] primitiveSchools = new String[10];
        int count = 0;
        for (School school : schools){
            primitiveSchools[count] = school.getSchoolName();
            count++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle(R.string.dialog_choose_school)

                .setSingleChoiceItems(primitiveSchools, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mStoreChoice = i;
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mListener.onDialogPositiveClick(ChooseSchoolDialogFragment.this, mStoreChoice);
                    }
                });

    return builder.create();
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, int i);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}