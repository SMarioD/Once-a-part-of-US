# Project Title: Once a Part of Us

## Author: Stoian Mario-Daniel

### Story:
In the largest forest ever seen by man, "The Old Forest," which separates the known society from a foggy realm controlled only by "The Great Tree," strange things began to happen. Gradually, the borders between the cities and the forest became thinner, strange sounds echoed from deep within the woods, and more people began to disappear without a trace.

One night, a massive earthquake awoke the entire nation. Everyone watched in horror as "The Great Tree" fell, and from the depths, a black wave destroyed all the people who had once been protected by nature. After the flood, only the branches of the tree remained, fading into an ominous gray as the strange substance spread into the soil and vegetation. Amidst the destruction, one branch held onto a fruit, and as the substance receded, the earth around the branch collapsed, transforming the branch into a scythe and the fruit into a boy. 

The boy, now a human-like entity formed of the same substance, ventured into the forest, drawn by its mysteries. With a scythe in hand, he begins a legendary adventure to discover his purpose and the fate of the world.

### Project Theme:
Dungeon Crawler. The goal of the game is to guide the main character through three levels to reach the origin of everything, uncover what happened to humanity, and understand why the main character exists in the first place. Along the way, players will face enemies who seek to prevent the journey.

### Rules:
- If the character’s HP reaches 0, the game ends, and the player must start from the first level or continue from the current level, where all previously defeated enemies respawn.
- Players can save progress at any point and reload the game from the saved checkpoint.

### Game Mechanics:
- The character starts with only melee attacks. As players progress through the levels, they will gain new abilities.
- Enemies defeated will release their souls, which serve as symbolic companions that follow the player until the end of the journey.

### Saving and Loading:
The game saves the following details:
- Current and maximum health
- Energy and maximum energy
- Souls collected
- The player’s position
- The current map

The game data is saved when the player chooses the "Save" option from the menu.

### Levels:
1. **First Map** - The forest that grew after the event.
2. **Second Map** - A cave beneath "The Great Tree."
3. **Third Map** - A heavenly realm.

### User Interface:
- **Main Menu**: Options for starting a new game or continuing from the last save.
- **In-game UI**: Displays health and collected souls in the top-left corner.
- **Pause Screen**: Accessed by pressing "P."

### Objects:
- **Key**: Used to open doors and chests.
- **Chest**: Contains valuable items.
- **Human Shoes**: Increase the player’s speed.
- **Soul**: Represents the souls of survivors or enemies.

### Collision Handling:
Collisions are managed using a boolean value `collisionOn`, which checks whether a character can pass through a tile or object.

### Enemy AI:
- Enemies move randomly or use the A* algorithm to find the shortest path to the player.

### Design Patterns:
- **Singleton**: Used in the Player class to ensure only one instance of the player exists.
- **Factory Method**: Used in TileManager to manage object creation.
- **State**: Used in KeyHandler to change the object's behavior depending on its internal state.

### UML Diagram:
- **[Link to UML Diagram]** *(Placeholder for actual UML diagram)*

### Bibliography:
- [Map Editor](https://www.mapeditor.org/)
- [Dungeon Crawler Assets](https://anokolisa.itch.io/dungeon-crawler-pixel-art-asset-pack)
- [Fantasy Monsters Creatures Assets](https://luizmelo.itch.io/monsters-creatures-fantasy)
- Additional resources from [Javier8a](https://www.javier8a.com/itc/bd1/articulo.pdf) (Pages 107-113, 127-134, 305-311).
