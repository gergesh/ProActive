package men.hasha.proactive.hooks;

import android.content.Context;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;

public class nl_eerko_boardgamestats {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedBridge.log("I, BGStatsHook, was invoked!");
        Class<?> c = findClass("nl.eerko.boardgamestats.main.AppUtils", lpparam.classLoader);
        Method m = findMethodBestMatch(c, "getSharedPreferenceBool", Context.class, String.class, boolean.class);
        XposedBridge.hookMethod(m, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String pref = (String) param.args[1];
                if (pref.indexOf("iap") == 0 && pref.indexOf("Active") == pref.length() - 6) {
                    param.setResult(true);
                    XposedBridge.log("Called with argument " + pref + ", overriden to true");
                }
            }
        });
    }
}
