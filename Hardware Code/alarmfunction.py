from firebase import firebase
from pygame import mixer
import time
import alsaaudio


firebase = firebase.FirebaseApplication('https://alarm-app-9c3ce.firebaseio.com/')

def sound():
    mixer.init()
    mixer.music.load('/home/pi/Downloads/old-school-bell.mp3')
    mixer.music.play()



while True:
        result = int (firebase.get('/Time/Hour',None))
        result2 = int (firebase.get('/Time/Minute',None))
        result3 = int (firebase.get('/Time/Volume',None))

        m = alsaaudio.Mixer('PCM')
        vol = m.getvolume()
        m.setvolume(result3)
        
        if result == time.localtime().tm_hour and result2 == time.localtime().tm_min:
            
            sound()
            print('playing alarm')
            
            #break
        




