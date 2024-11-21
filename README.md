# MyProperties
PROJECT DESCRIPTION: <br/>
  This is an App used to create and store properties<br/>
  
  Architecture: MVVM + Clean Architecture( UseCases, Repositories, etc)<br/>
  Three layers: Infrastructure, Domain, Presentation<br/>
 

STEPS TO BUILD THE APPLICATION:<br/><br/>
  
  Using Android Studio:<br/>
    1. Download Android Studio from official page<br/>
    2. Download Project version committed in master branch. You can download from Github or Cloning the repository using a Git client like Source Tree.<br/>
    3. Open de project in Android Studio<br/>
    4. Connect an Android device in developer mode<br/>
    5. Select the device to run <br/>
    6. Clic in Run Button<br/>
    7. Android Studio Build the application and launch it in the device<br/>
    8. Enjoy the App :)<br/><br/>
    
  Using an Apk File:<br/>
    1. Open Apk File in the device directly<br/>
    2. In new versions of Android a dialog requesting analyze the app appears. You accept the analisis.<br/>
    3. Depending of the type of building the apk, sometimes an alert of posible virus can appear.<br/>
    4. If you trust in the source of the apk file you can continue with the installation skipping the alerts<br/>
    5. Accept intallation and Enjoy the App :)<br/>

  

ABOUT THE USED DEPENDENCIES:<br/><br/>
  Jetpack compose libraries: The latest set of libraries recomended to implement UI in the application.<br/>
  Dagger and Hilt: To manage dependency injection in the App.<br/>
  Room: To store properties in local database<br/>
  Coil: To load images in the app<br/>
  LiveData: To implement MVVM according to suggested by Google<br/>
  GoogleMaps: To display a map to select the location of properties. To search a specific place. To diplay a static map preview of selected location.
