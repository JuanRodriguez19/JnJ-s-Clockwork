import time
import board
import busio
from adafruit_htu21d import HTU21D
from firebase import firebase

myFirebase = firebase.FirebaseApplication('https://alarm-app-9c3ce.firebaseio.com/')

# Create library object using our Bus I2C port
i2c = busio.I2C(board.SCL, board.SDA)
sensor = HTU21D(i2c)

while True:
    temp = sensor.temperature
    temp = '%.2f' % (temp)
    
    print("\nTemperature: %s C" % temp)
    print("Humidity: %0.1f %%" % sensor.relative_humidity)
    upload ={'Stemperature':temp}
    myFirebase.put('/SensorData',"Stemperature",upload)
    time.sleep(2)
    

    
    

