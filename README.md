A comprehensive Bukkit plugin that automatically creates backups of a player's inventory, armor, ender chest, and experience upon death. Administrators can manage these backups through an intuitive GUI, grant restoration rights, and players can easily reclaim their items.

## Features

- **Automatic Death Backups:** Creates a complete snapshot of a player's state upon death, including their inventory, armor, ender chest, experience, killer, and cause of death.
- **Intuitive GUI Management:** Administrators can browse, view, and manage all player backups using the `/backup` command.
- **Detailed Backup Inspection:** View the exact contents of a player's inventory, armor, and ender chest from a specific backup.
- **Backup Restoration System:** Admins can grant a backup to a player. The player is then notified and can restore their items using the `/odbierz-backup` command.
- **Customizable Player Notifications:** Utilizes a Boss Bar and chat messages to inform players when they have a granted backup available for restoration.
- **Advanced Sorting & Filtering:** The backup list can be sorted by date (newest/oldest) or by granted status.
- **Automatic Cleanup:** Backups are automatically deleted after a configurable period to save storage space.
- **Highly Configurable:** Customize nearly every aspect, including all GUIs, items, messages, and timings, via easy-to-edit YAML files.
- **Flexible Data Storage:** Powered by okaeri-persistence, allowing for various storage backends like Flat-file, MariaDB/MySQL, and MongoDB.

## Commands & Permissions

| Command              | Description                                                             | Permission         |
| -------------------- | ----------------------------------------------------------------------- | ------------------ |
| `/backup <player>`   | Opens the administrative menu to view and manage backups for a player.  | `backups.view`     |
| `/backup reload`     | Reloads the plugin's configuration files.                               | `backups.reload`   |
| `/odbierz-backup`    | Allows a player to open the menu to claim a granted backup.             | `backups.receive`  |

## Installation

1.  Download the latest `.jar` file from the [Releases](https://github.com/eripe14/backups/releases) page.
2.  Place the downloaded `.jar` file into your server's `plugins/` directory.
3.  Start your server to generate the configuration files (`config.yml` and `message.yml`).
4.  Open `config.yml` and configure your database settings under the `storage-config` section.
5.  Restart the server to apply the changes.

## Configuration

The plugin's behavior is controlled by two main files located in the `plugins/Backups/` directory:

-   `config.yml`: Contains settings for database connection, backup expiration time, Boss Bar appearance, and the complete layout and item customization for all GUIs.
-   `message.yml`: Contains all user-facing messages, allowing for full translation and personalization.

## License

This project is licensed under the MIT License. See the [LICENCE](LICENCE) file for details.
