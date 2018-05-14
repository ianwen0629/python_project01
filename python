​list1=[]    #english vocabs
list2=[]    #chinese translation

s=0         #score
w=0         #wrong times

import random
file_name="voc.txt"

#q to decide if the file is adding(if) or reading(else)
#p is the word that's adding
def edit_file(q,p):
    if q=="a":                                            #adding
        f=open(file_name, 'a', encoding="ANSI")
        if e:
            f.write("\n")
            f.write(p)
            f.write(",")
        else:
            f.write(p)
        f.close()
    else:                                                 #reading
        filereader=open(file_name, 'r', encoding="ANSI")
        for row in filereader:
            row_split=row.split(",")
            list1.append(row_split[0])
            list2.append(row_split[1])
        filereader.close()

while w<3:                                                              #when wrong times less than 3
    edit_file("r","")                                                   #reading file
    a=list2[random.randrange(0,len(list2))]                             #question
    print(end=a)
    n=input("English answer: ")
    if n=="0":                                                          #if enter "0"
        a=input("add? ")
        keep=True                                                       #to decide whether or not to stay in the while loop
        while (a!="no") and (a!="No") and (keep==True):                 #if enter words other than no
            e=True                                                      #entering English
            if (a=="Yes") or (a=="yes"):                                #if enter yes
                ve=input("enter new English vocab: ")
                while list1.count(ve)>=1:                               #detect if the new word existed or not
                    print("repeated")
                    ve=input("enter new English vocab: ")               #re-enter the word
                edit_file('a',ve)                                       #add new english vocab
                e=False                                                 #not entering english
                vc=input("enter new Chinese translation: ")
                vcn=vc+"\n"
                while (list2.count(vc)>=1) or (list2.count(vcn)>=1):    #detect if the new translation existed or not
                    print("repeated")
                    vc=input("enter new Chinese translation: ")         #re-enter the word
                    vcn=vc+"\n"
                edit_file('a',vc)                                       #add new Chinese translation
                keep=False                                              #not to stay in the while loop
                print("successfully added",ve,vc)                       #print if success
            elif (a!="No") or (a!="no"):                                #if not enter yes nor no
                print("enter yes or no")
                a=input("add? ")
    elif list1[list2.index(a)]==n:                                      #if the answer is correct
        s=s+10
        print("right, score:",s)
    else:                                                               #if the answer is wrong
        s=s-10
        print("wrong, score:",s)
        w=w+1                                                           #wrong times +1
print("game over")                                                      #wrong times = 3

r=1                                    #rank
file="record.txt"
fr=open(file, 'r', encoding="ANSI")
for row in fr:
    row=int(row)                       #change str to int for comparing
    if (s<row) and (s!=row):           #if score is smaller than one of the previous record
        r=r+1                          #rank+1
print("rank:",r)                       #show rank
fr.close()
f=open(file, 'a', encoding="ANSI")
s=str(s)                               #change score from int to str for adding
f.write(s)
f.write("\n")
f.close()
