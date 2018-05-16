package men.hasha.proactive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ProActive implements IXposedHookLoadPackage {
    Class<?> hookClass = null;
    Method m;

    public ProActive() throws ClassNotFoundException {
    }

    public void handleLoadPackage(final LoadPackageParam lpparam) throws InvocationTargetException, IllegalAccessException {
        // See if we have the hook
        try {
            hookClass = Class.forName("men.hasha.proactive.hooks." + lpparam.packageName.replace('.', '_'));
        } catch (ClassNotFoundException e) { }

        if (hookClass != null) {
            try {
                m = hookClass.getDeclaredMethod("hook", LoadPackageParam.class);
                m.invoke(null, lpparam);
            } catch (NoSuchMethodException e) {
                XposedBridge.log("No hook(lpparam) for " + lpparam.packageName + ". That's a problem.");
                e.printStackTrace();
            }
        }
    }
}
