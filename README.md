# AstroView
## Introduction
AstroView is an Android app, which lets you quickly and easly check your horoscope. It was made as a part of passing Android programming course.
The app has a Firebase connection configured, which enables registration for users as well as signing in via Google SignIn. 
All the horoscope data is fetch from external [Horoscope-API](https://github.com/tapaswenipathak/Horoscope-API).
## Table of contents
1. [Requirements & launch](#requirements-and-launch)
2. [Usage](#usage)
3. [Screenshots](#screenshots)
4. [Credits](#credits)

## Requirements and launch
* Download the code from repository
* Open the project in AndroidStudio
* Configure your FireBase connection
* Run the app on virtual or local device

During the development period, I tested the app on virtual device (API 30, Android 10.0+)

## Usage
After signing in, there are two buttons in main menu - *Horoscope* and *About the author*. After selecting *Horoscope*,
there will be displayed a simple form for user input - just select your zodiac sign, period of time and shake your phone.
Sensors event listener will catch the move and make API request - the horoscope will be displayed in separate view.

## Screenshots
Screenshots below presents basic activities such as **Sign in** and **Sign up**, **Main menu** and **Horoscope form**
<div style="{display: flex; flex-direction: row;"}>
<img width="300" alt="signin" src="https://user-images.githubusercontent.com/48965961/107415098-3acd0f00-6b13-11eb-86ab-b307612aa2e1.png">
<img width="300" alt="signup" src="https://user-images.githubusercontent.com/48965961/107417509-f8590180-6b15-11eb-8f03-5fc331ffec4b.png">
<img width="300" alt="mainmenu" src="https://user-images.githubusercontent.com/48965961/107415100-3c96d280-6b13-11eb-9fd6-2c60349474e8.png">
<img width="300" alt="horoscope" src="https://user-images.githubusercontent.com/48965961/107415108-3e609600-6b13-11eb-9904-e28a77e770b2.png">
</div>

## Credits
* Icons used in AstroView are made by [Freepik](https://www.freepik.com) and [Vitaly Gorbachev](https://www.flaticon.com/authors/vitaly-gorbachev) from [Flaticon](https://www.flaticon.com/)
* [Horoscope-API](https://github.com/tapaswenipathak/Horoscope-API) 
