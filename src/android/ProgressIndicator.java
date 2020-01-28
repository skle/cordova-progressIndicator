package org.apache.cordova.progressindicator;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

public class ProgressIndicator extends CordovaPlugin {

    private Dialog progressIndicator = null;
	private static final String LOG_TAG = "ProgressIndicator";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("show") || action.equals("showSimple")) {
            show();
            callbackContext.success();
            return true;
        } else if (action.equals("hide")) {
            hide();
            callbackContext.success();
            return true;
        } else {
            callbackContext.error("Not supported call. On Android we only support show, showSimple");
		}

        return false;
    }

    /**
     * This show the ProgressDialog
     *
     */
    public void show() {
        progressIndicator = new Dialog(cordova.getActivity());
        ProgressBar _ProgressBar = new ProgressBar(cordova.getActivity());
        _ProgressBar.setIndeterminate(true);
        _ProgressBar.setIndeterminateTintMode(android.graphics.PorterDuff.Mode.SRC_IN);
        _ProgressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.BLACK));
        _ProgressBar.setVisibility(View.VISIBLE);
        progressIndicator.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressIndicator.getWindow().setDimAmount(0.0f);
        progressIndicator.setContentView(_ProgressBar);
        progressIndicator.show();
    }

    /**
     * This hide the ProgressDialog
     */
    public void hide() {
        if (progressIndicator != null) {
            progressIndicator.dismiss();
            progressIndicator = null;
        }
    }
}
