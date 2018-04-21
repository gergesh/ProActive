package men.hasha.proactive;

import android.content.Context;

import java.lang.reflect.Method;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hooks {
    static Class<?> c;
    static Method m;

    public static void hookShuttle(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.simplecity.amp_library.ShuttleApplication",
                lpparam.classLoader, "d", XC_MethodReplacement.returnConstant(true));
    }

    public static void hookPhonograph(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.kabouzeid.gramophone.App",
                lpparam.classLoader, "isProVersion", XC_MethodReplacement.returnConstant(true));
    }

    public static void hookSolidExplorer(XC_LoadPackage.LoadPackageParam lpparam) {
        // Pro mode
        findAndHookMethod("pl.solidexplorer.SELicenseManager", lpparam.classLoader,
                "checkLicenseOnBackend", XC_MethodReplacement.returnConstant(true));

        // Unlock themes
        c = findClass("pl.solidexplorer.common.res.ColorScheme", lpparam.classLoader);
        m = findMethodBestMatch(c, "getState");
        XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(0));
    }

    public static void hookBGstats(XC_LoadPackage.LoadPackageParam lpparam) {
        c = findClass("nl.eerko.boardgamestats.main.AppUtils", lpparam.classLoader);
        m = findMethodBestMatch(c, "getSharedPreferenceBool", Context.class, String.class, boolean.class);
        XposedBridge.hookMethod(m, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("getSharedPreferenceBool hooked.");
                String pref = (String) param.args[1];
                if (pref.indexOf("iap") == 0 && pref.indexOf("Active") == pref.length() - 6) {
                    param.setResult(true);
                    XposedBridge.log("Called with argument " + pref + ", overriden to true");
                }
            }
        });
    }

    public static void hookTitaniumBackup(XC_LoadPackage.LoadPackageParam lpparam) {
        c = findClass("o.ﭡ", lpparam.classLoader);
        m = findMethodExact(c, "ˊ", "ﭡ", "ױ");
        XposedBridge.hookMethod(m, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = 2;
            }
        });
    }
}
