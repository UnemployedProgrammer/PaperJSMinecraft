package com.sebastian.paperJSMinecraft;

import com.sebastian.paperJSMinecraft.custommethods.BukkitMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JSExecute {

    public String execute(String jsCode) {
        // Rhino Context erstellen
        Context context = Context.enter();

        CustomLog.log("Code was executed; " + jsCode);

        try {
            // Den Geltungsbereich (Scope) initialisieren
            Scriptable scope = context.initStandardObjects();

            ScriptableObject.putProperty(scope, "Player", Player.class);
            ScriptableObject.putProperty(scope, "Bukkit", new BukkitMethods());


            // JavaScript-Code mit einer Funktion, die eine Summe berechnet
            //String jsCode = "function calculateSum(a, b) { return a + b; }"
            //        + "var result = calculateSum(5, 7); result;";

            // JavaScript-Code ausführen
            Object result = context.evaluateString(scope, jsCode, "SumScript", 1, null);

            // Ergebnis im Server-Log ausgeben
            return Context.jsToJava(result, String.class).toString();
        } catch (Exception e) {
            //getLogger().severe("Fehler beim Ausführen des Skripts: " + e.getMessage());
            CustomLog.log(Context.jsToJava(e.getMessage(), String.class).toString());
            return Context.jsToJava(e.getMessage(), String.class).toString();
        } finally {
            // Rhino-Context verlassen
            Context.exit();
        }
        //return "Not working, unknown error!";
    }

    public <T extends Event> String executeEvent(String jsCode, T event) {
        // Rhino Context erstellen

        if(jsCode == null) return "No JS Code.";

        Context context = Context.enter();

        CustomLog.log("Code was executed; " + jsCode);

        try {
            // Den Geltungsbereich (Scope) initialisieren
            Scriptable scope = context.initStandardObjects();

            ScriptableObject.putProperty(scope, "Player", Player.class);
            ScriptableObject.putProperty(scope, "Bukkit", new BukkitMethods());
            ScriptableObject.putProperty(scope, "eventData", event);

            // JavaScript-Code ausführen
            Object result = context.evaluateString(scope, jsCode, "SumScript", 1, null);

            // Ergebnis im Server-Log ausgeben
            return Context.jsToJava(result, String.class).toString();
        } catch (Exception e) {
            //getLogger().severe("Fehler beim Ausführen des Skripts: " + e.getMessage());
            CustomLog.log(Context.jsToJava(e.getMessage(), String.class).toString());
            return Context.jsToJava(e.getMessage(), String.class).toString();
        } finally {
            // Rhino-Context verlassen
            Context.exit();
        }
        //return "Not working, unknown error!";
    }
}
