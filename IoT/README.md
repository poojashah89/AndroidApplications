# CMPE277_FarmIoT

Download 3 applications and run in the emulator. 
1. Application is built using gradle 2.2.3
2. API Version 25

HOW TO RUN :<br/>
1. Run the FarmIoT Application <br/>
2. Run the FarmManager Application<br/>
3. Run the IotAutomate Application<br/>
4. Enter values in temperature and humidity in FarmIoT Application<br/>
5. It will automatically redirect it to IoTAutomate application<br/>
6. FarmManager application can help you to manually toggle the sensor status<br/>

SCREENSHOTS :

<img width="290" alt="screen shot 2017-03-13 at 10 41 45 pm" src="https://cloud.githubusercontent.com/assets/18491653/23889188/fbca203a-0848-11e7-945f-5386e3372ba3.png">

<img width="293" alt="screen shot 2017-03-13 at 10 46 00 pm" src="https://cloud.githubusercontent.com/assets/18491653/23889191/fd580304-0848-11e7-97eb-d1704a72a39c.png">

<img width="299" alt="screen shot 2017-03-13 at 10 46 18 pm" src="https://cloud.githubusercontent.com/assets/18491653/23889195/02acbaa2-0849-11e7-9b5b-7d5e5709b908.png">


DESCRIPTION OF THE PROJECT

In this assignment, I have created 3 applications with 2 Receiver classes. 

1.FarmIoT application : 

broadcastValues.setAction("com.assignments.sjsu.poojashah.temphumidity") 
//Broadcasting temperature Value
broadcastValues.putExtra("Temperature", tempVal); 
//Broadcasting Humidity Value
broadcastValues.putExtra("Humidity", humidityVal);

This line of code will send temperature and humidity values to FarmManager Application

2.FarmManager application with receiver class ‘MyReceiver’ : Manifest file contains the information of receiver class.

<receiver android:name=".MyReceiver"></receiver>

MyReceiver which is a Receiver class will take the values that are broadcasted and checks for the limit values. Accordingly, values will be boardcasted. 

3.IoTAutomate application with receiver class ‘AutomateReceiver’
Manifest file of IoTAutomate application contains : 

<receiver android:name=”.AutomateReceiver”>

    <intent-filter>
        <action android:name=”com.assignments.sjsu.poojashah.fanon”></action>
        <action android:name=”com.assignments.sjsu.poojashah.fanoff”></action>
        <action android:name=”com.assignments.sjsu.poojashah.fansprinkleron”></action>
        <action android:name=”com.assignments.sjsu.poojashah.fansprinkleroff”></action>

    </intent-filter>
</receiver>
 
These are the broadcasted values from FarmManager application and according to the satisfied condition valued will be received. 
