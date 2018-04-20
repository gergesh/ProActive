package men.hasha.proactive;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ProActive implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        // Shuttle
        if (lpparam.packageName.equals("another.music.player")) {
            findAndHookMethod("com.simplecity.amp_library.ShuttleApplication",
                    lpparam.classLoader, "d", XC_MethodReplacement.returnConstant(true));
        }

	// Phonograph
	else if (lpparam.packageName.equals("com.kabouzeid.gramophone")) {
	    findAndHookMethod("com.kabouzeid.gramophone.App", lpparam.classLoader,
	            "isProVersion", XC_MethodReplacement.returnConstant(true));
	}

	// Solid Explorer
	else if (lpparam.packageName.equals("pl.solidexplorer2")) {
	    findAndHookMethod("pl.solidexplorer.SELicenseManager", lpparam.classLoader,
	            "checkUnlockerLicense", XC_MethodReplacement.returnConstant(true));
	}
    }
}
