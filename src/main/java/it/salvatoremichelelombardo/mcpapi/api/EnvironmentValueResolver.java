package it.salvatoremichelelombardo.mcpapi.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentValueResolver {
    private static final Pattern ENV = Pattern.compile("\\$\\{([A-Za-z_][A-Za-z0-9_]*)}");
    public String resolve(String value) {
        if (value == null) return null;
        Matcher matcher = ENV.matcher(value); StringBuffer out = new StringBuffer();
        while (matcher.find()) { String name = matcher.group(1); String replacement = System.getenv(name); if (replacement == null) throw new IllegalStateException("Required environment variable is not set: " + name); matcher.appendReplacement(out, Matcher.quoteReplacement(replacement)); }
        matcher.appendTail(out); return out.toString();
    }
}
