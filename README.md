# Homework 05-01

## Instructions

Set in [webdriver.properties](src/main/resources/webdriver.properties) file the properties with executable path of:

* [chromedriver](https://chromedriver.chromium.org/)
* [msedgedriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
* [geckodriver](https://github.com/mozilla/geckodriver/releases)

## Exercise 1(Selenium Forms)

Go to page: https://rahulshettyacademy.com/AutomationPractice/

    1. Radio Buttons:
    
    Generate a random between 1-3 (Take note of each random value is equal to
    each radioButton on the web site) then the result should be used on the
    following exercises.

        a. Print in the console the random result
        b. Click over radio button (take note of random result)
        c. Print in the console the SELECTED and NOT SELECTED radioButton
        (the text should be taken of Web Element)
    
    Example:
        "NOT Selected RadioButton: Radio1"
        "Selected RadioButton: Radio2"
        "NOT Selected RadioButton: Radio3"

    2. Input:
        d. Print in the console the place holder of "Suggestion Class Example"
        input (Expected result: "Type to Select Countries")
        e. Add the text "El Sal" and select the first option
        f. Print in the console the text value (Expected result: "El Salvador")

    3. Select: "Dropdown Example"
        g. Use "Select" Java Object and Select option 2
        h. Print in the console the text value (Expected result:"Option2")
        i. Use "WebElement" Java Object and simulate the Select behavior and select option 3
        j. Print in the console the text value (Expected result:"Option3")

    4. Buttons: [Home | Practice | Login | Signup]
        k. Click on each header button (One at the time, you can click it and then go back) 
        l. Print in the console the "text" of each header button and concat
        "Different URL" if the button redirects to a different URL or add
        "Same URL" if the button doesn't redirect to a different URL.

    Example:
        "Different URL - Btn: Home"
        "Same URL - Btn: Practice"
        "Same URL - Btn: Login"
        "Same URL - Btn: Signup"

    5- Tabs: Repeat below instructions until you have 9 open Tabs in browser
        a) Select "Open Tab" button 
        b) Switch back to parent tab - Switch must be done using “SwitchTo()”  
        c) Print the current number of tabs  