Title:    Gravity Simulator

Version:  2.0

Author:   Nicholas Tokash

Description:

This gravity simulator was originally an experiment with JOGL. This version is completely recoded from the original version in order to make the code cleaner and easier to understand. It is a project I work on in my freetime.

Todo:

- Fix acceration between the mass bodies
- Optimize simulation 
  - Updating positions
  - Calculating acceleration
  - Use vertex buffer
- Add features from the original version
  - Mass body explosion
  - Export rendered frames
  - Trails tracking mass bodies
  - On screen information about the masses
- Add control GUI
  - Add mass body
  - Remove mass body
  - Reset simulation
  - Exit simulation
  - Export simulation
- Make a more accurate collision detection
- Add camera controls
- Color each mass body

Bugs:

- Mass bodies added accelerate downward for no apparent reason
