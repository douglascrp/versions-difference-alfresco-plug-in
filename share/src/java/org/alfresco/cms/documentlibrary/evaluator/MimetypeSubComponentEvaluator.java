/*
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
 
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
 * Heavily based on tasso9000 example code:
 *               at http://experiencewithalfresco.blogspot.dk/2012/06/type-subcomponent-evaluator.html
 *               
 *  Author  Marco Scapoli  <rianko@gmail.com>
 *  File    MimetypeSubComponentEvaluator.java
 **/

package org.alfresco.cms.documentlibrary.evaluator;

import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.ServletUtil;
import org.springframework.extensions.surf.exception.ConnectorServiceException;
import org.springframework.extensions.surf.extensibility.impl.DefaultSubComponentEvaluator;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class MimetypeSubComponentEvaluator extends DefaultSubComponentEvaluator {

    @Override
    public boolean evaluate(RequestContext context, Map<String, String> params) {
        
        Map<String, String> uriTokens = context.getUriTokens();
        String nodeRef = uriTokens.get("nodeRef");
        if (nodeRef == null) {
            nodeRef = context.getParameter("nodeRef");
        }

        try {

            final Connector conn = context.getServiceRegistry().getConnectorService().getConnector("alfresco", context.getUserId(), ServletUtil.getSession());

            final Response response = conn.call("/api/node/" + nodeRef.replace(":/", ""));
            if (response.getStatus().getCode() == Status.STATUS_OK) {

                String mimetype = parseReponse(response);
                
                if (mimetype.equals("application/msword") ||                                                            //doc
                    mimetype.equals("application/ogg") ||                                                               //ogx    
                    mimetype.equals("application/pdf") ||                                                               //pdf
                    mimetype.equals("application/rtf") ||                                                               //rtf
                    mimetype.equals("application/vnd.apple.keynote") ||                                                 //key
                    mimetype.equals("application/vnd.apple.numbers") ||                                                 //numbers
                    mimetype.equals("application/vnd.apple.pages")   ||                                                 //pages
                    mimetype.equals("application/vnd.ms-excel")     ||                                                  //xls
                    mimetype.equals("application/vnd.ms-excel.template.macroenabled.12")   ||                           //xltm
                    mimetype.equals("application/vnd.ms-outlook")    ||                                                 //msg
                    mimetype.equals("application/vnd.ms-powerpoint") ||                                                 //ppt
                    mimetype.equals("application/vnd.ms-project")    ||                                                 //mpp
                    mimetype.equals("application/vnd.oasis.opendocument.chart")   ||                                    //odc
                    mimetype.equals("application/vnd.oasis.opendocument.image")   ||                                    //odi
                    mimetype.equals("application/vnd.oasis.opendocument.presentation")  ||                              //odp
                    mimetype.equals("application/vnd.oasis.opendocument.spreadsheet")   ||                              //ods
                    mimetype.equals("application/vnd.oasis.opendocument.spreadsheet-template")   ||                     //ots
                    mimetype.equals("application/vnd.oasis.opendocument.text")   ||                                     //odt
                    mimetype.equals("application/vnd.oasis.opendocument.text-master")   ||                              //odm
                    mimetype.equals("application/vnd.oasis.opendocument.text-template")   ||                            //ott
                    mimetype.equals("application/vnd.oasis.opendocument.text-web")   ||                                 //oth
                    mimetype.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")   ||   //pptx
                    mimetype.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")   ||           //xlsx
                    mimetype.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.template")   ||        //xltx
                    mimetype.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")   ||     //docx
                    mimetype.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.template")   ||     //dotx
                    mimetype.equals("application/vnd.sun.xml.calc")   ||                                                //sxc
                    mimetype.equals("application/vnd.sun.xml.impress")   ||                                             //sxi
                    mimetype.equals("application/vnd.sun.xml.writer")   ||                                              //sxw
                    mimetype.equals("application/wordperfect")   ||                                                     //wpd
                    mimetype.equals("application/x-cpio")   ||                                                          //cpio
                    mimetype.equals("application/x-gtar")   ||                                                          //gtar
                    mimetype.equals("application/x-gzip")   ||                                                          //gzip
                    mimetype.equals("application/x-hdf")   ||                                                           //hdf
                    mimetype.equals("application/x-javascript")   ||                                                    //js
                    mimetype.equals("application/x-netcdf")   ||                                                        //cdf
                    mimetype.equals("application/x-shockwave-flash")   ||                                               //swf
                    mimetype.equals("application/x-tar")   ||                                                           //tar
                    mimetype.equals("application/zip")   ||                                                             //zip
                    mimetype.equals("text/calendar")   ||                                                               //ics
                    mimetype.equals("text/css")   ||                                                                    //css
                    mimetype.equals("text/csv")   ||                                                                    //csv
                    mimetype.equals("text/html")   ||                                                                   //html
                    mimetype.equals("text/mediawiki")   ||                                                              //mw
                    mimetype.equals("text/richtext")   ||                                                               //rtx
                    mimetype.equals("text/sgml")   ||                                                                   //sgml
                    mimetype.equals("text/tab-separated-values")   ||                                                   //tsv
                    mimetype.equals("text/x-setext")   ||                                                               //etx
                    mimetype.equals("text/xml")   ||                                                                    //xml
                    mimetype.equals("text/plain")                                                                       //txt
                   )
                    return true;
            } else {
                return false;
            }
        } catch (ConnectorServiceException cse) {
            cse.printStackTrace();
            return false;
        }

        return false;
    }
    

    private String parseReponse(Response response) {
        try {
            Document dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.getResponseStream());
            NodeList list = dom.getElementsByTagName("cmis:propertyString");
            int len = list.getLength();

            for (int i = 0; i < len; i++) {
                Element element = (Element) list.item(i);
                String propertyName = element.getAttribute("propertyDefinitionId");
                String objectMimeTypeId = null;
                if (propertyName.equals("cmis:contentStreamMimeType")) {
                    objectMimeTypeId = element.getElementsByTagName("cmis:value").item(0).getTextContent();
                }
                if (objectMimeTypeId == null) {
                    continue;
                }
                return objectMimeTypeId;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}