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