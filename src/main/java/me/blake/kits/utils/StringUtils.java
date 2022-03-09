package me.blake.kits.utils;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    public static void send(CommandSender sender, String key, Placeholder... placeholders){
        String value = transform(key, placeholders);
        sender.sendMessage(value);
    }

    public static void send(CommandSender sender, List<String> messages, Placeholder... placeholders){
        List<String> value = transformMultiple(messages, placeholders);
        value.forEach(sender::sendMessage);
    }

    public static String transform(String message, Placeholder... placeholders){
        message = setPlaceholders(message, placeholders);

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> transformMultiple(List<String> messages, Placeholder... placeholders){
        return messages.stream()
                .map(s -> setPlaceholders(s, placeholders))
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList());
    }

    public static String setPlaceholders(String text, Placeholder... placeholders){
        for(Placeholder placeholder : placeholders){
            text = text.replace("%" + placeholder.getName() + "%", placeholder.getValue());
        }

        return text;
    }


    @Getter
    public static class Placeholder {
        private final String name;
        private final String value;

        public Placeholder(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
