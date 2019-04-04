from firebase import firebase
from pygame import mixer
import time


firebase = firebase.FirebaseApplication('https://alarm-app-9c3ce.firebaseio.com/')

def sound():
    mixer.init()
    mixer.music.load('/home/pi/Downloads/old-school-bell.mp3')
    mixer.music.play()


while True:
        result = int (firebase.get('/Time/Hour',None))
        result2 = int (firebase.get('/Time/Minute',None))
        
        if result == time.localtime().tm_hour and result2 == time.localtime().tm_min:
            sound()
            print('playing alarm')
            
            #break
        




