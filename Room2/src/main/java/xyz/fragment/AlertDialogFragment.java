package xyz.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import xyz.room2.R;

public class AlertDialogFragment extends DialogFragment {

    AlertDialogListener mListener;
    public static String ID_LONG = "ID_LONG";
    long id_data;

    public interface AlertDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, long idItem);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Activity activity = getActivity();
        Bundle bundle = getArguments();

        if (bundle != null && activity != null) {
            //get data from bundle
            id_data = bundle.getLong(ID_LONG);
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.message_dialog)
                    .setPositiveButton(R.string.message_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // set listener for yes button
                            mListener.onDialogPositiveClick(AlertDialogFragment.this, id_data);
                        }
                    })
                    .setNegativeButton(R.string.message_no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            mListener.onDialogNegativeClick(AlertDialogFragment.this);
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
        //if no bundle - show error
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setNegativeButton(R.string.message_error, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the DialogListener

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DialogListener so we can send events to the host
            mListener = (AlertDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement AlertDialogListener");
        }
    }


}