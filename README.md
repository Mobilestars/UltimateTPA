# 🔁 UltimateTPA

**UltimateTPA** is a modern, fully-featured teleport request plugin for Minecraft (Spigot/Paper 1.18+).  
It allows players to easily send and manage teleport requests with interactive chat buttons, localization, and smart safety features.

---

## ⚙️ Features

- 💬 **Teleport requests** using `/tpa` and `/tpahere`
- ✅ **Accept or deny** with clickable chat buttons
- ⏱ **Teleport delay** and **move cancellation**
- 🌍 **Multi-language support** (JSON-based language files)
- 🔊 **Sound notifications** for requests and responses
- 🧠 **Smart cooldowns** and **request expiration**
- 🧍‍♂️ Handles both `/tpa` (teleport to a player) and `/tpahere` (teleport player to you)
- 💾 Persistent and modular design for future expansion

---

## 📁 Installation

1. Download the latest release of `UltimateTPA.jar`
2. Place it into your server’s `plugins/` folder
3. Restart or reload your server
4. The plugin automatically generates its configuration and language files

---

<details>
<summary>Config.yml</summary>

```yaml
# ==== UltimateTPA Configuration ====

# ==== Delay ====
teleport-delay: 5 # Time (in seconds) before teleport happens after accept

# ==== Expire-Time ====
request-expire-time: 60 # Time (in seconds) before a teleport request expires

# ==== Conditions ====
cancel-on-move: true # Whether moving during the delay cancels teleport

# ==== Prefix ====
prefix: "&7[&aTPA&7] " # Chat prefix for all messages

# ==== Sounds ====
sound:
  enabled: true
  accept: "ENTITY_PLAYER_LEVELUP"
  deny: "ENTITY_VILLAGER_NO"
  request: "BLOCK_NOTE_BLOCK_PLING"
```

</details>

---

## 🕹️ Commands

| Command | Description |
|----------|--------------|
| `/tpa <player>` | Send a teleport request to another player |
| `/tpahere <player>` | Request another player to teleport to you |
| `/tpaccept` | Accept a pending teleport request |
| `/tpdeny` | Deny a pending teleport request |

---

## 💬 Interactive Chat

Players receive teleport requests with **clickable options**:

[Accept] [Deny]


Clicking a button runs the respective command automatically.  
This makes teleport management fast and intuitive — no command typing required.

---

## 🌐 Language System

UltimateTPA supports **multiple languages** through simple JSON files.  
Each player automatically receives messages in their own Minecraft locale (e.g., `en_US`, `de_DE`).

Example language file structure:

/plugins/UltimateTPA/lang/en_US.json

<details>
<summary>en_US.json</summary>

```json
{
  "tpa_request": "§e%player% wants to teleport to you.",
  "tpahere_request": "&e%player% wants you to teleport to them.",
  "tpa_accept": "§a[Accept]",
  "tpa_deny": "§c[Deny]",
  "tpa_sent": "§7Teleport request sent to §e%player%",
  "tpahere_sent": "&7Teleport request (here) sent to &e%player%",
  "tpa_no_request": "§cYou have no pending teleport requests.",
  "tpa_sender_offline": "§cThe player is no longer online.",
  "tpa_accepted": "§aYou accepted the teleport request.",
  "tpa_accepted_by": "§aYour request was accepted by §e%player%",
  "tpa_denied": "§cYou denied the teleport request.",
  "tpa_denied_by": "§cYour request was denied by §e%player%"
}
```

</details>

### 🌐 Adding New Language Files

You can easily add new language files!  
You can find the available **language codes** [here on the Minecraft Wiki](https://minecraft.fandom.com/wiki/Language) (using the **ISO 639-3** standard).

If you’ve created new translations, feel free to share them with me on **[Discord](https://discord.gg/95ekxaGNB3)** – this helps the plugin grow and support more languages. *(Optional, but very appreciated!)* 💬

---

## 🧩 Developer Overview

**Core Classes:**

- `UltimateTPA` → Main plugin class  
- `TPACommand`, `TPAHereCommand`, `TPAAcceptCommand`, `TPADenyCommand` → Command handlers  
- `TPARequest` → Represents an active teleport request  
- `TPAMoveListener` → Cancels teleportation if the player moves  
- `LanguageManager` → Loads and manages translations  

---

## 🧰 Compatibility

- ✅ Compatible with **Bukkit**, **Spigot**, **Paper**, **Purpur** and **Folia**
- 🧱 Requires **Minecraft 1.18+**  
- ☕ Requires **Java 17+**

---

## 🧑‍💻 Author

**Developed by:** Scholle  
**Package:** `de.scholle.ultimateTPA`

---

## 📜 License

This project is licensed under the **Apache License 2.0**.  
You may freely use, modify, and distribute this plugin under the terms of that license.  

For more details, visit:  
[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
