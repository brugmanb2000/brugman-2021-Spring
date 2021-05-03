#Unanimous
## By Brandon Brugman

### Overview
My app Unanimous is all about finding an answer everyone agrees on. Where do we want to eat? What movie do you want to watch? When with a group, it can be hard to figure out what everyone wants or how split the group is. This is where Unanimous comes into play!
<br>
Unanimous is a quick and easy app to use that everyone get together and find a singular idea that the group wants to do. <br>
![AddItemResults](https://user-images.githubusercontent.com/54860312/116916941-36dcf480-ac0b-11eb-97f9-3873b8152553.gif)
<br>



### How Does It Work?
To use Unanimous, everyone will have the app downloaded. One person will be a host and start a session. They will give out their session PIN and everyone else can join on that PIN. <br>
![image](https://user-images.githubusercontent.com/54860312/116914779-6dfdd680-ac08-11eb-9cb3-016cecb3d4dd.png)
<br><br>

From there, everyone adds items as they want. Typically, the host will define a topic, but the app is specifically free form so brainstorming fun ideas will not be restricted. <br>
![add item GIF resized](https://user-images.githubusercontent.com/54860312/116918924-ca172980-ac0d-11eb-948d-8fe643306e97.gif)


<br><br>
Once everyone adds items, the users will vote with a like/dislike button. A user can only like or dislike an item once before it falls off their screen. Once the voting ends, everyone can see the results sorted by most to least amount of votes. 
<br>
![ResultsResized](https://user-images.githubusercontent.com/54860312/116919154-1b271d80-ac0e-11eb-94c6-63cd9ef4144d.gif)

<br><br>

### Notes for the Teacher

Some fun under the hood things I added that may not be apparent off the bat: 

- The REST API written in PHP (seen in the PHP folder) so users can share data
- RecyclerView with OnClickListener Buttson on the cards
- RecyclerView sorting in the results section
- Sanitized Data when accessing database
- RetroFIT 2 used for HTTPS calls

Notes about Project:
- Adding Camera was nixed from project as it didn't have any use
- Database is done using RetroFIT and a custom API I created
- This is a combination of Project 2/3/Final =)

### Keywords


