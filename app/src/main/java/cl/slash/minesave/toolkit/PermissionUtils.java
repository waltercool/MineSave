package cl.slash.minesave.toolkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waltercool on 9/13/16.
 */
public class PermissionUtils {

    public static void requestReadPermissions(Activity activity, String... permissions) {
        List<String> missingPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if(ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    missingPermissions.add(permission);
                } else {
                    //TODO Blocked permission
                    AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                    dialog.setMessage("ASD");
                    dialog.create().show();
                    return;
                }
            }
        }

        ActivityCompat.requestPermissions(activity,
                missingPermissions.toArray(new String[missingPermissions.size()]),
                500);
    }

}
