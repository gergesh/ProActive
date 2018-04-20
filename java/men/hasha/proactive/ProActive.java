package men.hasha.proactive;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
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
            findAndHookMethod("pl.solidexplorer.backend.seApi.model.RedeemStatus", lpparam.classLoader,
                    "getSuccessful", new XC_MethodHook() {
                @Override
                protected final void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Boolean b = true;
                    param.setResult(b);
                }
            });

            findAndHookMethod("pl.solidexplorer.backend.seApi.model.RedeemStatus", lpparam.classLoader,
                    "getComponentName", XC_MethodReplacement.returnConstant("full_version"));
        }

        // BG Stats
        else if (lpparam.packageName.equals("nl.eerko.boardgamestats")) {
            findAndHookMethod("nl.eerko.boardgamestats.main.AppUtils", lpparam.classLoader,
                    "getSharedPreferenceBool", new XC_MethodHook() {
                @Override
                protected final void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String str = (String) param.args[1];
                    if (str.indexOf("iap") == 0 && str.indexOf("Active") == str.length() - 6)
                        param.setResult(true);
                }
            });
        }
    }
}
