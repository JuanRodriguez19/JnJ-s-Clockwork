# JnJ-s-Clockwork
Created By Juan Rodriguez, Johnson Dinh, and Jordan Pulido

### HTU21D-F Temperature/Humidity Sensor (0x40)
![HTU21D-F Sensor](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/HumiditySensor.JPG)

### 1.2″ 4-Digit 7-Segment display
![Display](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/Display.JPG)

### I2S 3W Class D Amplifier Breakout MAX98357
![Amp](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/Amplifier.JPG)



## Table of Contents
1. [Introduction](#introduction)
2. [Budget for Materials Required](#budget-for-materials-required)
3. [Time Schedule](#time-schedule)
4. [Assembly of Pi](#assembly-of-pi)
5. [Wiring](#wiring)
6. [PCB Design Files](#pcb-design-files)
7. [PCB Soldering](#pcb-soldering)
8. [Power Up](#power-up)
9. [Case Design](#case-design)
10. [Assembly for Hardware](#assembly-for-hardware)
11. [Code for Sensors](#code-for-sensors)
12. [Database Design](#database-design)
13. [Mobile Application](#mobile-application) 
14. [Reproduction of Project](#reproduction-of-project)

### Introduction
JnJ’s Clockwork is an android based alarm mobile application where users are able to set and customize alarms of their choice for daily use. The user will be able to create alarm profiles along with being able to set timers and stopwatches. When connected to its corresponding hardware component, the app furthers its capabilities by allowing users the ability to read local temperatures via the sensor included. The project is designed to give users ease of access to anything time related in a simple and clean formfactor. <br>
<br>
This project consists of the HTU21D-F Temperature/Humidity Sensor for reading local temperature values, a 1.2″ 4-Digit 7-Segment display for displaying current time and temperature readings, and a I2S 3W Class D Amplifier Breakout MAX98357 for audio when an alarm goes off. 

### Budget for Materials Required
The required materials and budget for this project can be found in the documentation folder in this repository or in this link provided.
<a href = "https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/JnJ's%20Clockwork%20Budget.pdf">[Budget]</a> <br> 
<br> ![Budget](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/Budget.JPG) <br>

### Time Schedule
Realistically, this project should take around 3-4 weeks to complete if all materials and facilites are available to you. The materials themselves might take a week to arrive due to shipping, but the actual proccess of assembling and programming should not take longer than a week if proper time is given . A couple of hours each day can be dedicated towards the different aspects of the project to make time usuage more efficient and effective. For me, this project took around 2 whole semester (8 months) to finish along with an average work time of around 2.5 hours a week. Here is the time schedule I followed: <br>
<br> ![Schedule](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/ProjectSchedule1.JPG)
![Schedule](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/ProjectSchedule2.JPG)

### Assembly of Pi
These steps will cover how to set up the Raspberry Pi 3 B+ properly so that you have the ability to log in and test your sensors capabilites.

1. Format an SD card with a minimum of 8GB to be used for the OS of the Pi. You can use the following link to download a SD card formatting software: https://www.sdcard.org/downloads/formatter_4/index.html

2. Download and unzip the latest version of the OS for the Raspberry Pi to your SD card. Download NOOBS in the link as that will ensure that you will have almost everything required when starting: https://www.raspberrypi.org/downloads/noobs/   

3. Once the image is on the SD card, remove it from the pc and insert it in the Pi. Now plug in a seperate monitor, mouse, keyboard, HDMI, ethernet cable, and power supply to the Pi in their corresponding ports. The Pi turns on automatically when the power is plugged in.

4. Upon the boot up session, select Raspbian as the operating system for the Pi and follow the instructions as they appear. You may also change the keyboard layout on the bottom during intial boot. The US layout is highly reccommended.

5. Once installation is completed, you should be brought to the desktop. Connect yourself to either Wifi or wired connection in order to perform the next few steps.

6. Open the terminal in the top left corner of the screen and input the following lines (this takes quite a long time):
	```Shell
	wget https://raw.githubusercontent.com/six0four/StudentSenseHat/master/firmware/hshcribv01.sh \  
	-O /home/pi/hshcribv01.sh  
	chmod u+x /home/pi/hshcribv01.sh  
	/home/pi/hshcribv01.sh  
	```

7. Now it is time to set up a VNC connection so that you can access your Pi on any computer screen. From the Start Menu, go -> Preferences->Raspberry Pi Configuration->Interfaces, then set VNC to Enabled. Now on the destop in the top right corner, you should see a VNC logo. When you click it you should see an IP address for your Pi which will be used to connect it via the VNC software. Download the software on any computer you wish to communicate with the Pi: https://www.realvnc.com/en/connect/download/vnc/

8. Once the software is installed, connect the ethernet cable from the Pi to your computer of choice to have a direct connection. Now you can simply input the same address you found in the Pi in the VNC software and it should connect.

9. To turn off the Pi, type `sudo powerdown` in the terminal. 

If you are still unsure or struggling with a part in particular, this video provides a step by step explanation for everthing required: https://www.youtube.com/watch?v=xBlxuf_LSCM


### Wiring
Before wiring each sensor to the breadboard, it is important to solder the pins that come included to the sensors corresponding pin layouts. Addtionally, make sure to cut the exccess pins that come included that will not be required for each sensor. 
<br>
<br>
When soldering, make sure you have safetly glasses equipped along with having proper ventilation that contains a extractor arm for the fumes. A soldering toolkit is also required which is available in most labs.
Here is a great soldering tutorial to help with those unsure.<br>
https://www.youtube.com/watch?v=3230nCz3XQA

You can wire the sensors to the Raspberry Pi using the following charts:
<br>

<b>HTU21D-F Temperature/Humidity Sensor (0x40).</b>

| Device Pin| Pi           |
| --------- | ------------ |
| 1 (VIN)   | [5.0v]       |
| 2 (3.3v)  | [3.3v]       |
| 3 (GND)   | [GND]        |
| 4 (SDA)   | [GPIO 2]     |
| 5 (SCI)   | [GPIO 3]     |


<b>1.2″ 4-Digit 7-Segment display.</b>

| Device Pin| Pi           |
| --------- | ------------ |
| 1 (D)     | [SDA]        |
| 2 (C)     | [SCL]        |
| 3 (+)     | [5.0v]       |
| 4 (-)     | [GND]        |
| 5 (IO)    | [3.3v]       |


<b>I2S 3W Class D Amplifier Breakout MAX98357.</b>

| Device Pin                                     | Pi           |
| ---------------------------------------------- | ------------ |
| 1 (VIN)                                        | [5.0v]       |
| 2 (GND)                                        | [GND]        |
| 3 (DIN)                                        | [GPIO21]     |
| 4 (BCLK)                                       | [GPIO18]     |
| 5 (LRCLK)                                      | [GPIO19]     |


This is the layout for the pins of the Raspberry pi for guidance on where certain pins are located<br>

![pins](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/Pinouts.JPG)


### PCB Design Files
In order to develop the PCB design files, the appilcation Fritzing is required along with the Corresponding sensors file which must be added to the application under MyParts. The files can be located here: https://github.com/adafruit/Fritzing-Library/tree/master/parts
<br>
Once the Sensor is added to parts, you can create a frizting diagram for the wiring of the pi and sensor. It should look similar to this.
![breadboard](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/Breadboard.JPG)

From here you can create the PCB design from the wiring you just designed. The PCB layout should look similar to this.

![PCB](https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Pictures/PCB.JPG)

With these now ready, you can put together your Gerber files and create your PCB using a lazer cutter machine.
The Gerber files are located and can be downloaded <a href = "https://github.com/JuanRodriguez19/JnJ-s-Clockwork/blob/master/Documentation/Fritzing/n01182963%20GerberFiles.zip">Here</a>

### PCB Soldering
Using the same rules as when soldering the Sensors, solder pieces of wire in between the vias on the PCB board. Once thats done, solder the 20 pin socket to the PCB board to the corresonding holes for where the pi would connect. For the remaining pin sockets you have, solder in the respective headers for each sensor in the appropriate locations. Your final board should look similar to this.

Top view with sensors connected: <br>
![imageofsensor](https://github.com/JuanRodriguez19/SensorEffector/blob/master/Documentation/IMG_0924.jpg)

Bottom view: <br>
![imageofsensor](https://github.com/JuanRodriguez19/SensorEffector/blob/master/Documentation/PcbBottom.JPG)

### Power Up
In this section, we will now see if everything works, this works on whether you have soldered your PCB or you normally wired it onto your circuit board. Once connected boot up the Raspberry Pi, open the terminal window and follow these steps:<br>

1. This will bring you into the configuration tool 
```
sudo rasp-config
```
2. Use your arrows keys to go down and select "Interfacing Options"<br>
![Configuration Menu](https://github.com/JDinhGit/TempSensor/blob/master/Documentation/PiScreenshot/Configuration.PNG)<br>
3. Select I2C and submit yes. It should display ARM I2C is enabled.<br>
![i2c option](https://github.com/JDinhGit/TempSensor/blob/master/Documentation/PiScreenshot/i2c.PNG)<br>
4. Exit by selecting the finish option. By using the command below it should your address's for the sensors being (0x40) and (0x70).<br>
```
sudo i2cdetect -y 1
```
![Address Output](https://github.com/JDinhGit/TempSensor/blob/master/Documentation/PiScreenshot/address.PNG)<br>

### Case Design
Still in development. The maximum dimensions the final product can be is around 13/16" x 6" x 2 7/8" = (32.5cm x 15.25cm x 7.25cm).

### Assembly for Hardware
This can only be worked on once case is complete.

### Code for Sensors
<b>HTU21D-F Temperature/Humidity Sensor</b>

<br>

<b>1.2″ 4-Digit 7-Segment display</b>

<br>

<b>I2S 3W Class D Amplifier Breakout MAX98357</b>

<br>

### Database Design
The database connection is established and connected to the mobile application. Reading and writing from the sensor to the database are also required. The database utilizes user-authentication to allow maximum security and protection for the users information. In order to read and write temperature, the user must be registered using a username and password through authentication processing. Offline mode allows access to the app, without the need to register and login. Offline mode skips user-authentication, and moves the user to the actual app. In offline mode, there will be no form of communications to the database.  Therefore the user is unable to read/write temperature to the database. 

### Mobile Application


### Reproduction of Project
If you are to follow these steps exactly as explained in this guide, you should be able to reproduce this project without any diffculties considering you have the necessary equipment and tools.
