DreamCode.cc Extension for easy creator making.

Init
<pre>
    this.registerInjectable(BukkitCreator.class).setup(this, componentService);
</pre>

Example
<pre>
    ItemCreatorMenu itemCreatorMenu = cratesPlugin.createInstance(ItemCreatorMenu.class);
    itemCreatorMenu.setItemStack(itemStack);
    itemCreatorMenu.setReturnConsumer(itemStack -> {
        //save retrieved is somewhere
        //save config
    });
    itemCreatorMenu.build().open(player);
</pre>

<pre>
    bukkitCreator.createHandler(player, string -> {
      try {
          double parsed = Double.parseDouble(string);
          //save double
      } catch (Exception exception) {
          creatorMessageConfig.invalidValue.send(player);
      }
  });
</pre>
