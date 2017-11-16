Project's author: Marco Scapoli
Original project available at https://code.google.com/p/versions-difference-alfresco-plug-in/

I've just exported this project from Google Code to GitHub to try some changes:
* Update project to use Alfresco SDK
* Make it work on 5.0.d

Join me if you want to help.

 
/*
 * Copyright (C) 2012 Marco Scapoli
 *
 * This file is part of Versions Difference Alfresco Plug-in.
 *
 *  Versions Difference Alfresco Plug-in is free software: you can redistribute 
 *  it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Versions Difference Alfresco Plug-in is distributed in the hope
 *  that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Versions Difference Alfresco Plug-in.
 *  If not, see <http://www.gnu.org/licenses/>.
 *              
 *  Author  Marco Scapoli  <rianko@gmail.com>
 *  File    README.txt
 *----------------------------------------------------------------------------*/

To deploy the extension code 
---------------------------------------
1) Copy tomcat/webapps/alfresco.war to alfresco.war.bak
2) Update the paths in build.properties
3) Stop Alfresco
4) Run the standard ant target - produces build\dist\versions-diff-repo-<version>.amp and build\lib\versions-diff-share-<version>.jar and deploy them
5) Run <alfresco_home>/bin/apply_amps.sh for merge the .AMP with Alfresco .WAR
6) Start Alfresco

These ant targets deploy the AMP and the JAR directly into the Alfresco installation.

If you just want to generate the AMP, JAR do:

package-alfresco-amp - can then be installed with Module Management tool
package-share-jar - can be installed by just copying to webapps/share/WEB-INF/lib


========================== SUPPORTED MIMETYPE LIST: ==========================

               application/msword <!-- doc -->
               application/ogg <!-- ogx -->
               application/pdf <!-- pdf -->
               application/rtf <!-- rtf -->
               application/vnd.apple.keynote <!-- key -->
               application/vnd.apple.numbers <!-- numbers -->
               application/vnd.apple.pages <!-- pages -->
               application/vnd.ms-excel <!-- xls -->
               application/vnd.ms-excel.template.macroenabled.12 <!-- xltm -->
               application/vnd.ms-outlook <!-- msg -->
               application/vnd.ms-powerpoint <!-- ppt -->
               application/vnd.ms-project <!-- mpp -->
               application/vnd.oasis.opendocument.chart <!-- odc -->
               application/vnd.oasis.opendocument.image <!-- odi -->
               application/vnd.oasis.opendocument.presentation <!-- odp -->
               application/vnd.oasis.opendocument.spreadsheet <!-- ods -->
               application/vnd.oasis.opendocument.spreadsheet-template <!-- ots -->
               application/vnd.oasis.opendocument.text <!-- odt -->
               application/vnd.oasis.opendocument.text-master <!-- odm -->
               application/vnd.oasis.opendocument.text-template <!-- ott -->
               application/vnd.oasis.opendocument.text-web <!-- oth -->
               application/vnd.openxmlformats-officedocument.presentationml.presentation <!-- pptx -->
               application/vnd.openxmlformats-officedocument.spreadsheetml.sheet <!-- xlsx -->
               application/vnd.openxmlformats-officedocument.spreadsheetml.template <!-- xltx -->
               application/vnd.openxmlformats-officedocument.wordprocessingml.document <!-- docx -->
               application/vnd.openxmlformats-officedocument.wordprocessingml.template <!-- dotx -->
               application/vnd.sun.xml.calc <!-- sxc -->
               application/vnd.sun.xml.impress <!-- sxi -->
               application/vnd.sun.xml.writer <!-- sxw -->
               application/wordperfect <!-- wpd -->
               application/x-cpio <!-- cpio -->
               application/x-gtar <!-- gtar -->
               application/x-gzip <!-- gzip -->
               application/x-hdf <!-- hdf -->
               application/x-javascript <!-- js -->
               application/x-netcdf <!-- cdf -->
               application/x-shockwave-flash <!-- swf -->
               application/x-tar <!-- tar -->
               application/zip <!-- zip -->
               text/calendar <!-- ics -->
               text/css <!-- css -->
               text/csv <!-- csv -->
               text/html <!-- html -->
               text/mediawiki <!-- mw -->
               text/richtext <!-- rtx -->
               text/sgml <!-- sgml -->
               text/tab-separated-values <!-- tsv -->
               text/x-setext <!-- etx -->
               text/xml <!-- xml -->
               text/plain <!-- txt -->


Project description extracted from the original project's page
----


An Alfresco Add-on for implements the Diff function for different versions of the same document.

Alfresco Add-on | Versions Differences Plug-in

INTRO

This is the result of the research for my academic degree thesis, developed at Dipartimento di Informatica e Comunicazione (DICOM) in Como at Università degli Studi dell'Insubria with the supervision of Prof.Sandro Morasca and Ph.D Davide Taibi (http://www.taibi.it/en)

The library that implements the Diff function is the Diff Match Patch Google API developed by Neil Fraser and based on the Myer's diff algorithm

Developed on Alfresco >4.0c

WHAT'S GOING ON?

The plug-in adds a new action (the icon https://versions-difference-alfresco-plug-in.googlecode.com/svn/trunk/screenshot/versions-diff-ico-16.png in the red square location) in the Versions History Panel for showing the differences from the last version of a document and one of the previous at choice.

https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_action.png

INSTALLATION INSTRUCTION

Download the versions-diff-repo-0.6.amp

Copy the file in {ALFRESCO_HOME}/amps
Start the {ALFRESCO_HOME}/bin/apply_amps script for merge the modifications in the .WAR
Start Alfresco for recreate the tomcat/webapps/alfresco and tomcat/webapps/share folders
Stop Alfresco
Deploy the versions-diff-share-0.6.jar and restart to complete
-IMPORTANT NOTE: the jar file to work properly must be placed in {TOMCAT_HOME}/webapps/share/WEB-INF/lib

THE RESULT

https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_diff.png

This is the previous diff in JSON object form (directly from the webscript)

https://versions-difference-alfresco-plug-in.googlecode.com/svn-history/r3/trunk/screenshot/the_raven_json.png

FUTURE DEVELOPMENT and PROJECT PATH

resolve the version "bug", the version label in grey say 3.0 for both the versions but the upload hour instead is correct because is different (this is caused by an hardcoded function of Alfresco that I'll try to bypass in the future)
the layout of the lines is somewhat incorrect, and miss the space from the json object, I'll try to implement a better view
write the wiki and submit online documentation
[ver.0.6] ~~_write a mimetype based sub-component evaluator_ (allow not to render the action in presence of a mimetype without the plain/text transformation)~~
rewrite the mimetype based sub-component evaluator (allow to bring the mimetype allowed list from a configuration file)
better Java code (want to rewrite a better Java code because the restrictive thesis time did not allow me to write quietly)
better Javascript code (see above, same reason)
write a JUNIT test suit (my first intention was to apply the Test Driven Development to this project but lack of Junit ft. Alfresco documentation have drove me to develop without tests, this will be an exercise)
testing all the file extension in the mimetype evaluation list (now it's only based on mimetypes that have a plain/text transformer, I'm not sure that all works)
