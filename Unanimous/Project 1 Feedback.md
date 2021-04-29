# Project 1 Feedback

## Requirements

1. **3 Activities**
2. **2 custom Fragments on the same Activity**
3. **Web Service**
4. **Camera**
5. **Design Patterns**
6. **Readme**
7. **Video**


## Score
7/7


## Notes/Extras

App in general looks ok. The colors are a bit harsh.

All EditTexts currently have text in them, requiring the user delete it before entering their own data. Use the `hint` attribute on the EditText to avoid that.

Movie search, as you know, crashes when it can't find the movie. It also doesn't update the title and description until you press search again.

After taking an image, the image is extremely tiny. You can adjust the scaling of the image to scale within the constraints of the Image View.

There's no observer pattern implemented. You did use the View Model, which I like but you could have actually used it's observable features to get the observer pattern requirement. Make sure you get that pattern in and working for Project 2. That way, you don't have to explicitly hit the Paste button, you can automatically sync those two fragments by observing changes to that shared view model.

Video didn't have audio so I wasn't sure if you went over any of the items above. I tested on different machines and players. In the video you also didn't go through the application. I assume this was just an encoding issue. Double check that your version plays, and if so, maybe encode it in a different format since it wasn't working for me, or you can upload it to a unlisted link on YouTube.

## Final Score

80 + 15 = 95