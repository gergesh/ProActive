package men.hasha.proactive.hooks;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;

public class pl_solidexplorer {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        // Pro mode
        findAndHookMethod("pl.solidexplorer.SELicenseManager", lpparam.classLoader,
                "checkLicenseOnBackend", XC_MethodReplacement.returnConstant(true));

        // Unlock themes
        // TODO not working
        Class<?> c = findClass("pl.solidexplorer.common.res.ColorScheme", lpparam.classLoader);
        Method m = findMethodBestMatch(c, "getState");
        XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(0));
    }
}
