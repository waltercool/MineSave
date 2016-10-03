package cl.slash.minesave.toolkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.cloudrail.si.CloudRail;
import com.cloudrail.si.exceptions.HttpException;
import com.cloudrail.si.exceptions.ParseException;
import com.cloudrail.si.interfaces.CloudStorage;
import com.cloudrail.si.services.Box;
import com.cloudrail.si.services.Dropbox;
import com.cloudrail.si.services.GoogleDrive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import cl.slash.minesave.R;
import cl.slash.minesave.obj.constants.WorldStorageLocation;
import cl.slash.minesave.obj.domain.MineWorld;

/**
 * Created by waltercool on 9/13/16.
 */
public class StorageUtils {

    private String compressWorld(Context context, MineWorld world) throws Exception {
        //TODO Compress into cache, so I can fetch it later.
        File f = new File(context.getExternalCacheDir(), world.getFolderName()+".zip");
        Util.zipDir(world.getPath(), f.getPath());
        return f.getPath();
    }

    private MineWorld uncompressWorld(Context context, URI pathCompressedFile) throws IOError {
        return null;
    }

    public boolean saveInCloud(Context context, MineWorld world,
                               WorldStorageLocation cloudProvider) {
        //TODO Move it to ASyncTask, so I can update status changes
        String compressedFilePath;
        try {
            compressedFilePath = compressWorld(context, world);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        Resources res = context.getResources();
        CloudRail.setAppKey(res.getString(R.string.key_cloudrail));

        CloudStorage provider;
        switch (cloudProvider) {
            case BOX:
                provider = new Box(context, res.getString(R.string.key_box_key),
                        res.getString(R.string.key_box_secret));
                break;
            case DRIVE:
                provider = new GoogleDrive(context, res.getString(R.string.key_gdrive),
                        res.getString(R.string.key_gdrive_secret));
                break;
            case DROPBOX:
                provider = new Dropbox(context, res.getString(R.string.key_dropbox),
                        res.getString(R.string.key_dropbox_secret));
                break;
            default:
                provider = null;
                break;
        }

        try {
            provider.loadAsString(loadCredentials(context));
        } catch (ParseException e) {
            //No credentials saved. This is normal.
        }
        try {
            String remoteFolder = res.getString(R.string.app_remote_folder);
            if (!provider.exists(remoteFolder)) {
                provider.createFolder(remoteFolder);
            }

            File file = new File(compressedFilePath);

            InputStream is = new FileInputStream(file);
            provider.upload(remoteFolder +"/"+file.getName(), is, is.available(), true);
            is.close();
            saveCredentials(context, provider.saveAsString());
            return true;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            // Should never happen, unless compression fails
        } catch(IOException e) {
            e.printStackTrace();
        } catch(HttpException e) {
            e.printStackTrace();
            // Permission error?
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            // Bad API usage?
        } catch(RuntimeException e) {
            e.printStackTrace();
            // Session invalid?
            saveCredentials(context, null);
        }
        return false;
    }

    public boolean loadFromCloud(Context context, MineWorld world) {
        //TODO load
        return false;
    }

    private void saveCredentials(Context context, String cred) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("KEY", cred).apply();
    }

    private String loadCredentials(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("KEY", "");
    }

}
