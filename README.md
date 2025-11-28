# NeoTroll 🎭

A Minecraft troll plugin for Spigot 1.16.5 that adds fun (but harmless) trolling commands.

## Features

- **/crash <player>** - Attempts to crash player's client using various methods
- Multiple crash methods for better compatibility
- Permission-based command access

## Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/crash <player>` | `neotroll.crash` | Attempts to crash specified player's client |

## Installation

1. Download the latest JAR from [Releases](#)
2. Place it in your server's `plugins/` folder
3. Restart the server
4. Configure permissions if needed

## Permissions

- `neotroll.crash` - Allows using /crash command (default: op)

## Building

```bash
mvn clean package
