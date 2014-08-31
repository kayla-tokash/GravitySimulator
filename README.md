Title:    Gravity Simulator

Version:  0.2.R2

Author:   Nicholas Tokash

Description:

This gravity simulator was originally an experiment with JOGL. This version is completely recoded from the original version in order to make the code cleaner and easier to understand. It is a project I work on in my freetime.

Requied Libraries:

- JOGl (OpenGL Library for Java)
  - gluegen-rt.jar
  - jogl-all.jar
Download here: http://jogamp.org/

Todo:

//DONE// - Fix acceration between the mass bodies
- Optimize simulation 
  - Updating positions
  - Calculating acceleration
  - Use vertex buffer
- Add features from the original version
  - Mass body explosion
  - Export rendered frames
  - Trail tracking mass bodies
  - On screen information about the masses
- Add control GUI
  - Add mass body
  - Remove mass body
  - Reset simulation
  - Exit simulation
  - Export simulation
  - Import simulation
  - Make GUI asynchronous
- Make a more accurate collision detection
  - Currently, I do not check if the mass bodies collided along their current path. Its possible that a mass body can "pass through" another mass body.
- Add view controls
- Color each mass body

Bugs:

- Collision detection does not work
