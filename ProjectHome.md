# Alfresco Add-on | Versions Differences Plug-in #

### INTRO ###
This is the result of the research for my academic degree thesis, developed at Dipartimento di Informatica e Comunicazione (DICOM) in Como at Università degli Studi dell'Insubria with the supervision of Prof.Sandro Morasca and Ph.D Davide Taibi (http://www.taibi.it/en)

The library that implements the Diff function is the [Diff Match Patch Google API](http://code.google.com/p/google-diff-match-patch/) developed by Neil Fraser and based on the [Myer's diff algorithm](http://neil.fraser.name/software/diff_match_patch/myers.pdf)

Developed on Alfresco >4.0c

### WHAT'S GOING ON? ###
The plug-in adds a new action (the icon ![https://versions-difference-alfresco-plug-in.googlecode.com/svn/trunk/screenshot/versions-diff-ico-16.png](https://versions-difference-alfresco-plug-in.googlecode.com/svn/trunk/screenshot/versions-diff-ico-16.png) in the red square location) in the Versions History Panel for showing the differences from the last version of a document and one of the previous at choice.

![https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_action.png](https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_action.png)

### INSTALLATION INSTRUCTION ###

Download the versions-diff-repo-0.6.amp

  1. Copy the file in {ALFRESCO\_HOME}/amps
  1. Start the {ALFRESCO\_HOME}/bin/apply\_amps script for merge the modifications in the .WAR
  1. Start Alfresco for recreate the tomcat/webapps/alfresco and tomcat/webapps/share folders
  1. Stop Alfresco
  1. Deploy the versions-diff-share-0.6.jar and restart to complete
> > -**IMPORTANT NOTE:** the jar file to work properly must be placed in {TOMCAT\_HOME}/webapps/share/WEB-INF/lib
### THE RESULT ###

![https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_diff.png](https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_diff.png)

This is the previous diff in JSON object form (directly from the webscript)

![https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_json.png](https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_json.png)

### FUTURE DEVELOPMENT and PROJECT PATH ###

  * _resolve the version "bug", the version label in grey say 3.0 for both the versions but the upload hour instead is correct because is different (this is caused by an hardcoded function of Alfresco that I'll try to bypass in the future)_
  * _the layout of the lines is somewhat incorrect, and miss the space from the json object, I'll try to implement a better view_
  * _write the wiki and submit online documentation_
  * **[ver.0.6]** ~~_write a **mimetype based sub-component evaluator**_ (allow not to render the action in presence of a mimetype without the plain/text transformation)~~
  * _rewrite the **mimetype based sub-component evaluator**_ (allow to bring the mimetype allowed list from a configuration file)
  * _better Java code_ (want to rewrite a better Java code because the restrictive thesis time did not allow me to write quietly)
  * _better Javascript code_ (see above, same reason)
  * _write a JUNIT test suit_ (my first intention was to apply the Test Driven Development to this project but lack of Junit ft. Alfresco documentation have drove me to develop without tests, this will be an exercise)
  * _testing all the file extension in the mimetype evaluation list_ (now it's only based on mimetypes that have a plain/text transformer, I'm not sure that all works)