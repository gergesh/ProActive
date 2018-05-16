package men.hasha.proactive;

import android.content.Context;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

public class Hooks {
    static Class<?> c;
    static Method m;

    // Shuttle
    public static void another_music_player(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.simplecity.amp_library.ShuttleApplication",
                lpparam.classLoader, "d", XC_MethodReplacement.returnConstant(true));
    }

    // Phonograph
    public static void com_kabouzeid_gramophone(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.kabouzeid.gramophone.App",
                lpparam.classLoader, "isProVersion", XC_MethodReplacement.returnConstant(true));
    }

    // Solid Explorer
    public static void pl_solidexplorer(XC_LoadPackage.LoadPackageParam lpparam) {
        // Pro mode
        findAndHookMethod("pl.solidexplorer.SELicenseManager", lpparam.classLoader,
                "checkLicenseOnBackend", XC_MethodReplacement.returnConstant(true));

        // Unlock themes
        // TODO not working
        c = findClass("pl.solidexplorer.common.res.ColorScheme", lpparam.classLoader);
        m = findMethodBestMatch(c, "getState");
        XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(0));
    }

    // BG Stats
    public static void nl_eerko_boardgamestats(XC_LoadPackage.LoadPackageParam lpparam) {
        c = findClass("nl.eerko.boardgamestats.main.AppUtils", lpparam.classLoader);
        m = findMethodBestMatch(c, "getSharedPreferenceBool", Context.class, String.class, boolean.class);
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

    // SD Maid
    public static void eu_thedarken_sdm(XC_LoadPackage.LoadPackageParam lpparam) {
        // Pro mode
        c = findClass("eu.thedarken.sdm.tools.upgrades.e", lpparam.classLoader);
        Class<?> d = findClass("eu.thedarken.sdm.tools.upgrades.d", lpparam.classLoader);
        m = findMethodExact(c, "a", d);
        XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(true));
        // UI changes
        // TODO
    }

    // Sandbox
    public static void sandbox_art_sandbox(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("sandbox.art.sandbox.repositories.entities.Account",
                lpparam.classLoader, "isSubscriptionActive", XC_MethodReplacement.returnConstant(true));
    }

    // Deliveries
    public static void de_orrs_deliveries(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("de.orrs.deliveries.c.a", lpparam.classLoader, "a", String.class, XC_MethodReplacement.returnConstant(""));
    }

}
