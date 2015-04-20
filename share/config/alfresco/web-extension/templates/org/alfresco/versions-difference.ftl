<#include "include/alfresco-template.ftl" />
<#--
/**
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
 
/**
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
 *  File    versions-difference.ftl
 **/
-->
<@templateHeader>
   <@script type="text/javascript" src="${url.context}/res/modules/documentlibrary/doclib-actions.js"></@script>
   <@link rel="stylesheet" type="text/css" href="${page.url.context}/res/components/document-details/document-details-panel.css" />
   <@link rel="diff_stylesheet" type="text/css" href="${page.url.context}/res/components/document-details/versions-diff-page.css" />
   <@templateHtmlEditorAssets />
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global"/>
      <@region id="title" scope="template"/>
      <@region id="navigation" scope="template"/>
   </div>
   <div id="bd">
      <div class="yui-g">
         <div class="yui-u first" id="left-column" rel="diff_stylesheet">
            <@region id="left-node-header" scope="page"/>
            <@region id="left-column" class="left-column" rel="diff_stylesheet" scope="page"/>
         </div>
         <div class="yui-u" id="right-column" rel="diff_stylesheet">
            <@region id="right-node-header" scope="page"/>
            <@region id="right-column" class="right-column" rel="diff_stylesheet" scope="page"/>
         </div>
      </div>
   </div>
   <@region id="doclib-custom" scope="template"/>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global"/>
   </div>
</@>
