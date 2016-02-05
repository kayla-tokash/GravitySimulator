Title:    Gravity Simulator

Version:  0.2.R4

Author:   Kayla Tokash

Description:

This gravity simulator was originally an experiment with JOGL. This version is completely recoded from the original version in order to make the code cleaner and easier to understand. It is a project I work on in my freetime.

Requied Libraries:
(possibly out of date, I will work on this)

- JOGl (OpenGL Library for Java)
  - gluegen-rt.jar
  - jogl-all.jar

Download here: http://jogamp.org/deployment/archive/rc/v2.2.4/jar/
/I noticed that a new release of the OpenGL libraries is incompatible with my code. I recommend using the v2.2.4 release./


Todo:

- ~~Fix acceration between the mass bodies~~
- Optimize simulation 
  - Updating positions
  - Calculating acceleration
  - Use vertex buffer
- Add features from the original version
  - Mass body explosion
  - Export rendered frames
  - Trail tracking mass bodies INPROGRESS
  - On screen information about the masses
- Add control GUI
  - Add mass body
  - Remove mass body
  - Reset simulation
  - Exit simulation
  - Export simulation
  - Import simulation
  - Make GUI asynchronous
  - Add view controls
- ~~Make a more accurate collision detection~~
- ~~Color each mass body~~

Bugs:

None at the moment


*This current version will follow the largest mass in the universe and slowly rotate the camera around it.*
