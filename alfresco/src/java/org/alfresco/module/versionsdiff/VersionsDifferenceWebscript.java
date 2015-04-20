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
 *  Author  Marco Scapoli  <rianko@gmail.com>
 *  File    VersionsDifferenceWebscript.java
 **/

package org.alfresco.module.versionsdiff;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.content.transform.ContentTransformer;
// Google Diff Match Patch import
import org.alfresco.module.versionsdiff.util.diff_match_patch;
import org.alfresco.module.versionsdiff.util.diff_match_patch.Diff;
//import org.alfresco.module.versionsdiff.util.diff_match_patch.Operation;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;

public class VersionsDifferenceWebscript extends DeclarativeWebScript
{
    private static Log logger = LogFactory.getLog(VersionsDifferenceWebscript.class);

    private ServiceRegistry serviceRegistry;
    
    // for Spring injection
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
    
    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache)
    {
        if(null == req)
        {
            logger.error("VersionsDifferenceWebscript.java: The request URL is not well formatted");
            throw new WebScriptException("VersionsDifferenceWebscript.java: The request URL is not well formatted");
        }else{
            
            // generate the returned model object
            Map<String, Object> model = new HashMap<String, Object>();

            // node reference to the last version of the document
            NodeRef lastVersRef = getArgsNodeRef(req);
            
            // node reference to the selected version of the document
            NodeRef selectVersRef = getArgsVersRef(req);

            // Instantiate the diff_match_patch object
            diff_match_patch diffMatchPatch = new diff_match_patch();
            
            // selectedVersRef is the first parameter for INSERT and DELETE right computation
            LinkedList<Diff> diffList = diffMatchPatch.diff_main(getPlainTxtTrasformation(selectVersRef), getPlainTxtTrasformation(lastVersRef));
            
            // semantic cleanup post-processing for human readable differentiation
            diffMatchPatch.diff_cleanupSemantic(diffList);
            
            LinkedList<String[]> diffObjList = new LinkedList<String[]>();
            
            // loop through the Diffs LinkedList
            while (!diffList.isEmpty())
            {
                // Pop of the first element in the list
                Diff element = diffList.pop();
                String[] obj = {element.operation.toString(), element.text.toString()};
                diffObjList.add(obj);
            }
            
            
            model.put("result", diffObjList);
            return model;
        }
    }
            
    private ContentService getContentService()
    { 
        return this.serviceRegistry.getContentService();
    }

    private MimetypeService getMimetypeService()
    { 
        return this.serviceRegistry.getMimetypeService();
    }
    private FileFolderService getFileFolderService()
    {
        return this.serviceRegistry.getFileFolderService();
    }

    /**
     * Get the nodeRef string args from url and put it in a NodeRef object
     *
     * @param WebScriptRequest req for get the nodeRef string from URL querystring
     * @return a NodeRef to the passed URL args nodeRef
     */
    private NodeRef getArgsNodeRef(WebScriptRequest req)
    {
        if(null == req)
        {
            logger.error("Parameter req in WebScriptRequest cannot be of type null");
            throw new WebScriptException("Parameter req in WebScriptRequest cannot be of type null");            
        }else{
            String nodeRefStr = req.getParameter("nodeRef");
            if (StringUtils.isBlank(nodeRefStr))
            {
                throw new WebScriptException("URL args nodeRef cannot be blank");
            }else{
                return new NodeRef(nodeRefStr);
            }
        }
    }

    /**
     * Get the versRef string args from url and put it in a NodeRef object
     *
     * @param WebScriptRequest req for get the versRef string from URL querystring
     * @return a NodeRef to the passed URL args versRef
     */
    private NodeRef getArgsVersRef(WebScriptRequest req)
    {
        if(null == req)
        {
            logger.error("Parameter req in WebScriptRequest cannot be of type null");
            throw new WebScriptException("Parameter req in WebScriptRequest cannot be of type null");
        }else{
            String versRefStr = req.getParameter("versRef");
            if (StringUtils.isBlank(versRefStr))
            {
                throw new WebScriptException("URL args nodeRef cannot be blank");
            }else{
                return new NodeRef(versRefStr);
            }
        }
    }

    /**
     * Get the Filename of a passed NodeRef
     *
     * @param NodeRef of the Filename to retrieve
     * @return a String containing the NodeRef Filename
     */
    protected String getFilename(NodeRef nodeRef)
    {
        if(null == nodeRef){
            throw new WebScriptException("URL args nodeRef cannot be null");
        }
        return getFileFolderService().getFileInfo(nodeRef).getName();
    }

    /**
     * Get the Mimetype of a passed NodeRef
     *
     * @param NodeRef of the Mimetype to retrieve
     * @return a String containing the NodeRef Mimetype
     */
    protected String guessMimetype(NodeRef nodeRef)
    {
        if(null == nodeRef){
            throw new WebScriptException("URL args nodeRef cannot be null");
        }
        String filename = getFilename(nodeRef);
        return getMimetypeService().guessMimetype(filename);
    }    

    /**
     * Transform the passed document in plain/text string
     *
     * @param the node reference of the document to transform in plain/text
     * @return a string with the content of the document or "" if something goes wrong
     */
    private String getPlainTxtTrasformation(NodeRef nodeRef)
    {
        ContentReader reader = getContentService().getReader(nodeRef, ContentModel.PROP_CONTENT);
        if (reader != null && reader.exists())
        {
                // get the transformer
                ContentTransformer transformer = getContentService().getTransformer(reader.getMimetype(), MimetypeMap.MIMETYPE_TEXT_PLAIN);
                
                if (transformer != null)
                {
                    // we have a transformer that is fast enough
                    ContentWriter writer = getContentService().getTempWriter();
                    writer.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);

                    try
                    {
                        transformer.transform(reader, writer);
                        // point the reader to the new-written content
                        reader = writer.getReader();
                        // Check that the reader is a view onto something concrete
                        if (!reader.exists())
                        {
                            throw new ContentIOException("The transformation did not write any content, yet: \n" + "   transformer:     " + transformer + "\n" + "   temp writer:     " + writer);
                        }else{
                            return reader.getContentString();
                        }
                        
                    }
                    catch (ContentIOException e)
                    {
                    }
                }
                return "No trasformer for this type of File";
            }
        return "Content Reader fail";
    }

}