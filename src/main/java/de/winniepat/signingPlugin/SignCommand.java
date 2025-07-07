package de.winniepat.signingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SignCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command");
            return true;
        }

        if (!player.hasPermission("signplugin.sign")) {
            player.sendMessage(ChatColor.RED + "You dont have permission to sign items");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /sign <text>");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            player.sendMessage(ChatColor.RED + "You must hold an item to sign it.");
            return true;
        }

        String signText = String.join(" ", args);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(List.of(ChatColor.GRAY + "Signed by " + player.getName(), ChatColor.GOLD + signText));
        item.setItemMeta(meta);

        player.sendMessage(ChatColor.GREEN + "Item signed with: " + ChatColor.GOLD + signText);
        return true;
    }
}
