int red = 13; //red LED pin
int yellow = 12; //yellow LED pin
int green = 11; //green LED pin
int a = 8; //button a pin
int b = 7; //button b pin
int at = 0; //mode
int bt = 0; //execute
int an = 0; //make sure mode is set for only once
int bn = 0; //how many times button b was pressed and released

void setup(){
  pinMode(red,OUTPUT);
  pinMode(yellow,OUTPUT);
  pinMode(green,OUTPUT);
  pinMode(8,INPUT);
  pinMode(7,INPUT);
  Serial.begin(9600);
}

void loop(){
  int ap = digitalRead(8);
  int bp = digitalRead(7);
  if(ap==1){ //after pressing button a
    bt=0; //stop execute
    bn=0; //reset button b times
    if(an==0){ //make sure mode change is triggered if only if an==0
      if(at<3){ //changing mode
        at++;
        Serial.print("Mode:");
        Serial.println(at);
      }else if(at==3){ //change back to mode 1 if mode 3 was already reached
        at=1;
        Serial.print("Mode:");
        Serial.println(at);
      }
      an++; //make an==1, which stops mode adding process
    }
  }else if(bp==1&&bt==0&&bn==0){ //when button b is pressed initially
    bt=1; //light up
    bn=1; //button b was pressed to light up
  }else if(bp==0&&bn==1){ //when button b is released
    bn=2; //button b was released
  }else if(bp==1&&bn==2){ //when button b is pressed to dark off
    bt=0; //dark off
    bn=3; //button b is pressed to dark off
  }else if(bp==0&&bn==3){ //when button b is released
    bn=4; //button b is released
  }else if(bp==1&&bn==4){ //when button b is pressed to reopen the lights
    bt=1; //light up
    bn=1; //go back to line 41 when button b is released
  }else if(bt==1){ //lights
    if(at==0){
      Serial.println("Nothing");
    }else if(at==1){
      Serial.println("Red");
      digitalWrite(13,HIGH);
    }else if(at==2){
      Serial.println("Red & Green");
      digitalWrite(11,HIGH);
      digitalWrite(13,HIGH);
    }else if(at==3){
      Serial.println("Yellow");
      digitalWrite(12,HIGH);
    }
  }else if(ap==0&&bp==0){ //both button is released, and the lights are off
    an=0; //mode can be added when the next time button a is triggered
    digitalWrite(13,LOW);
    digitalWrite(12,LOW);
    digitalWrite(11,LOW);
  }
}
