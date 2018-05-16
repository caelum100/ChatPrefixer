# ChatPrefixer
ChatPrefixer is a very simple plugin that allows you to format your chat using Vault prefixes and suffixes. Its configuration is extremely easy to use, and the plugin aims to allow you to make your chat look good as easily as possible.

**Dependencies**
To use this plugin, you will need a Vault-compatible prefix managing plugin (often a permissions plugin like PermissionEx, LuckPerms, etc). You will also need to install the latest version of Vault.

**Configuration**
There is only one setting in the configuration: the chat format. You may use variables like ${username} and ${prefix}. For example, if your configuration has
```
format: '${prefix} ${username}: ${message}'
```
then a message from a player named Player with prefix [Prefix] will look something like
`[Prefix] Player: Chat message I just sent`
Full list of variables you can use:
${world} - the sending player's world's name.
${username} - the sending player's username.
${uuid} - the sending player's UUID.
${prefix} - the prefix set for the sending player
${suffix} - the suffix set for the sending player
${message} - the message sent by the player

Please make sure to enclose your format in the YAML configuration in single quotes.

**Support**
Please do not leave a bad review asking for support. Instead, go to the issue tracker and post an issue or send me a PM.
