package mx.org.bamx.jsh.project.NetWorking;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by PC on 05/07/2018.
 */

public class ProgressDialogMessage {
    String message = "";
    ProgressDialog progressDialog;
    Context context;

    public ProgressDialogMessage(Context context) {
        this.context = context;
    }

    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }


}
