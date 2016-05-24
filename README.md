Ti.FortuneWheelView
===================

This is a Titanium Mobile module project, that wrappes the project https://github.com/myriadmobile/fortune. 
Thanks to [Myriad mobile](http://www.myriadmobile.com/)

![](https://github.com/AppWerft/Ti.FortuneWheelView/raw/master/android/assets/wheelView.gif)

PRs welcome!

Usage
-----
~~~

var Module = require('de.appwerft.fortunewheel');

var wheelView = Module.createWheelView({
    icons : ["amphibia", "aves", "insecta", "mammalia", "reptilia"].map(function(icon) {
        return Ti.Filesystem.getFile(Ti.Filesystem.resourcesDirectory, "assets", icon + '.png').nativePath;
    }), // array of locale images, accepts a string path to a local file, or a TiBlob image object.
    wheelOptions : { /* following properties are optional */
        spinSensitivity : 1, // Multipler for spin speed. ie .5, half the speed of finger
        frameRate : 40, // Frames per second
        friction : 5, // Slows down friction radians per second
        velocityClamp : 15, // clamps max fling to radians per second
        flingable : true, // Decides if the user can fling
        grooves : true, // Locks at correct angles
        notch : 90; // Where the notch is located in degrees
        unselectScaleOffset : .8f, // Scale offset of unselected icons
        selectScaleOffset : 1.0, // Scale offset of the selected icons
        distanceScale : 1,0, // Float from 0 - 1 (should be) to decide how close to the edge the icons show
        centripetalPercent : .25f, // Float from -.5 - distancePercent amount of Centripetal force affects you
    }
});

wheelView.addEventListener('groovechanged',function(){
    console.log(arguments[0].index);
});

wheelView.setSelectedItem(2);
wheelView.getSelectedIndex();
wheelView.getSelectedIndex();


~~~

